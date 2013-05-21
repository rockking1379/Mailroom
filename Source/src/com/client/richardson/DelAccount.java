package com.client.richardson;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class DelAccount extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelAccount frame = new DelAccount();
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
	public DelAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseSelectAn = new JLabel("Please select an Account to delete:");
		lblPleaseSelectAn.setBounds(40, 11, 229, 14);
		contentPane.add(lblPleaseSelectAn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(43, 36, 184, 20);
		contentPane.add(comboBox);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(91, 67, 89, 23);
		contentPane.add(btnDelete);
	}
}
