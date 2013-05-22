package com.client.richardson;
//<<<<<<< HEAD
// 5-17-2013
//Testing new Project
import java.awt.BorderLayout;
import com.client.common.*;
//=======

import java.awt.BorderLayout;
//>>>>>>> origin/Nick
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
//<<<<<<< HEAD
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JOptionPane;
//=======
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
//>>>>>>> origin/Nick
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
//<<<<<<< HEAD

import com.client.common.*;

import java.awt.Dialog.ModalExclusionType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class OpenScreen extends JFrame {
	DatabaseManager manager;
//=======




//>>>>>>> origin/Nick
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenScreen frame = new OpenScreen(true);
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
//<<<<<<< HEAD

	
	public OpenScreen(boolean admin) {
		
				manager = new DatabaseManager();
				
				manager.setDatabase("Mailroom_db");
				manager.setFile("People.txt");
				loadSettings();
				manager.setup();
				manager.loadPackages(true,null);
				



		

		setTitle("My Mail Room");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/Untitled.jpg"));
		setIconImage(icon.getImage());
		//setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Untitled.jpg"));
		setBackground(new Color(0, 102, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 447);
=======
	public OpenScreen(boolean admin) {
		setVisible(true);
		setTitle("Adams State Mail Room System");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/Untitled.jpg"));
		setIconImage(icon.getImage());
		setBackground(new Color(0, 102, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 447);
>>>>>>> origin/Nick
		
		JMenuBar menuBar = new JMenuBar();
		if(admin){
		setJMenuBar(menuBar);
		}
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmCreateNewAccount = new JMenuItem("Create New Account");
<<<<<<< HEAD
		mntmCreateNewAccount.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddAccount();
=======
		
		mntmCreateNewAccount.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddAccount a = new AddAccount();
>>>>>>> origin/Nick
				
			}
			
		});
<<<<<<< HEAD
		mnFile.add(mntmCreateNewAccount);
		
		JMenuItem mntmCreateNewRoute = new JMenuItem("Create New Route");
		mnFile.add(mntmCreateNewRoute);
		
		mntmCreateNewRoute.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e)
            {

            	RouteMaker route = new RouteMaker(manager);
                route.setVisible(true);

            	JFrame f = new JFrame("Create A Route");
                
                
                RouteMaker dual = new RouteMaker(manager);
                
                String[] stopNames= new String[manager.getStops().size()];
                
                for(Stop s: manager.getStops()){
                	stopNames[manager.getStops().indexOf(s)] = s.getName();
                }
                
                dual.addSourceElements(stopNames);

               
                f.getContentPane().add(dual, BorderLayout.CENTER);
                f.setSize(493, 360);
                f.setVisible(true);
                f.setResizable(false);

            }
        });
		
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		mntmClose.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e)
            {
            	dispose();
            }
        });
=======
		JMenuItem crtRt = new JMenuItem("Create Route");
		
		crtRt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				 JFrame f = new JFrame("Dual List Box Tester");
				    
				    RouteMaker dual = new RouteMaker();
				    dual.addSourceElements(new String[] {  "AAO", "Academic Affairs", "Admissions\t", "AITC", "Alumni/Foundation", "Art", "AS&F", "Bookstore", "Business Office", "Communications", "Community Partnership", "Computing Services",
				    		"Counseling & Career", "Counselor Education", "EEO", "English/ Communication", "Enrollment", "Extended Studies", "Facilities Office", "Facilities Warehouse", "Finance/ Administration", "Financial Aid", 
				    		"Gingerbread House", "Graduate School", "HGPPSL", "Hold for Pickup", "Housing", "HPPE", "Human Resources", "Institutional Research", "Library", "Museum", "Music", "Nursing", "One Stop", "Payroll", "Plachy", 
				    		"Police Department", "President", "Print Shop", "Purchasing", "Radio Station", "Records", "REX", "School of Business", "SMT", "SODEXO", "Student Affairs", "Student Life", "SUB Office", "SUB Mailroom", 
				    		"SVP Enrollment Manager", "Teacher Education", "Theatre", "Title V", "Upward Bound" });

				   
				    f.getContentPane().add(dual, BorderLayout.CENTER);
				    f.setSize(493, 360);
				    f.setVisible(true);
				    f.setResizable(false);				
			}
		});
		
		mnFile.add(mntmCreateNewAccount);
		mnFile.add(crtRt);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
				
			}
			
		});
		mnFile.add(mntmClose);
	
