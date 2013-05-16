package mailroom;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class AdvSearch extends JFrame {
//hi
	private JPanel contentPane;
	private JTextField FirstNameField;
	private JTextField LastNameField;
	private JTextField StartMonth;
	private JTextField StopField;
	private JTextField BoxNum;
	private JButton btnSearch;
	private JTextField StartDay;
	private JTextField StartYear;
	private JTextField EndMonth;
	private JLabel label_2;
	private JTextField EndDay;
	private JLabel label_3;
	private JTextField EndYear;
	private JLabel lblTracking;
	private JTextField trackingField;

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
		setBounds(100, 100, 442, 173);
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
		LastNameField.setBounds(298, 33, 118, 20);
		contentPane.add(LastNameField);
		LastNameField.setColumns(10);
		
		JLabel lblDates = new JLabel("Dates:");
		lblDates.setForeground(new Color(255, 255, 255));
		lblDates.setBounds(10, 61, 46, 14);
		contentPane.add(lblDates);
		
		StartMonth = new JTextField();
		StartMonth.setBounds(53, 58, 23, 20);
		contentPane.add(StartMonth);
		
		
		
		JLabel lblTo = new JLabel("to");
		lblTo.setForeground(new Color(255, 255, 255));
		lblTo.setBounds(171, 64, 46, 14);
		contentPane.add(lblTo);
		
		JLabel label = new JLabel("/");
		label.setForeground(Color.WHITE);
		label.setBounds(79, 61, 17, 14);
		contentPane.add(label);
		
		StartDay = new JTextField();
		StartDay.setBounds(87, 58, 23, 20);
		contentPane.add(StartDay);
		
		JLabel label_1 = new JLabel("/");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(114, 61, 17, 14);
		contentPane.add(label_1);
		
		StartYear = new JTextField();
		StartYear.setBounds(122, 58, 39, 20);
		contentPane.add(StartYear);
		
		EndMonth = new JTextField();
		EndMonth.setBounds(224, 58, 23, 20);
		contentPane.add(EndMonth);
		
		label_2 = new JLabel("/");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(250, 61, 17, 14);
		contentPane.add(label_2);
		
		EndDay = new JTextField();
		EndDay.setBounds(257, 58, 23, 20);
		contentPane.add(EndDay);
		
		label_3 = new JLabel("/");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(283, 61, 17, 14);
		contentPane.add(label_3);
		
		EndYear = new JTextField();
		EndYear.setBounds(290, 58, 39, 20);
		contentPane.add(EndYear);
		
		
		StartMonth.addKeyListener(new KeyAdapter(){
	          public void keyPressed(KeyEvent e){
	          String value=StartMonth.getText();
	          if(value.length()==1){
	          StartDay.requestFocus();
	            }
	          }
	     });
		
		StartDay.addKeyListener(new KeyAdapter(){
	          public void keyPressed(KeyEvent e){
	          String value=StartDay.getText();
	          if(value.length()==1){
	          StartYear.requestFocus();
	            }
	          }
	     });
		
		StartYear.addKeyListener(new KeyAdapter(){
	          public void keyPressed(KeyEvent e){
	          String value=StartYear.getText();
	          if(value.length()==3){
	          EndMonth.requestFocus();
	            }
	          }
	     });
		
		
		EndMonth.addKeyListener(new KeyAdapter(){
	          public void keyPressed(KeyEvent e){
	          String value=EndMonth.getText();
	          if(value.length()==1){
	          EndDay.requestFocus();
	            }
	          }
	     });
		
		EndDay.addKeyListener(new KeyAdapter(){
	          public void keyPressed(KeyEvent e){
	          String value=EndDay.getText();
	          if(value.length()==1){
	          EndYear.requestFocus();
	            }
	          }
	     });
		
		EndYear.addKeyListener(new KeyAdapter(){
	          public void keyPressed(KeyEvent e){
	          String value=EndYear.getText();
	          if(value.length()==3){
	          StopField.requestFocus();
	            }
	          }
	     });
		
		
		JLabel lblStop = new JLabel("Stop:");
		lblStop.setForeground(new Color(255, 255, 255));
		lblStop.setBounds(10, 86, 46, 14);
		contentPane.add(lblStop);
		
		StopField = new JTextField();
		StopField.setBounds(53, 83, 101, 20);
		contentPane.add(StopField);
		StopField.setColumns(10);
		
		JLabel lblBox = new JLabel("Box #:");
		lblBox.setForeground(new Color(255, 255, 255));
		lblBox.setBounds(181, 86, 46, 14);
		contentPane.add(lblBox);
		
		BoxNum = new JTextField();
		BoxNum.setBounds(222, 83, 86, 20);
		contentPane.add(BoxNum);
		BoxNum.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(327, 107, 89, 23);
		contentPane.add(btnSearch);
		
		lblTracking = new JLabel("Tracking # :");
		lblTracking.setForeground(Color.WHITE);
		lblTracking.setBounds(10, 11, 66, 14);
		contentPane.add(lblTracking);
		
		trackingField = new JTextField();
		trackingField.setBounds(79, 8, 337, 20);
		contentPane.add(trackingField);
		trackingField.setColumns(10);
		
		JCheckBox chckbxDelivered = new JCheckBox("Delivered");
		chckbxDelivered.setForeground(new Color(255, 255, 255));
		chckbxDelivered.setBackground(new Color(0, 102, 0));
		chckbxDelivered.setBounds(13, 107, 97, 23);
		contentPane.add(chckbxDelivered);
		
		JCheckBox chckbxPickedUp = new JCheckBox("Picked Up");
		chckbxPickedUp.setForeground(new Color(255, 255, 255));
		chckbxPickedUp.setBackground(new Color(0, 102, 0));
		chckbxPickedUp.setBounds(130, 107, 97, 23);
		contentPane.add(chckbxPickedUp);
		
		
		
	}
}
