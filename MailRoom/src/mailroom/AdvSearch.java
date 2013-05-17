package mailroom;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class AdvSearch extends JFrame {

	private JPanel contentPane;
	private JTextField FirstNameField;
	private JTextField LastNameField;
	private JTextField StopField;
	private JTextField BoxNum;
	private JButton btnSearch;
	private JTextField StartYear;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField EndYear;
	private JLabel lblTracking;
	private JTextField trackingField;
	private JComboBox StartMonth;
	private JComboBox StartDay;
	private JComboBox EndMonth;
	private JComboBox EndDay;
	private JComboBox comboBox;
	private JPanel SearchPanel;
	private String sMonth="01";
	private String eMonth="01";
	private String sDay="01";
	private String eDay="01";
	private String sDate="";
	private String eDate="";
	private String sYear="2000";
    private String eYear="4000";
    private boolean date = true;

	
	
	

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
					AdvSearch frame = new AdvSearch();
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
	public AdvSearch() {
		
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/download.jpg"));
		setIconImage(icon.getImage());
		//setIconImage(Toolkit.getDefaultToolkit().getImage("src\\compass.jpg"));
		setTitle("Advanced Search");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 167);
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
		lblTo.setBounds(196, 86, 23, 14);
		contentPane.add(lblTo);
		
		JLabel label = new JLabel("/");
		label.setForeground(Color.WHITE);
		label.setBounds(90, 85, 17, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(140, 85, 17, 14);
		contentPane.add(label_1);
		
		StartYear = new JTextField();
		StartYear.setBounds(150, 82, 39, 20);
		contentPane.add(StartYear);
		
		label_2 = new JLabel("/");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(254, 85, 17, 14);
		contentPane.add(label_2);
		
		label_3 = new JLabel("/");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(308, 85, 17, 14);
		contentPane.add(label_3);
		
		EndYear = new JTextField();
		EndYear.setBounds(318, 82, 39, 20);
		contentPane.add(EndYear);
		
		StartYear.setDocument(new JTextFieldLimit(4));
	         
		EndYear.setDocument(new JTextFieldLimit(4));
		
		
		
		
		
		JLabel lblStop = new JLabel("Stop:");
		lblStop.setForeground(new Color(255, 255, 255));
		lblStop.setBounds(10, 61, 46, 14);
		contentPane.add(lblStop);
		
		StopField = new JTextField();
		StopField.setBounds(74, 58, 101, 20);
		contentPane.add(StopField);
		StopField.setColumns(10);
		
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
		contentPane.add(btnSearch);
		
		
		
		
	    btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {               
            	
                try{
                sYear = StartYear.getText();
                eYear = EndYear.getText();
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
		chckbxDelivered.setBounds(361, 82, 85, 23);
		contentPane.add(chckbxDelivered);
		
		JCheckBox chckbxPickedUp = new JCheckBox("Picked Up");
		chckbxPickedUp.setForeground(new Color(255, 255, 255));
		chckbxPickedUp.setBackground(new Color(0, 102, 0));
		chckbxPickedUp.setBounds(361, 58, 83, 23);
		contentPane.add(chckbxPickedUp);
		
		StartMonth = new JComboBox();
		StartMonth.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"}));
		StartMonth.setBounds(47, 82, 40, 20);
		contentPane.add(StartMonth);

		StartDay = new JComboBox();
		StartDay.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",}));
		StartDay.setBounds(98, 82, 40, 20);
		contentPane.add(StartDay);
		
		//This is how to get items out of comboBox
		StartMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {	
				 Object month = StartMonth.getSelectedItem();
				 String tsMonth = month.toString();			       
			        long i = Integer.parseInt(tsMonth);
			        sMonth= tsMonth.format("%02d",i);
			        System.out.println(i);
				if(i==2)
				StartDay.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29",}));
				else{
				StartDay.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",}));
				}
				}
        });
	
		StartDay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				 Object info = StartDay.getSelectedItem();
				  String tsDay = info.toString();			       
			        long i = Integer.parseInt(tsDay);
			        sDay= tsDay.format("%02d",i);
			        System.out.println(i);		       
				} });
		
		
		
		
		EndMonth = new JComboBox();
		EndMonth.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"}));
		EndMonth.setBounds(210, 82, 40, 20);
		contentPane.add(EndMonth);
		
		EndDay = new JComboBox();
		EndDay.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",}));
		EndDay.setBounds(262, 82, 40, 20);
		contentPane.add(EndDay);
		
		EndMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {				
				 Object month = EndMonth.getSelectedItem();
			        String teMonth = month.toString();			       
			        long i = Integer.parseInt(teMonth);
			        eMonth= teMonth.format("%02d",i);
			        System.out.println(i);
				if(i==2)
				EndDay.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29",}));
				else{
				EndDay.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",}));
				}
				}
        });

		EndDay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				 Object info = EndDay.getSelectedItem();
			        String teDay = info.toString();			       
			        long i = Integer.parseInt(teDay);
			        eDay= teDay.format("%02d",i);
			        System.out.println(i);
				} });
		
		
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
	}
	
	public void dateCheck(){
		sDate=new StringBuilder().append(sYear).append(sMonth).append(sDay).toString();
		eDate=new StringBuilder().append(eYear).append(eMonth).append(eDay).toString();
		int sdate = Integer.parseInt(sDate);
		int edate = Integer.parseInt(eDate);
		
		System.out.println("\n" +sdate);
		System.out.println(edate);
		if(sdate>edate){
			
			date=false;
			
		}
		
	}
	
}
