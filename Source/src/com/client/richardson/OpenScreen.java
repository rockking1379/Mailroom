package com.client.richardson;

//<<<<<<< HEAD
// 5-17-2013
//Testing new Project
import java.awt.BorderLayout;

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
import com.client.common.Package;

import java.awt.Dialog.ModalExclusionType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JLabel;

public class OpenScreen extends JFrame
{
	DatabaseManager manager;
	String loggedIn;
	Table displayTable;

	// >>>>>>> origin/Nick
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					OpenScreen frame = new OpenScreen(true,
							new DatabaseManager(), "Someone");
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	// <<<<<<< HEAD

	public OpenScreen(boolean admin, DatabaseManager manager, String loggedIn)
	{
		setResizable(false);

		this.manager = manager;
		this.loggedIn = loggedIn;

		manager.loadPackages(true, null);

		setVisible(true);
		setTitle("Adams State Mail Room System");
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/image/Untitled.jpg"));
		setIconImage(icon.getImage());
		setBackground(new Color(0, 102, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 469);

		JMenuBar menuBar = new JMenuBar();
		if (admin)
		{
			setJMenuBar(menuBar);
		}

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmCreateNewAccount = new JMenuItem("Account Manager");

		mntmCreateNewAccount.addActionListener(new CreateAccountListener());

		mnFile.add(mntmCreateNewAccount);

		JMenuItem mntmCreateNewRoute = new JMenuItem("Create New Route");
		mnFile.add(mntmCreateNewRoute);

		mntmCreateNewRoute.addActionListener(new CreateRoute());

		JMenuItem deleteStop = new JMenuItem("Delete Stop");
		mnFile.add(deleteStop);

		deleteStop.addActionListener(new DeleteStop());

		JMenuItem crtRt = new JMenuItem("Manage Routes");

		crtRt.addActionListener(new RouteManagerListener());

		mnFile.add(mntmCreateNewAccount);
		mnFile.add(crtRt);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new logoutListener());
		mnFile.add(mntmLogout);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(1);

			}

		});
		mnFile.add(mntmClose);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnScanPackage = new JButton("Scan Package");
		btnScanPackage.setToolTipText("Scan in a new package");
		btnScanPackage.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnScanPackage.setBounds(20, 22, 121, 80);
		contentPane.add(btnScanPackage);

		btnScanPackage.addActionListener(new ScanPackageListener());

		JButton btnPrintRoute = new JButton("Print Route");
		btnPrintRoute.setToolTipText("Print a package route");
		btnPrintRoute.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrintRoute.setBounds(20, 113, 121, 80);
		contentPane.add(btnPrintRoute);

		btnPrintRoute.addActionListener(new PrintRouteListener());

		displayTable = new Table(manager);

		displayTable.setBounds(151, 11, 916, 366);

		contentPane.add(displayTable);
		displayTable.setOpaque(true);

		JButton btnSearch = new JButton("Search");
		btnSearch.setToolTipText("Search for a package");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch.setBounds(20, 204, 121, 80);
		contentPane.add(btnSearch);
		JButton btnRefresh = new JButton("Refesh");

		btnRefresh.addActionListener(new RefreshListener());
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefresh.setBounds(20, 354, 121, 23);
		contentPane.add(btnRefresh);

		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(20, 317, 121, 23);
		// contentPane.add(btnPrint);

		JLabel lblLoggedInAs = new JLabel("Logged in as: " + loggedIn);
		lblLoggedInAs.setForeground(Color.WHITE);
		lblLoggedInAs.setBounds(378, 384, 247, 14);
		contentPane.add(lblLoggedInAs);

		btnSearch.addActionListener(new SearchListener());

		setVisible(true);
	}

	public void loadSettings()
	{
		manager = new DatabaseManager();
		File settings = new File("./properties.prop");
		if (settings.exists())
		{
			try
			{
				FileInputStream fStream = new FileInputStream(settings);
				DataInputStream dis = new DataInputStream(fStream);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						dis));

				String settingLine;
				while ((settingLine = br.readLine()) != null)
				{
					// Read Settings
					String setting = "";
					int index = 0;
					while (settingLine.charAt(index) != ';')
					{
						setting += settingLine.charAt(index);
						index++;
					}
					index++;
					if (setting.toUpperCase().equals("DATABASE"))
					{
						// Read in Database configuration
						String temp = "";
						for (int i = index; i < settingLine.length(); i++)
						{
							temp += settingLine.charAt(i);
						}
						manager.setDatabase(temp);
					}
					else
					{
						if (setting.toUpperCase().equals("PERSONS"))
						{
							String temp = "";

							for (int i = index; i < settingLine.length(); i++)
							{
								temp += settingLine.charAt(i);
							}
							manager.setFile(temp);
						}
					}
				}
				br.close();
			}
			catch (Exception e)
			{
				// Do nothing
			}
			manager.setup();
			manager.loadPackages(true, null);

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
				if (settings.createNewFile())
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
				// Ignore the exceptions
			}
			JOptionPane.showMessageDialog(null,
					"Restart Application for changes to take effect.\nThanks!");
			System.exit(0);
		}
	}

	public class CreateRoute implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFrame f = new JFrame("Create A Route");

			RouteMaker dual = new RouteMaker(manager, f, loggedIn);

			String[] stopNames = new String[manager.getStops().size()];

			for (Stop s : manager.getStops())
			{
				stopNames[manager.getStops().indexOf(s)] = s.getName();
			}
			try
			{
				dual.addSourceElements(stopNames);
			}
			catch (NullPointerException ex)
			{

			}

			f.getContentPane().add(dual, BorderLayout.CENTER);
			f.setSize(493, 360);
			f.setVisible(true);
			f.setResizable(false);

		}

	}

	public class RouteManagerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

			new RouteManager(manager, loggedIn);

		}
	}

	public class ScanPackageListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

			ScanPackage scan = new ScanPackage(manager, loggedIn);
			scan.setVisible(true);
		}

	}

	public class PrintRouteListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{

			RoutePrint route = new RoutePrint(manager);
			route.setVisible(true);
		}
	}

	public class CreateAccountListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			new AddAccount(manager);

		}

	}

	public class SearchListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			AdvSearch search = new AdvSearch(displayTable);
			search.setVisible(true);
		}
	}

	public class RefreshListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{

			displayTable.updateTabel();

		}

	}

	public class DeleteStop implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			new DelStop(manager).setVisible(true);

		}

	}
	
	private class logoutListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			manager.closeUp();
			login l = new login();
			l.setVisible(true);
			dispose();
		}
	}
}
