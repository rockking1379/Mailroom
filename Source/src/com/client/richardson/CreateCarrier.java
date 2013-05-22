package com.client.richardson;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CreateCarrier extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCarrier frame = new CreateCarrier();
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
	public CreateCarrier() {
		setResizable(false);
		setTitle("Add Carrier");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 261, 124);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterNameOf = new JLabel("Enter Name of the New Carrier:");
		lblEnterNameOf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnterNameOf.setForeground(new Color(255, 255, 255));
		lblEnterNameOf.setBounds(43, 11, 204, 14);
		contentPane.add(lblEnterNameOf);
		
		textField = new JTextField();
		textField.setBounds(53, 36, 156, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(83, 67, 89, 23);
		contentPane.add(btnAdd);
	}
}
