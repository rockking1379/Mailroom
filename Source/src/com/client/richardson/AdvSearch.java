package com.client.richardson;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import com.client.common.*;
import com.client.common.Package;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import com.client.common.DatabaseManager;
import com.client.richardson.DatePicker;

public class AdvSearch extends JFrame
{

	private JPanel contentPane;
	private JTextField FirstNameField;
	private JTextField LastNameField;
	private JTextField BoxNum;
	private JButton btnSearch;
	private JLabel lblTracking;
	private JTextField trackingField;
	private String sDate = "";
	private String eDate = "";
	private String sYear;
	private String eYear;
	private boolean date = true;
	private JTextField StartField;
	private JTextField EndField;
	JComboBox StopBox;
	JCheckBox chckbxPickedUp;
	JCheckBox chckbxDelivered;
	JCheckBox chckbxSearchByDelivered;

	class JTextFieldLimit extends PlainDocument
	{
		private int limit;

		JTextFieldLimit(int limit)
		{
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper)
		{
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException
		{
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit)
			{
				super.insertString(offset, str, attr);
			}
		}
	}

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
					AdvSearch frame = new AdvSearch(new Table(null));
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
	Table table;
	DatabaseManager manager;

	public AdvSearch(Table table)
	{
		this.table = table;
		manager = table.manager;

		setResizable(false);
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/image/download.jpg"));
		setIconImage(icon.getImage());
		// setIconImage(Toolkit.getDefaultToolkit().getImage("src\\compass.jpg"));
		setTitle("Advanced Search");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 166);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setBounds(10, 36, 66, 14);
		contentPane.add(lblFirstName);

		FirstNameField = new JTextField();
		FirstNameField.setBounds(79, 33, 118, 20);
		contentPane.add(FirstNameField);
		FirstNameField.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setBounds(222, 36, 66, 14);
		contentPane.add(lblLastName);

		LastNameField = new JTextField();
		LastNameField.setBounds(298, 33, 136, 20);
		contentPane.add(LastNameField);
		LastNameField.setColumns(10);

		JLabel lblDates = new JLabel("Dates:");
		lblDates.setForeground(new Color(255, 255, 255));
		lblDates.setBounds(10, 85, 46, 14);
		contentPane.add(lblDates);

		JLabel lblTo = new JLabel("to");
		lblTo.setForeground(new Color(255, 255, 255));
		lblTo.setBounds(177, 85, 23, 14);
		contentPane.add(lblTo);

		JLabel lblStop = new JLabel("Stop:");
		lblStop.setForeground(new Color(255, 255, 255));
		lblStop.setBounds(10, 61, 46, 14);
		contentPane.add(lblStop);

		JLabel lblBox = new JLabel("Box #:");
		lblBox.setForeground(new Color(255, 255, 255));
		lblBox.setBounds(196, 61, 46, 14);
		contentPane.add(lblBox);

