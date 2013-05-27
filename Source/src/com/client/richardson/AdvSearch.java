package com.client.richardson;
import java.awt.BorderLayout;
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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.client.common.DatabaseManager;
import com.client.richardson.DatePicker;



public class AdvSearch extends JFrame {

	private JPanel contentPane;
	private JTextField FirstNameField;
	private JTextField LastNameField;
	private JTextField BoxNum;
	private JButton btnSearch;
	private JLabel lblTracking;
	private JTextField trackingField;
	private JComboBox comboBox;
	private JPanel SearchPanel;
	private String sDate="";
	private String eDate="";
	private String sYear;
	private String eYear;
    private boolean date = true;
    private JTextField StartField;
    private JTextField EndField;

	
	
	

	class JTextFieldLimit extends PlainDocument {
		  private int limit;
		  JTextFieldLimit(int limit) {
		    super();
		    this.limit = limit;
		  }

		  JTextFieldLimit(int limit, boolean upper) {
		    super();
		    this.limit = limit;
		  }

		  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		    if (str == null)
		      return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvSearch frame = new AdvSearch(new Table(null));
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
	Table table;
	DatabaseManager manager;
	public AdvSearch(Table table) {
		this.table=table;
		manager = table.manager;
		
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/download.jpg"));
		setIconImage(icon.getImage());
		//setIconImage(Toolkit.getDefaultToolkit().getImage("src\\compass.jpg"));
		setTitle("Advanced Search");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 167);
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
		btnSearch.setBounds(345, 105, 89, 23);
		btnSearch.addActionListener(new SearchListener());
		contentPane.add(btnSearch);
		
		
		
		
	    btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {               
            	
                try{
                date=true;
                sYear = StartField.getText();
                sYear = sYear.replace("-", "");
                System.out.println(sYear);
                eYear = EndField.getText();
                eYear = eYear.replace("-", "");
                System.out.println(eYear);
        		int s = Integer.parseInt(sYear);
        		int ey = Integer.parseInt(eYear);
        		boolean i = s<=ey;
        		dateCheck();
        		if(i==false||date==false){
        			Component frame = null;
					JOptionPane.showMessageDialog(frame,
                		    "You have entered a date start date after your entered end date!",
                		    "Year error",
                		    JOptionPane.ERROR_MESSAGE);
					setBounds(100, 100, 460, 167);
					SearchPanel.setVisible(false);
        		}else{
        		setBounds(100, 100, 460, 225);
                SearchPanel.setVisible(true);
        		}
                }catch(Exception ex){
                	Component frame = null;
					JOptionPane.showMessageDialog(frame,
                		    "You have entered an incorrect year!",
                		    "Year error",
                		    JOptionPane.ERROR_MESSAGE);
					SearchPanel.setVisible(false);
					setBounds(100, 100, 460, 167);
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
		
		JCheckBox chckbxDelivered = new JCheckBox("Delivered");
		chckbxDelivered.setForeground(new Color(255, 255, 255));
		chckbxDelivered.setBackground(new Color(0, 102, 0));
		chckbxDelivered.setBounds(376, 81, 85, 23);
		contentPane.add(chckbxDelivered);
		
		JCheckBox chckbxPickedUp = new JCheckBox("Picked Up");
		chckbxPickedUp.setForeground(new Color(255, 255, 255));
		chckbxPickedUp.setBackground(new Color(0, 102, 0));
		chckbxPickedUp.setBounds(376, 60, 83, 23);
		contentPane.add(chckbxPickedUp);
		
		JComboBox StopBox = new JComboBox();
		StopBox.setModel(new DefaultComboBoxModel(new String[] {"AAO", "Academic Affairs", "Admissions\t", "AITC", "Alumni/Foundation", "Art", "AS&F", "Bookstore", "Business Office", "Communications", "Community Partnership", "Computing Services",
	    		"Counseling & Career", "Counselor Education", "EEO", "English/ Communication", "Enrollment", "Extended Studies", "Facilities Office", "Facilities Warehouse", "Finance/ Administration", "Financial Aid", 
	    		"Gingerbread House", "Graduate School", "HGPPSL", "Hold for Pickup", "Housing", "HPPE", "Human Resources", "Institutional Research", "Library", "Museum", "Music", "Nursing", "One Stop", "Payroll", "Plachy", 
	    		"Police Department", "President", "Print Shop", "Purchasing", "Radio Station", "Records", "REX", "School of Business", "SMT", "SODEXO", "Student Affairs", "Student Life", "SUB Office", "SUB Mailroom", 
	    		"SVP Enrollment Manager", "Teacher Education", "Theatre", "Title V", "Upward Bound"}));
		
		StopBox.setBounds(47, 58, 142, 20);
		contentPane.add(StopBox);
		
		
		SearchPanel = new JPanel();
		SearchPanel.setBackground(new Color(0, 102, 0));
		SearchPanel.setBounds(0, 105, 446, 77);
		contentPane.add(SearchPanel);
		SearchPanel.setLayout(null);
		SearchPanel.setVisible(false);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 0, 209, 20);
		SearchPanel.add(comboBox);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 31, 46, 14);
		SearchPanel.add(lblName);
		lblName.setForeground(Color.WHITE);
		
