package com.client.richardson;

import java.awt.BorderLayout;
import java.io.*;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JPasswordField;

import com.client.common.DatabaseManager;
import com.client.common.Stop;
import com.client.common.User;

import java.awt.Font;
//>>>>>>> origin/Nick

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	public boolean admin;
	public boolean accountExists;
	JLabel lblLoginError;
	private JPasswordField passwordField;
	DatabaseManager manager;
	
	File adminHash = new File("Admin_Hash.txt");
	File userHash = new File("User_Hash.txt");
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
//<<<<<<< HEAD
					
					frame.setTitle("Login");
					
					ImageIcon icon= new ImageIcon(getClass().getResource("/image/Key.jpg"));
					frame.setIconImage(icon.getImage());
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
//=======
					frame.setVisible(true);
//>>>>>>> origin/Nick
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		manager = new DatabaseManager();
		loadSettings();
		
//<<<<<<< HEAD
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 203);
		contentPane = new JPanel();
//=======
		setTitle("Login To Mailroom System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 203);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
//>>>>>>> origin/Nick
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please Log In");
//<<<<<<< HEAD
		lblNewLabel.setBounds(139, 11, 108, 14);
//=======
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(123, 11, 124, 14);
//>>>>>>> origin/Nick
		contentPane.add(lblNewLabel);
		
		userName = new JTextField();
		userName.setBounds(96, 50, 151, 20);
		contentPane.add(userName);
		userName.setColumns(10);
		
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
//<<<<<<< HEAD
//=======
		lblUsername.setForeground(new Color(255, 255, 255));
//>>>>>>> origin/Nick
		lblUsername.setBounds(10, 53, 76, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");

		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(10, 84, 76, 14);
		contentPane.add(lblPassword);
		
		 lblLoginError = new JLabel("Login Error");
		 lblLoginError.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoginError.setForeground(Color.RED);
		lblLoginError.setVisible(false);
		lblLoginError.setBounds(123, 30, 103, 23);
//>>>>>>> origin/Nick
		contentPane.add(lblLoginError);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(96, 81, 151, 20);
		contentPane.add(passwordField);
//<<<<<<< HEAD
		
		
		
//=======
//>>>>>>> origin/Nick
	}
	
	public class OkListener implements ActionListener{
		JFrame f;
		public OkListener(JFrame f){
			this.f=f;
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(userName.getText().equals("DEV")&&passwordField.getText().equals("CSCI")){
				OpenScreen o = new OpenScreen(true,manager,"Developer Access");
				o.setVisible(true);
				adminHash=null;
				userHash=null;
				f.dispose();
			}
		

			Integer hash = userName.getText().hashCode()+passwordField.getText().hashCode();
			System.out.println(hash);
			User u = manager.login(userName.getText(),hash);
			
			if(u==null){
				lblLoginError.setVisible(true);
				return;
			}
			

			String fullName = u.getFName()+" "+u.getLName();
			

			
			
					OpenScreen o = new OpenScreen(u.getAdmin(),manager,fullName);
					o.setVisible(true);
					adminHash=null;
					userHash=null;
					f.dispose();
//=======
					
//>>>>>>> origin/Nick
				
					
				
		
			
		}
		
	}
	public void loadSettings(){
		
		File settings = new File("./properties.prop");
		if(settings.exists())
		{
			try
			{
				FileInputStream fStream = new FileInputStream(settings);
				DataInputStream dis = new DataInputStream(fStream);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		
				String settingLine;
				while((settingLine = br.readLine()) != null)
				{
					//Read Settings
					String setting = "";				
					int index = 0;
					while(settingLine.charAt(index) != ';')
					{
						setting += settingLine.charAt(index);
						index++;
					}
					index++;
					if(setting.toUpperCase().equals("DATABASE"))
					{
						//Read in Database configuration
						String temp = "";
						for(int i = index; i < settingLine.length(); i++)
						{
							temp += settingLine.charAt(i);
						}
						manager.setDatabase(temp);
					}
					else
					{
						if(setting.toUpperCase().equals("PERSONS"))
						{
							String temp = "";
							
							for(int i = index; i < settingLine.length(); i++)
							{
								temp += settingLine.charAt(i);
							}
							manager.setFile(temp);
						}
					}
				}
				br.close();
			}
			catch(Exception e)
			{
				//Do nothing
			}
			manager.setup();
			//manager.loadPackages(true, null);
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Settings File Not Found.");
			
			JFileChooser fc = new JFileChooser();
			fc.showDialog(null, "SELECT");
			fc.setFileFilter(null);
			File database = fc.getSelectedFile();
			fc.showDialog(null, "SELECT");
			File persons = fc.getSelectedFile();
			
			try 
			{
				if(settings.createNewFile())
				{
					FileWriter fOutput = new FileWriter(settings);
					BufferedWriter bw = new BufferedWriter(fOutput);
					bw.write("DATABASE;" + database.getAbsolutePath());
					bw.newLine();
					bw.write("PERSONS;" + persons.getAbsolutePath() + "\n");
					bw.close();
					fOutput.close();
				}
			} 
			catch (Exception e) 
			{
				//Ignore the exceptions
			}
			JOptionPane.showMessageDialog(null, "Restart Application for changes to take effect.\nThanks!");	
			System.exit(0);
		}
	}

	public class CreateRoute implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame f = new JFrame("Create A Route");
            
            
            RouteMaker dual = new RouteMaker(manager,f);
            
            String[] stopNames= new String[manager.getStops().size()];
            
            
            
            for(Stop s: manager.getStops()){
            	stopNames[manager.getStops().indexOf(s)] = s.getName();
            }
            try{
            dual.addSourceElements(stopNames);
        	}
            catch(NullPointerException ex){
        		
        		
        		
        	}
           
            f.getContentPane().add(dual, BorderLayout.CENTER);
            f.setSize(493, 360);
            f.setVisible(true);
            f.setResizable(false);
			
		}
		
	}
	public class RouteManagerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		    new RouteManager(manager);
		 
	}
	}
	public class ScanPackageListener implements ActionListener{
		 public void actionPerformed(ActionEvent e)
         {

             ScanPackage scan = new ScanPackage(manager);
             scan.setVisible(true);
         }
		
	}
	public class PrintRouteListener implements ActionListener{
		
		  public void actionPerformed(ActionEvent e)
          {

              RoutePrint route = new RoutePrint(manager);
              route.setVisible(true);
          }
	}
	public class CreateAccountListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			new AddAccount(manager);

			
		}
		
	}
}
