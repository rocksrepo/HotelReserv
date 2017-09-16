package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(166, 29, 100, 48);
		
		JButton button = new JButton("New button");
		button.setBounds(303, 29, 100, 48);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(21, 144, 100, 48);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(166, 144, 100, 48);
		
		JButton btnSetup = new JButton("Setup");
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Systemsetup sysSet = new Systemsetup();
				sysSet.setLocationRelativeTo(null);
				sysSet.setVisible(true);
			}
		});
		btnSetup.setBounds(303, 144, 100, 48);
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_1);
		contentPane.add(button);
		contentPane.add(button_1);
		contentPane.add(button_2);
		contentPane.add(btnSetup);
		
		JButton button_4 = new JButton("New button");
		button_4.setBounds(21, 29, 100, 48);
		contentPane.add(button_4);
	}

}
