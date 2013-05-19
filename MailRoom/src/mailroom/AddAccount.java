package mailroom;

import java.awt.BorderLayout;
import java.io.*;
import java.util.ArrayList;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class AddAccount extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField pre;
	JRadioButton rdbtnAdministrator;
	final JFrame login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAccount frame = new AddAccount();
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
	public AddAccount() {
		login=null;
		setTitle("Create Account");
		setResizable(false);
		
		this.setBackground(Color.GREEN);
		
		setBounds(100, 100, 450, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseInputA = new JLabel("Please input a username and password");
		lblPleaseInputA.setBounds(71, 11, 293, 14);
		contentPane.add(lblPleaseInputA);
		
		username = new JTextField();
		username.setBounds(150, 36, 168, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(37, 39, 103, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(36, 64, 80, 14);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(150, 67, 168, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ButtonListener());
		btnOk.setBounds(345, 130, 89, 23);
		contentPane.add(btnOk);
		
		 rdbtnAdministrator = new JRadioButton("Administrator");
		buttonGroup.add(rdbtnAdministrator);
		rdbtnAdministrator.setBounds(71, 130, 109, 23);
		contentPane.add(rdbtnAdministrator);
		
		JRadioButton rdbtnUser = new JRadioButton("User");
		rdbtnUser.setSelected(true);
		buttonGroup.add(rdbtnUser);
		rdbtnUser.setBounds(182, 130, 109, 23);
		contentPane.add(rdbtnUser);
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setBounds(37, 109, 103, 14);
		contentPane.add(lblReenterPassword);
		
		pre = new JPasswordField();
		pre.setBounds(150, 103, 168, 20);
		contentPane.add(pre);
		pre.setColumns(10);
		
		setVisible(true);
	}
	public AddAccount(login login) {
		this.login=login;
		
		setTitle("Create Account");
		setResizable(false);
		
		this.setBackground(Color.GREEN);
		
		setBounds(100, 100, 450, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseInputA = new JLabel("Please input a username and password");
		lblPleaseInputA.setBounds(71, 11, 293, 14);
		contentPane.add(lblPleaseInputA);
		
		username = new JTextField();
		username.setBounds(150, 36, 168, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(37, 39, 103, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(36, 64, 80, 14);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(150, 67, 168, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ButtonListener());
		btnOk.setBounds(345, 130, 89, 23);
		contentPane.add(btnOk);
		
		 rdbtnAdministrator = new JRadioButton("Administrator");
		buttonGroup.add(rdbtnAdministrator);
		rdbtnAdministrator.setSelected(true);
		rdbtnAdministrator.setBounds(71, 130, 109, 23);
		contentPane.add(rdbtnAdministrator);
		
		JRadioButton rdbtnUser = new JRadioButton("User");
		rdbtnUser.setSelected(true);
		rdbtnUser.setEnabled(false);
		buttonGroup.add(rdbtnUser);
		rdbtnUser.setBounds(182, 130, 109, 23);
		contentPane.add(rdbtnUser);
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setBounds(37, 109, 103, 14);
		contentPane.add(lblReenterPassword);
		
		pre = new JPasswordField();
		pre.setBounds(150, 103, 168, 20);
		contentPane.add(pre);
		pre.setColumns(10);
		
		setVisible(true);
	}
	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane p = new JOptionPane();
			if(!password.getText().equals(pre.getText())){
				
				p.showMessageDialog(null, "The two passwords did not match");
				return;
			}
			
			Integer hash = username.getText().hashCode()+password.getText().hashCode();
			System.out.println(hash);
			
			
			try {
				String file="User_Hash.txt";
				if(rdbtnAdministrator.isSelected()){
					file="Admin_Hash.txt";
				}
				File f = new File(file);
				BufferedReader reader = new BufferedReader(new FileReader(f));
				ArrayList<String> hashes= new ArrayList<String>();
				String input;
				while((input=reader.readLine())!=null){
					hashes.add(input);
				}
				reader.close();
				
				for(String s: hashes){
					
					if(s.equals(hash.toString())){
						System.out.println("Account exists");
						System.out.println(s);
						p.showMessageDialog(null, "This account already exists");
					return;
					}
				}
				
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				for(String s: hashes){
					writer.write(s);
					writer.newLine();
				}
				System.out.println(hash);
				writer.write(hash.toString());
				writer.newLine();
				writer.close();
				
				if(!login.equals(null)){
					login.setVisible(true);
					
				}
				
				password.setText(null);
				username.setText(null);
				pre.setText(null);
				
				
				
			} catch (FileNotFoundException e1) {
				
				File f = new File("User_Hash.txt");
				File fi = new File("Admin_Hash.txt");
				try {
					BufferedWriter w = new BufferedWriter(new FileWriter(f));
					w.write("");
					w= new BufferedWriter(new FileWriter(fi));
					w.write("");
				} catch (IOException e2) {
					System.out.println("The hash files could not be created");
					e2.printStackTrace();
				}
				actionPerformed(e);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			dispose();
			
		}
			
		}
		JFrame f = this;
	}
	