		BoxNum = new JTextField();
		BoxNum.setBounds(239, 58, 86, 20);
		contentPane.add(BoxNum);
		BoxNum.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(145, 110, 89, 23);
		btnSearch.addActionListener(new SearchListener());
		contentPane.add(btnSearch);

		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!StartField.getText().equals("")
						&& !EndField.getText().equals(""))
				{
					try
					{
						date = true;
						sYear = StartField.getText();
						sYear = sYear.replace("-", "");
						System.out.println(sYear);
						eYear = EndField.getText();
						eYear = eYear.replace("-", "");
						System.out.println(eYear);
						int s = Integer.parseInt(sYear);
						int ey = Integer.parseInt(eYear);
						boolean i = s <= ey;
						dateCheck();

						if (i == false || date == false)
						{
							Component frame = null;
							JOptionPane
									.showMessageDialog(
											frame,
											"You have entered a date start date after your entered end date!",
											"Year error",
											JOptionPane.ERROR_MESSAGE);
						}
					}
					catch (Exception ex)
					{
						Component frame = null;
						JOptionPane.showMessageDialog(frame,
								"You have entered an incorrect year!",
								"Year error", JOptionPane.ERROR_MESSAGE);

					}

				}
			}
		});

		lblTracking = new JLabel("Tracking # :");
		lblTracking.setForeground(Color.WHITE);
		lblTracking.setBounds(10, 11, 66, 14);
		contentPane.add(lblTracking);

		trackingField = new JTextField();
		trackingField.setBounds(79, 8, 355, 20);
		contentPane.add(trackingField);
		trackingField.setColumns(10);

		chckbxDelivered = new JCheckBox("Delivered");
		chckbxDelivered.setEnabled(false);
		chckbxDelivered.setForeground(new Color(255, 255, 255));
		chckbxDelivered.setBackground(new Color(0, 102, 0));
		chckbxDelivered.setBounds(374, 104, 85, 23);
		contentPane.add(chckbxDelivered);

		chckbxPickedUp = new JCheckBox("Picked Up");
		chckbxPickedUp.setEnabled(false);
		chckbxPickedUp.setForeground(new Color(255, 255, 255));
		chckbxPickedUp.setBackground(new Color(0, 102, 0));
		chckbxPickedUp.setBounds(374, 81, 83, 23);
		contentPane.add(chckbxPickedUp);

		chckbxPickedUp.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent arg0)
			{
				if (chckbxPickedUp.isSelected() == true
						&& chckbxDelivered.isSelected() == false)
				{
					chckbxDelivered.setSelected(true);
				}

			}

		});

		StopBox = new JComboBox();
		ArrayList<Stop> stops = (ArrayList<Stop>) manager.getStops();
		ArrayList<String> stopNames = new ArrayList<String>();

		System.out.println(stops.size() - 1);
		stopNames.add("All Stops");
		for (Stop s : stops)
		{
			stopNames.add(s.getName());
		}

		Object[] blah = stopNames.toArray();
		Arrays.sort(blah);

		StopBox.setModel(new DefaultComboBoxModel(blah));
		StopBox.setSelectedItem("All Stops");
		StopBox.setBounds(47, 58, 142, 20);
		contentPane.add(StopBox);

		StartField = new JTextField();
		StartField.setBounds(47, 82, 86, 20);
		contentPane.add(StartField);
		StartField.setColumns(10);

		JButton btnDate = new JButton("");
		btnDate.setBounds(135, 80, 32, 23);
		contentPane.add(btnDate);
		Image icon1;
		try
		{
			icon1 = ImageIO.read(getClass().getResource("/image/cal.jpg"));
			btnDate.setIcon(new ImageIcon(icon1));
		}
		catch (IOException e1)
		{

		}
		btnDate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				final JFrame f = new JFrame();
				JPanel p = new JPanel();

				StartField.setText(new DatePicker(f).setPickedDate());
			}
		});

		EndField = new JTextField();
		EndField.setBounds(202, 82, 86, 20);
		contentPane.add(EndField);
		EndField.setColumns(10);

		JButton btnEDate = new JButton("");
		btnEDate.setBounds(293, 81, 32, 23);
		contentPane.add(btnEDate);

		chckbxSearchByDelivered = new JCheckBox("Search By Delivered");
		chckbxSearchByDelivered.setForeground(new Color(255, 255, 255));
		chckbxSearchByDelivered.setBackground(new Color(0, 102, 0));
		chckbxSearchByDelivered.setBounds(331, 57, 159, 23);
		contentPane.add(chckbxSearchByDelivered);
		chckbxSearchByDelivered.addItemListener(new CheckBoxListener());

		if (chckbxSearchByDelivered.isSelected())
		{
			chckbxDelivered.setEnabled(true);
			chckbxPickedUp.setEnabled(true);
		}
		if (!chckbxSearchByDelivered.isSelected())
		{
			chckbxDelivered.setEnabled(false);
			chckbxPickedUp.setEnabled(false);
		}

		try
		{
			icon1 = ImageIO.read(getClass().getResource("/image/cal.jpg"));
			btnEDate.setIcon(new ImageIcon(icon1));

		}
		catch (IOException e1)
		{

		}

		btnEDate.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent ae)
			{
				final JFrame f = new JFrame();

				EndField.setText(new DatePicker(f).setPickedDate());
			}
		});

	}

	public void dateCheck()
	{
		sDate = new StringBuilder().append(sYear.substring(4)).append(
				sYear.substring(0, 2)).append(sYear.substring(2, 4)).toString();
		eDate = new StringBuilder().append(eYear.substring(4)).append(
				eYear.substring(0, 2)).append(eYear.substring(2, 4)).toString();
		int sdate = Integer.parseInt(sDate);
		int edate = Integer.parseInt(eDate);

		System.out.println("\n" + sdate);
		System.out.println(edate);
		if (sdate > edate)
		{

			date = false;
			System.out.println(date);
		}

	}

	public class SearchListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			/*
			if (!StartField.getText().equals("")
					|| !EndField.getText().equals(""))
			{
				try
				{
					sdDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
							.parse(StartField.getText());
					edDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
							.parse(EndField.getText());

					System.out.println(sDate + " " + eDate);

					sDate = StartField.getText();
					eDate = EndField.getText();
				}
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			*/
			ArrayList<Package> results = new ArrayList<Package>();
			String selectedStop = (String) StopBox.getSelectedItem();

			if (!trackingField.getText().equals(""))
			{

				ArrayList<Package> sresults = (ArrayList<Package>) manager
						.searchPackages(trackingField.getText(), 0);

				for (com.client.common.Package p : sresults)
				{
					if (matchesParams(p))
					{
						boolean add = true;
						if(results.size() > 0)
						{
							for(int i = 0; i < results.size(); i++)
							{
								if(results.get(i).getTrackNum().equals(p.getTrackNum()))
								{
									add = false;
								}
							}
						}
						if(add)
						{
							results.add(p);
						}
					}
				}

			}

			if (!FirstNameField.getText().equals(""))
			{

				ArrayList<Package> sresults = (ArrayList<Package>) manager
						.searchPackages(FirstNameField.getText(), 0);

				for (com.client.common.Package p : sresults)
				{
					if (matchesParams(p))
					{
						boolean add = true;
						if(results.size() > 0)
						{
							for(int i = 0; i < results.size(); i++)
							{
								if(results.get(i).getTrackNum().equals(p.getTrackNum()))
								{
									add = false;
								}
							}
						}
						if(add)
						{
							results.add(p);
						}
					}
				}

			}

			if (!LastNameField.getText().equals(""))
			{

				ArrayList<Package> sresults = (ArrayList<Package>) manager
						.searchPackages(LastNameField.getText(), 0);

				for (com.client.common.Package p : sresults)
				{
					if (matchesParams(p))
					{
						boolean add = true;
						if(results.size() > 0)
						{
							for(int i = 0; i < results.size(); i++)
							{
								if(results.get(i).getTrackNum().equals(p.getTrackNum()))
								{
									add = false;
								}
							}
						}
						if(add)
						{
							results.add(p);
						}
					}
				}

			}

			if (!BoxNum.getText().equals(""))
			{

				ArrayList<Package> sresults = (ArrayList<Package>) manager
						.searchPackages(BoxNum.getText(), 0);

				for (com.client.common.Package p : sresults)
				{
					if (matchesParams(p))
					{
						boolean add = true;
						if(results.size() > 0)
						{
							for(int i = 0; i < results.size(); i++)
							{
								if(results.get(i).getTrackNum().equals(p.getTrackNum()))
								{
									add = false;
								}
							}
						}
						if(add)
						{
							results.add(p);
						}
					}
				}

			}

			if (!StartField.getText().equals("")
					&& !EndField.getText().equals(""))
			{
				// String sDate=null;
				// String eDate=null;

				try
				{
					sdDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
							.parse(StartField.getText());
					edDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
							.parse(EndField.getText());

					System.out.println(sDate + " " + eDate);

					sDate = StartField.getText();
					eDate = EndField.getText();
				}
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(sDate + " " + eDate);
				ArrayList<Package> sresults = (ArrayList<Package>) manager
						.findPackage(sDate, eDate);

				for (com.client.common.Package p : sresults)
				{
					if (matchesParams(p))
					{
						boolean add = true;
						if(results.size() > 0)
						{
							for(int i = 0; i < results.size(); i++)
							{
								if(results.get(i).getTrackNum().equals(p.getTrackNum()))
								{
									add = false;
								}
							}
						}
						if(add)
						{
							results.add(p);
						}
					}
				}

			}

			selectedStop = (String) StopBox.getSelectedItem();
			if (!selectedStop.equals("All Stops"))
			{
				ArrayList<Package> sresults = (ArrayList<Package>) manager
						.searchPackages(selectedStop, 0);

				for (com.client.common.Package p : sresults)
				{
					if (matchesParams(p))
					{
						boolean add = true;
						if(results.size() > 0)
						{
							for(int i = 0; i < results.size(); i++)
							{
								if(results.get(i).getTrackNum().equals(p.getTrackNum()))
								{
									add = false;
								}
							}
						}
						if(add)
						{
							results.add(p);
						}
					}
				}

			}

			if(chckbxSearchByDelivered.isSelected())
			{
				ArrayList<Package> sresults = (ArrayList<Package>) manager
						.findPackage(chckbxDelivered.isSelected(), chckbxPickedUp
								.isSelected());
	
				for (com.client.common.Package p : sresults)
				{
					if (matchesParams(p))
					{
						boolean add = true;
						if(results.size() > 0)
						{
							for(int i = 0; i < results.size(); i++)
							{
								if(results.get(i).getTrackNum().equals(p.getTrackNum()))
								{
									add = false;
								}
							}
						}
						if(add)
						{
							results.add(p);
						}
					}
				}
			}

			table.setSearchResults(results);
			dispose();
		}

	}

	Date sdDate;
	Date edDate;

	private boolean matchesParams(Package p)
	{

		boolean matches = true;

		String person = p.getFName().toUpperCase();
		String entered = FirstNameField.getText().toUpperCase();

		if (!FirstNameField.getText().equals("")
				&& !person.equals(entered.toUpperCase()))
		{
			System.out.println(person.equals(entered));
			matches = false;
		}
		person = p.getLName().toUpperCase();
		entered = LastNameField.getText().toUpperCase();
		if (!LastNameField.getText().equals("") && !person.equals(entered))
		{
			matches = false;
		}
		if (!BoxNum.getText().equals("")
				&& !p.getBoxNum().toUpperCase().equals(
						BoxNum.getText().toUpperCase()))
		{
			matches = false;
		}

		String stop = (String) StopBox.getSelectedItem();

		if (!stop.equals("All Stops") && !p.getStop().equals(stop))
		{
			matches = false;
		}

		if (!StartField.getText().equals("") && !EndField.getText().equals(""))
		{
			try
			{
				System.out.println(p.getDate());

				Date pDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
						.parse(p.getDate());
				System.out
						.println(((sdDate.compareTo(pDate) > 0) + " " + (edDate
								.compareTo(pDate) < 0)));
				if (sdDate.compareTo(pDate) > 0 && edDate.compareTo(pDate) < 0)
				{

					matches = false;
				}
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (chckbxSearchByDelivered.isSelected())
		{

			if (p.getPickedUp() != chckbxPickedUp.isSelected()
					&& p.getDelivered() != chckbxDelivered.isSelected())
			{
				matches = false;
			}

		}

		return matches;

	}

	private class CheckBoxListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{

			if (chckbxSearchByDelivered.isSelected())
			{
				chckbxDelivered.setEnabled(true);
				chckbxPickedUp.setEnabled(true);
			}
			if (!chckbxSearchByDelivered.isSelected())
			{
				chckbxDelivered.setEnabled(false);
				chckbxPickedUp.setEnabled(false);
			}

		}
	}

}