>>>>>>> origin/Nick
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnScanPackage = new JButton("Scan Package");
		btnScanPackage.setToolTipText("Scan in a new package");
		btnScanPackage.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnScanPackage.setBounds(20, 22, 121, 80);
		contentPane.add(btnScanPackage);
		
	    btnScanPackage.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
<<<<<<< HEAD
                ScanPackage scan = new ScanPackage(manager);
=======
                ScanPackage scan = new ScanPackage();
>>>>>>> origin/Nick
                scan.setVisible(true);
            }
        });
		
		JButton btnPrintRoute = new JButton("Print Route");
		btnPrintRoute.setToolTipText("Print a package route");
		btnPrintRoute.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrintRoute.setBounds(20, 113, 121, 80);
		contentPane.add(btnPrintRoute);
	        
		 btnPrintRoute.addActionListener(new ActionListener() {
	    	 
	            public void actionPerformed(ActionEvent e)
	            {
<<<<<<< HEAD
	                RoutePrint route = new RoutePrint(manager);
=======
	                RoutePrint route = new RoutePrint();
>>>>>>> origin/Nick
	                route.setVisible(true);
	            }
	        });
		
	         Table newContentPane = new Table();
<<<<<<< HEAD
	         newContentPane.setBounds(151, 11, 560, 366);
=======
	         newContentPane.setBounds(151, 11, 686, 366);
>>>>>>> origin/Nick
	         contentPane.add(newContentPane);
	         newContentPane.setOpaque(true);
	         
	         JButton btnSearch = new JButton("Search");
	         btnSearch.setToolTipText("Search for a package");
	         btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
	         btnSearch.setBounds(20, 204, 121, 80);
	         contentPane.add(btnSearch);
	         
<<<<<<< HEAD
	         JButton btnRefesh = new JButton("Refesh");
	         
	         btnRefesh.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					//we need search parameters for the class table
					
				}
	        	 
	         });
	         btnRefesh.setFont(new Font("Tahoma", Font.BOLD, 11));
	         btnRefesh.setBounds(20, 354, 121, 23);
	         contentPane.add(btnRefesh);
=======
	         JButton btnRefresh = new JButton("Refresh");
	         btnRefresh.setBounds(20, 351, 121, 23);
	         contentPane.add(btnRefresh);
	         
	         JButton btnPrint = new JButton("Print");
	         btnPrint.setBounds(20, 317, 121, 23);
	         contentPane.add(btnPrint);
>>>>>>> origin/Nick
	         
	         btnSearch.addActionListener(new ActionListener() {
		    	 
	             public void actionPerformed(ActionEvent e)
	             {
	                 AdvSearch search = new AdvSearch();
	                 search.setVisible(true);
	             }
	         });
<<<<<<< HEAD
	          
	         setVisible(true); 
	}
	public void loadSettings(){
		DatabaseManager dbManager = new DatabaseManager();
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
					while(settingLine.charAt(index) != ':')
					{
						setting += settingLine.charAt(index);
						index++;
					}
					if(setting.toUpperCase().equals("DATABASE"))
					{
						//Read in Database configuration
						String temp = "";
						for(int i = index; i < settingLine.length(); i++)
						{
							temp += settingLine.charAt(i);
						}
						
						dbManager.setDatabase(temp);
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
							
							dbManager.setFile(temp);
						}
					}
				}
				
				br.close();
				
			}
			catch(Exception e)
			{
				//Do nothing
			}
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
					bw.write("DATABASE:" + database.getAbsolutePath() + "\n");
					bw.write("PERSONS:" + persons.getAbsolutePath() + "\n");
					bw.close();
					fOutput.close();
				}
			} 
			catch (Exception e) 
			{
				//Ignore the exceptions
			}
			JOptionPane.showMessageDialog(null, "Restart Application for changes to take effect.\nThanks!");			
		}
	}	
					
	
	

	
=======
	}
>>>>>>> origin/Nick
}
