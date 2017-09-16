package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db.DbAccess;
import main.Employee;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Systemsetup extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JTextField textField_3;
	private DefaultTableModel model;
	private static ArrayList<Employee> empList;
	private DbAccess dba;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Systemsetup frame = new Systemsetup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static ArrayList<Employee> getEmpList() {
		return empList;
	}
	
	public void setUpdateFields(Employee emp) {
		textField_1.setText(emp.getUserId());
		textField_2.setText(emp.getEmpName());
		textField_3.setText(emp.getEmplName());
	}

	/**
	 * Create the frame.
	 */
	public Systemsetup() {	
		dba = new DbAccess();
		
		Systemsetup sysSet = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 544, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		userTable("");
		
		table.getColumn("Edit").setCellRenderer(new ButtonRender());
		table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(),sysSet));
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				userTable(textField.getText());
				
				table.getColumn("Edit").setCellRenderer(new ButtonRender());
				table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(),sysSet));
			}
		});
		textField.setToolTipText("Search Employee");
		textField.setBounds(10, 29, 177, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(10, 293, 93, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 318, 93, 14);
		contentPane.add(lblLastName);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 243, 93, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 268, 93, 14);
		contentPane.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(98, 240, 161, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 265, 161, 20);
		contentPane.add(passwordField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(98, 290, 161, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(98, 315, 161, 20);
		contentPane.add(textField_3);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(170, 374, 89, 23);
		contentPane.add(btnAddUser);
	}
	
	public void userTable(String SearchStr) {
		model = new DefaultTableModel();
		
		String[] colums = {"EmployeeId","Name","User ID","Edit"};
		Vector<String> columnIdentifiers = new Vector<>();		
		Collections.addAll(columnIdentifiers, colums);
		
		empList = new ArrayList<>();
		Vector<Vector<String>> tableData = new Vector<>();
		
		//columnIdentifiers.add
		//model.setColumnIdentifiers(columnIdentifiers);
		
		ResultSet rs = dba.getAllEmployees(SearchStr);
		
		try {
			while (rs.next()) {
				int empId = rs.getInt("employeeId");
				String empfName = rs.getString("fname");
				String emplName = rs.getString("lname");
				String userId = rs.getString("uname");
				
				Employee emp = new Employee(empId, empfName, emplName, userId);
				
				Vector<String> tempVector = new Vector<>();
				tempVector.add(String.valueOf(empId));
				tempVector.add(empfName+" "+emplName);
				tempVector.add(userId);			
				tempVector.add("Edit");	
				
				empList.add(emp);
				tableData.add(tempVector);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setDataVector(tableData, columnIdentifiers);
		table.setModel(model);
	}
}

class ButtonRender extends JButton implements TableCellRenderer{
	
	public ButtonRender(){
		setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText((value == null) ? "" : value.toString());
		return this;
	}	
}

class ButtonEditor extends DefaultCellEditor {
	protected JButton button;

	private String label;
	private int selectedRow;
	private Systemsetup setupGui;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox, Systemsetup sysSetup) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
		
		setupGui=sysSetup;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		selectedRow = row;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			//JOptionPane.showMessageDialog(null,String.valueOf(selectedRow));
			// System.out.println(label + ": Ouch!");
			Employee selectedEmp = Systemsetup.getEmpList().get(selectedRow);
			setupGui.setUpdateFields(selectedEmp);
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