		JLabel lblFirst = new JLabel("First");
		lblFirst.setBounds(97, 31, 60, 14);
		SearchPanel.add(lblFirst);
		lblFirst.setForeground(Color.WHITE);
		
		JLabel lblLast = new JLabel("Last");
		lblLast.setBounds(47, 31, 46, 14);
		SearchPanel.add(lblLast);
		lblLast.setForeground(Color.WHITE);
		
		JLabel lblAtStop = new JLabel("Stop:");
		lblAtStop.setBounds(196, 31, 46, 14);
		SearchPanel.add(lblAtStop);
		lblAtStop.setForeground(Color.WHITE);
		
		JLabel lblAtThisStop = new JLabel("At This Stop");
		lblAtThisStop.setBounds(231, 31, 104, 14);
		SearchPanel.add(lblAtThisStop);
		lblAtThisStop.setForeground(Color.WHITE);
		
		JLabel lblTrackingSearch = new JLabel("Tracking # :");
		lblTrackingSearch.setBounds(10, 45, 66, 14);
		SearchPanel.add(lblTrackingSearch);
		lblTrackingSearch.setForeground(Color.WHITE);
		
		JLabel lblTrackingnumber = new JLabel("TrackingNumber");
		lblTrackingnumber.setBounds(84, 45, 176, 14);
		SearchPanel.add(lblTrackingnumber);
		lblTrackingnumber.setForeground(Color.WHITE);
		
		JLabel lblBoxSearch = new JLabel("Box #:");
		lblBoxSearch.setBounds(318, 31, 46, 14);
		SearchPanel.add(lblBoxSearch);
		lblBoxSearch.setForeground(Color.WHITE);
		
		JLabel lblBoxnum = new JLabel("BoxNum");
		lblBoxnum.setBounds(355, 31, 52, 14);
		SearchPanel.add(lblBoxnum);
		lblBoxnum.setForeground(Color.WHITE);
		
		JLabel lblDeliveredDate = new JLabel("Delivered Date:");
		lblDeliveredDate.setBounds(260, 60, 86, 14);
		SearchPanel.add(lblDeliveredDate);
		lblDeliveredDate.setForeground(Color.WHITE);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(351, 60, 95, 14);
		SearchPanel.add(lblDate);
		lblDate.setForeground(Color.WHITE);
		
		JLabel lblScanDate = new JLabel("Scan Date:");
		lblScanDate.setBounds(279, 45, 78, 14);
		SearchPanel.add(lblScanDate);
		lblScanDate.setForeground(Color.WHITE);
		
		JLabel lblScanDate_1 = new JLabel("Scan Date");
		lblScanDate_1.setBounds(345, 45, 89, 14);
		SearchPanel.add(lblScanDate_1);
		lblScanDate_1.setForeground(Color.WHITE);
		
		JLabel lblDelivered = new JLabel("Delivered:");
		lblDelivered.setBounds(10, 60, 66, 14);
		SearchPanel.add(lblDelivered);
		lblDelivered.setForeground(Color.WHITE);
		
		JLabel lblDeliveryStatus = new JLabel("Delivery Status");
		lblDeliveryStatus.setBounds(79, 60, 96, 14);
		SearchPanel.add(lblDeliveryStatus);
		lblDeliveryStatus.setForeground(Color.WHITE);
		
		StartField = new JTextField();
		StartField.setBounds(47, 82, 86, 20);
		contentPane.add(StartField);
		StartField.setColumns(10);
		
