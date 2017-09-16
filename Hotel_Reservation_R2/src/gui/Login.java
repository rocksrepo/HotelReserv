package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.DbAccess;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private DbAccess dba;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		dba = new DbAccess();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(45, 57, 73, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 108, 73, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(140, 54, 148, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 108, 148, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String uname = textField.getText();
				char[] pass = passwordField.getPassword();
				String password = new String(pass);
				
				boolean validLogin = dba.isValidLogin(uname, password);
				
				if(validLogin){					
					MainMenu mainMen = new MainMenu();
					mainMen.setLocationRelativeTo(null);
					mainMen.setVisible(true);
					
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid User Name or Password");
				}
				
			}
		});
		btnLogin.setBounds(199, 171, 89, 23);
		contentPane.add(btnLogin);
	}
}
