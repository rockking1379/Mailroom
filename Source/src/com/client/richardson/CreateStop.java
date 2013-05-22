package com.client.richardson;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class CreateStop extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	ArrayList<Route> routes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateStop frame = new CreateStop(new DatabaseManager());
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
	public CreateStop(DatabaseManager manager) {
		ArrayList<Route> routes;
		setResizable(false);
		setVisible(true);
		setTitle("Create Stop");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 316, 221);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNameOfThe = new JLabel("Name of the New Stop:");
		lblNameOfThe.setForeground(new Color(255, 255, 255));
		lblNameOfThe.setBounds(83, 31, 163, 14);
		contentPane.add(lblNameOfThe);
		
		textField = new JTextField();
		textField.setBounds(61, 56, 180, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSelectRouteTo = new JLabel("Select Route to add Stop To:");
		lblSelectRouteTo.setForeground(new Color(255, 255, 255));
		lblSelectRouteTo.setBounds(71, 88, 163, 14);
		contentPane.add(lblSelectRouteTo);
		
		routes=(ArrayList<Route>)manager.getRoutes();
		String[] rtNames = new String[routes.size()-1];
		for(Route r: routes){
			rtNames[routes.indexOf(r)] =r.getName();
			
		}
		
		
		JComboBox comboBox = new JComboBox();
		DefaultComboBoxModel model = new DefaultComboBoxModel(rtNames);
		comboBox.setBounds(94, 113, 116, 20);
		
		contentPane.add(comboBox);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(104, 144, 89, 23);
		contentPane.add(btnCreate);
	}
}