		JButton btnDate = new JButton("");
		btnDate.setBounds(135, 80, 32, 23);
		contentPane.add(btnDate);
		Image icon1;
		try {
			icon1 = ImageIO.read(getClass().getResource("/image/cal.jpg"));
			btnDate.setIcon(new ImageIcon(icon1));
		} catch (IOException e1) {
			
		}
		btnDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
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
		try {
			icon1 = ImageIO.read(getClass().getResource("/image/cal.jpg"));
			btnEDate.setIcon(new ImageIcon(icon1));
		} catch (IOException e1) {
			
		}
		
		btnEDate.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent ae) {
            	 final JFrame f = new JFrame();
            	 
                    EndField.setText(new DatePicker(f).setPickedDate());
            }
    });
	
	}
	
	public void dateCheck(){
		sDate=new StringBuilder().append(sYear.substring(4 )).append(sYear.substring(0, 2)).append(sYear.substring(2, 4)).toString();
		eDate=new StringBuilder().append(eYear.substring(4)).append(eYear.substring(0, 2)).append(eYear.substring(2, 4)).toString();
		int sdate = Integer.parseInt(sDate);
		int edate = Integer.parseInt(eDate);
		
		System.out.println("\n" +sdate);
		System.out.println(edate);
		if(sdate>edate){
			
			date=false;
			System.out.println(date);
		}
		
	}
	public class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ArrayList<Package> results = new ArrayList<Package>();
			if(!trackingField.getText().equals("")){
				
				ArrayList<Package> sresults =(ArrayList<Package>) manager.searchPackages(trackingField.getText(),0);
				
				
				for(com.client.common.Package p: sresults){
					
					if(results.size()!=0){
						for(Package pa: results){
							if(!pa.getTrackNum().equals(p.getTrackNum())){
								results.add(p);
							}
						}
					}
					else{
						results.add(p);
					}
				}
				
			}
			
			
			if(!FirstNameField.getText().equals("")){
				
				ArrayList<Package> sresults =(ArrayList<Package>) manager.searchPackages(FirstNameField.getText(),0);
				
				for(com.client.common.Package p: sresults){
					
					if(results.size()!=0){
						for(Package pa: results){
							if(!pa.getTrackNum().equals(p.getTrackNum())){
								results.add(p);
							}
						}
					}
					else{
						results.add(p);
					}
				}
				
			}
			
			if(!LastNameField.getText().equals("")){
				
				ArrayList<Package> sresults =(ArrayList<Package>) manager.searchPackages(LastNameField.getText(),0);
				
				for(com.client.common.Package p: sresults){
					
					if(results.size()!=0){
						for(Package pa: results){
							if(!pa.getTrackNum().equals(p.getTrackNum())){
								results.add(p);
							}
						}
					}
					else{
						results.add(p);
					}
				}
				
			}
			
			if(!BoxNum.getText().equals("")){
				
				ArrayList<Package> sresults =(ArrayList<Package>) manager.searchPackages(BoxNum.getText(),0);
				
				for(com.client.common.Package p: sresults){
					
					if(results.size()!=0){
						for(Package pa: results){
							if(!pa.getTrackNum().equals(p.getTrackNum())){
								results.add(p);
							}
						}
					}
					else{
						results.add(p);
					}
				}
				
			}
			
			
			if(!StartField.getText().equals("")&&!EndField.getText().equals("")){
				String sDate=null;
				String eDate=null;
				
				try {
					Date sdDate = new SimpleDateFormat("MM-dd-yyyy",Locale.ENGLISH).parse(StartField.getText());
					Date edDate = new SimpleDateFormat("MM-dd-yyyy",Locale.ENGLISH).parse(EndField.getText());
					
					 sDate = DateFormat.getDateInstance(DateFormat.SHORT).format(sdDate);
					 eDate=DateFormat.getDateInstance(DateFormat.SHORT).format(edDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(sDate+" "+eDate);
				ArrayList<Package> sresults =(ArrayList<Package>) manager.findPackage(sDate,eDate);
				
				for(com.client.common.Package p: sresults){
					
					if(results.size()!=0){
						for(Package pa: results){
							if(!pa.getTrackNum().equals(p.getTrackNum())){
								results.add(p);
							}
						}
					}
					else{
						results.add(p);
					}
				}
				
			}
			
			table.setSearchResults(results);
			dispose();
		}
		
	}
}
