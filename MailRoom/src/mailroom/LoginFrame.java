package mailroom;

import java.awt.BorderLayout;
import java.io.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField password;
	public boolean admin;
	public boolean accountExists;
	JLabel lblLoginError;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please Log In");
		lblNewLabel.setBounds(139, 11, 108, 14);
		contentPane.add(lblNewLabel);
		
		userName = new JTextField();
		userName.setBounds(96, 50, 151, 20);
		contentPane.add(userName);
		userName.setColumns(10);
		
		password = new JTextField();
		password.setBounds(96, 81, 151, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new OkListener(this));
		btnOk.setBounds(139, 131, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnClose = new JButton("Close");
		
		btnClose.setBounds(235, 131, 89, 23);
		btnClose.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
				
			}
			
		});
		contentPane.add(btnClose);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 53, 76, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 84, 46, 14);
		contentPane.add(lblPassword);
		
		 lblLoginError = new JLabel("Login Error");
		lblLoginError.setForeground(Color.RED);
		lblLoginError.setVisible(false);
		lblLoginError.setBounds(135, 36, 93, 14);
		contentPane.add(lblLoginError);
	}
	
	public class OkListener implements ActionListener{
		JFrame f;
		public OkListener(JFrame f){
			this.f=f;
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> hashes = new ArrayList<String>();
			Integer hash = userName.getText().hashCode()+password.getText().hashCode();
			System.out.println(hash);
			File a= new File("Admin_Hash.txt");
			File u= new File("User_Hash.txt");
			try {
				BufferedReader reader = new BufferedReader(new FileReader(a));
				String input;
				while((input=reader.readLine())!=null){
					hashes.add(input);
				}
				
				for(String s: hashes){
					if(s.equals(hash.toString())){
						admin=true;
						accountExists=true;
						
						
					}
				}
				if(!accountExists){
					admin=false;
					reader = new BufferedReader(new FileReader("User_Hash.txt"));
					
					hashes = new ArrayList<String>();
					
					while((input=reader.readLine())!=null){
						hashes.add(input);
					}
					for(String st: hashes){
						if(st.equals(hash.toString())){
							accountExists=true;
							
						}
						
					}
				}
				if(!accountExists){
					lblLoginError.setVisible(true);
					
				}
				else{
					f.setVisible(false);
					OpenScreen o = new OpenScreen(admin);
					o.setVisible(true);
				}
					
				
				
			} catch (FileNotFoundException e1) {
				new AddAccount().setVisible(true);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
}
