package split.mailrooms;

import java.awt.BorderLayout;
import java.io.*;
import java.util.ArrayList;
import java.awt.EventQueue;
import com.client.common.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
//>>>>>>> origin/Nick
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
//<<<<<<< HEAD
//=======
import javax.swing.JPasswordField;
import com.client.common.*;

public class SUBAddAccount extends JFrame {

	private JPanel contentPane;
	private JPasswordField password;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField pre;
	JRadioButton rdbtnAdministrator;
	final JFrame login;
	JButton btnDelete;
	JButton btnCreate;
	DatabaseManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SUBAddAccount frame = new SUBAddAccount(new DatabaseManager());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public SUBAddAccount(DatabaseManager manager) {
//<<<<<<< HEAD
		this.manager=manager;
		login=null;
//=======
//>>>>>>> origin/Nick
		setTitle("Create Account");
		setResizable(false);
		
		this.setBackground(Color.GREEN);
		
		setBounds(100, 100, 356, 280);
		contentPane = new JPanel();
//<<<<<<< HEAD
//=======
		contentPane.setBackground(new Color(0, 102, 0));
		//>>>>>>> origin/Nick
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseInputA = new JLabel("Please input a username and password");
//<<<<<<< HEAD
//=======
		lblPleaseInputA.setForeground(new Color(255, 255, 255));
		//>>>>>>> origin/Nick
		lblPleaseInputA.setBounds(71, 11, 293, 14);
		contentPane.add(lblPleaseInputA);
		
		password = new JPasswordField();
		password.setBounds(125, 130, 195, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		 rdbtnAdministrator = new JRadioButton("Administrator");
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(10, 102, 103, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(10, 133, 80, 14);
		contentPane.add(lblPassword);
		
		
		 btnCreate = new JButton("Create Account");
		btnCreate.addActionListener(new ButtonListener());
		btnCreate.setBounds(192, 218, 128, 23);
		contentPane.add(btnCreate);
		
		 rdbtnAdministrator = new JRadioButton("Administrator");
		 rdbtnAdministrator.setBackground(new Color(0, 102, 0));
		 rdbtnAdministrator.setForeground(new Color(255, 255, 255));
		 //>>>>>>> origin/Nick
		buttonGroup.add(rdbtnAdministrator);
		rdbtnAdministrator.setBounds(71, 188, 109, 23);
		contentPane.add(rdbtnAdministrator);
		
		JRadioButton rdbtnUser = new JRadioButton("User");
		//<<<<<<< HEAD
		//=======
		rdbtnUser.setForeground(new Color(255, 255, 255));
		rdbtnUser.setBackground(new Color(0, 102, 0));
//>>>>>>> origin/Nick
		rdbtnUser.setSelected(true);
		buttonGroup.add(rdbtnUser);
		rdbtnUser.setBounds(182, 188, 109, 23);
		contentPane.add(rdbtnUser);
		
//<<<<<<< HEAD
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setForeground(Color.WHITE);
		lblReenterPassword.setBounds(10, 166, 121, 14);
		contentPane.add(lblReenterPassword);
		
		pre = new JPasswordField();
		pre.setBounds(125, 161, 195, 20);
		contentPane.add(pre);
		pre.setColumns(10);
		
		btnDelete = new JButton("Delete Account");
		btnDelete.setBounds(54, 218, 128, 23);
		btnDelete.addActionListener(new DeleteListener());
		contentPane.add(btnDelete);
		
		username = new JTextField();
		username.setBounds(127, 99, 193, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setBounds(10, 39, 107, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(10, 71, 76, 14);
		contentPane.add(lblLastName);
		
		firstName = new JTextField();
		firstName.setBounds(125, 36, 195, 20);
		contentPane.add(firstName);
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setBounds(125, 68, 195, 20);
		contentPane.add(lastName);
		lastName.setColumns(10);
		
		setVisible(true);
	}
	
	
	public SUBAddAccount(SUBlogin suBlogin,DatabaseManager manager) {
		this.login=suBlogin;
		this.manager = manager;
		
//=======
//>>>>>>> origin/Nick
		setTitle("Create Account");
		setResizable(false);
		
		this.setBackground(Color.GREEN);
		
		setBounds(100, 100, 356, 280);
		contentPane = new JPanel();
//<<<<<<< HEAD
//=======
		contentPane.setBackground(new Color(0, 102, 0));
		//>>>>>>> origin/Nick
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseInputA = new JLabel("Please input a username and password");
//<<<<<<< HEAD
//=======
		lblPleaseInputA.setForeground(new Color(255, 255, 255));
		//>>>>>>> origin/Nick
		lblPleaseInputA.setBounds(71, 11, 293, 14);
		contentPane.add(lblPleaseInputA);
		
		password = new JPasswordField();
		password.setBounds(125, 130, 195, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		 rdbtnAdministrator = new JRadioButton("Administrator");
		 rdbtnAdministrator.setSelected(true);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(14, 102, 103, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(10, 133, 80, 14);
		contentPane.add(lblPassword);
		
		
		 btnCreate = new JButton("Create Account");
		btnCreate.addActionListener(new ButtonListener());
		btnCreate.setBounds(192, 218, 128, 23);
		contentPane.add(btnCreate);
		
		 rdbtnAdministrator = new JRadioButton("Administrator");
		 rdbtnAdministrator.setBackground(new Color(0, 102, 0));
		 rdbtnAdministrator.setForeground(new Color(255, 255, 255));
		 rdbtnAdministrator.setSelected(true);
		 //>>>>>>> origin/Nick
		buttonGroup.add(rdbtnAdministrator);
		rdbtnAdministrator.setBounds(71, 188, 109, 23);
		contentPane.add(rdbtnAdministrator);
		
		JRadioButton rdbtnUser = new JRadioButton("User");
		//<<<<<<< HEAD
		//=======
		rdbtnUser.setEnabled(false);
		rdbtnUser.setForeground(new Color(255, 255, 255));
		rdbtnUser.setBackground(new Color(0, 102, 0));
//>>>>>>> origin/Nick
		rdbtnUser.setSelected(true);
		buttonGroup.add(rdbtnUser);
		rdbtnUser.setBounds(182, 188, 109, 23);
		contentPane.add(rdbtnUser);
		
//<<<<<<< HEAD
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setForeground(Color.WHITE);
		lblReenterPassword.setBounds(10, 167, 121, 14);
		contentPane.add(lblReenterPassword);
		
		pre = new JPasswordField();
		pre.setBounds(125, 161, 195, 20);
		contentPane.add(pre);
		pre.setColumns(10);
		
		btnDelete = new JButton("Delete Account");
		btnDelete.setEnabled(false);
		btnDelete.setBounds(54, 218, 128, 23);
		btnDelete.addActionListener(new DeleteListener());
		contentPane.add(btnDelete);
		
		username = new JTextField();
		username.setBounds(127, 99, 193, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setBounds(10, 36, 107, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(14, 77, 76, 14);
		contentPane.add(lblLastName);
		
		firstName = new JTextField();
		firstName.setBounds(125, 36, 195, 20);
		contentPane.add(firstName);
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setBounds(125, 68, 195, 20);
		contentPane.add(lastName);
		lastName.setColumns(10);
		
		setVisible(true);
	}
	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("In Delete Button");
		
			
			
			
			
			JOptionPane p = new JOptionPane();
			if(!password.getText().equals(pre.getText())){
				
				p.showMessageDialog(null, "The two passwords did not match");
				return;
			}
			
			Integer hash = username.getText().hashCode()+password.getText().hashCode();
			if(manager.login(username.getText(), hash)!=null){
				JOptionPane.showMessageDialog(null,"This account already exists");
				return;
			}
			
			
			
			User u = new User(username.getText(),firstName.getText(),lastName.getText(), rdbtnAdministrator.isSelected());
			
			
			
			manager.createUser(u, hash);
			
//<<<<<<< HEAD
				if(login!=null){
					login.setVisible(true);
					dispose();
					
				}
				
//=======
//>>>>>>> origin/Nick
				password.setText(null);
				username.setText(null);
				pre.setText(null);

//>>>>>>> origin/Nick
			
			String s;
		
			dispose();
		}
			
		}
	
//<<<<<<< HEAD
		JFrame f = this;
		private JTextField username;
		private JTextField firstName;
		private JTextField lastName;
		
	
		public class DeleteListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				new DelAccount(manager).setVisible(true);
				
				
	//<<<<<<< HEAD
				dispose();
	//=======
				
	//>>>>>>> origin/Nick
				
			}
				
				
			}
		}

	
	

