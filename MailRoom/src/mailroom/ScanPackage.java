package mailroom;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;


public class ScanPackage extends JFrame {

	private JPanel contentPane;
	private JTextField NameText;
	private JTextField StopText;
	private static JTextField TrackText;
	private JTextField BoxText;
	private JTextField LastNameText;
	private JLabel lblDate_1;
	JComboBox comboBox;
	String newDate;
	Date date =new Date();
<<<<<<< HEAD
=======
	boolean noStops =false;
>>>>>>> origin/Tom

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScanPackage frame = new ScanPackage(new DatabaseManager());
					
					frame.addWindowListener(new WindowAdapter(){ 
						  public void windowOpened( WindowEvent e){ 
						    TrackText.requestFocus();
						  } 
						});

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScanPackage(final DatabaseManager manager) {
		setResizable(false);
		setTitle("Scan My Package");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/images.jpg"));
		setIconImage(icon.getImage());
		
		setBackground(new Color(0, 102, 0));
		//setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\MailRoom\\images.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 545, 272);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NameText = new JTextField();
		NameText.setBounds(110, 80, 179, 20);
		contentPane.add(NameText);
		NameText.setColumns(10);
		
		StopText = new JTextField();
		StopText.setBounds(375, 130, 151, 20);
		contentPane.add(StopText);
		StopText.setColumns(10);
	
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setBounds(35, 85, 76, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setBounds(35, 110, 46, 14);
		contentPane.add(lblDate);
		
		JLabel lblOtherStop = new JLabel("Other Stop:");
		lblOtherStop.setForeground(new Color(255, 255, 255));
		lblOtherStop.setBounds(299, 133, 66, 14);
		contentPane.add(lblOtherStop);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(229, 161, 89, 23);
		contentPane.add(btnSave);
		btnSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String stop=(String)comboBox.getSelectedItem();
				if(!StopText.getText().equals(null)){
					stop=StopText.getText();
				}
<<<<<<< HEAD
				
=======
				Person p= manager.getPerson(NameText.getText(),LastNameText.getText());
>>>>>>> origin/Tom
				manager.addPackage(new Package(NameText.getText(),LastNameText.getText(),date,BoxText.getText(),stop,TrackText.getText()));
				
			}
			
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(35, 161, 89, 23);
		contentPane.add(btnClear);
		
	    btnClear.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
               StopText.setText("");
               TrackText.setText("");
               NameText.setText("");
               LastNameText.setText("");
               BoxText.setText("");
            }
        });
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(405, 161, 89, 23);
		contentPane.add(btnClose);
		
	    btnClose.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
		
		TrackText = new JTextField();
		TrackText.setBounds(110, 55, 416, 20);
		contentPane.add(TrackText);
		addWindowListener(new WindowAdapter(){ 
			  public void windowOpened( WindowEvent e){ 
			    TrackText.requestFocus();
			  } 
			});
		
		TrackText.setColumns(10);
		
		
		
		JLabel lblTracking = new JLabel("Tracking #");
		lblTracking.setForeground(new Color(255, 255, 255));
		lblTracking.setBounds(35, 60, 66, 14);
		contentPane.add(lblTracking);
		
		JButton btnAutoFill = new JButton("Auto Fill");
		btnAutoFill.setBounds(437, 23, 89, 20);
		contentPane.add(btnAutoFill);
		final ScanPackage frame = this;
		btnAutoFill.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			ArrayList<Person> p=manager.findPerson(NameText.getText(),LastNameText.getText());
			if(p==null){
				JOptionPane.showMessageDialog(null,"no one was found by that name.");
				return;
			}
			if(p.size()==0){
				JOptionPane.showMessageDialog(null,"no one was found by that name.");
				return;
			}
			if(p.size()>1){
				new MultipleResultDisplay(frame,p);
			}
			else{
			BoxText.setText(p.get(0).getBox());
			comboBox.setSelectedItem(p.get(0).getStop().getName());
			}
				
			}
			
		});
		
		btnAutoFill.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			Person p=manager.getPerson(NameText.getText(),LastNameText.getText());
			
			BoxText.setText(p.getBox());
			comboBox.setSelectedItem(p.getStop().getName());
				
				
			}
			
		});
		
		BoxText = new JTextField();
		BoxText.setBounds(375, 105, 151, 20);
		contentPane.add(BoxText);
		BoxText.setColumns(10);
		
		JLabel lblBox = new JLabel("Box #");
		lblBox.setForeground(new Color(255, 255, 255));
		lblBox.setBounds(299, 108, 34, 14);
		contentPane.add(lblBox);
		
	    comboBox = new JComboBox();
		DefaultComboBoxModel stopNames=null;
<<<<<<< HEAD
		boolean noStops =false;
		try{
		ArrayList<Stop> stops = (ArrayList<Stop>) manager.getStops();
=======
		
		try{
		ArrayList<Stop> stops = manager.getStops();
>>>>>>> origin/Tom
		String[] sa= new String[stops.size()-1];
		int i=0;
		for(Stop s: stops){
			sa[i]= s.getName();
			i++;
		}
		 stopNames = new DefaultComboBoxModel(sa);
		}
		catch(NegativeArraySizeException ex){
			JOptionPane.showMessageDialog(this, "Please create Stops before scanning in packages");
			dispose();
			noStops=true;
		}
		
<<<<<<< HEAD
		if(noStops){
			dispose();
		}
		try{
		comboBox.setModel(stopNames);
		}
		catch(NullPointerException ex){
			
			dispose();
		}
=======
		
		try{
			comboBox.setModel(stopNames);
			}
			catch(NullPointerException ex){
				dispose();
			}

>>>>>>> origin/Tom
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AAO", "Academic Affairs", "Admissions\t", "AITC", "Alumni/Foundation", "Art", "AS&F", "Bookstore", "Business Office", "Communications", 
				"Community Partnership", "Computing Services", "Counseling & Career", "Counselor Education", "EEO", "English/ Communication", "Enrollment", "Extended Studies", "Facilities Office", "Facilities Warehouse", 
				"Finance/ Administration", "Financial Aid", "Gingerbread House", "Graduate School", "HGPPSL", "Hold for Pickup", "Housing", "HPPE", "Human Resources", "Institutional Research", "Library", "Museum", "Music",
				"Nursing", "One Stop", "Payroll", "Plachy", "Police Department", "President", "Print Shop", "Purchasing", "Radio Station", "Records", "REX", "School of Business", "SMT", "SODEXO", "Student Affairs", 
				"Student Life", "SUB Office", "SUB Mailroom", "SVP Enrollment Manager", "Teacher Education", "Theatre", "Title V", "Upward Bound"}));
		comboBox.setBounds(110, 130, 179, 20);
		contentPane.add(comboBox);
		
		JLabel lblStop = new JLabel("Stop:");
		lblStop.setForeground(Color.WHITE);
		lblStop.setBounds(35, 133, 46, 14);
		contentPane.add(lblStop);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(299, 83, 66, 14);
		contentPane.add(lblLastName);
		
		LastNameText = new JTextField();
		LastNameText.setBounds(375, 80, 151, 20);
		contentPane.add(LastNameText);
		LastNameText.setColumns(10);
		
		JLabel lblDate_1 = new JLabel("Date");
		lblDate_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate_1.setForeground(Color.WHITE);
		lblDate_1.setBounds(110, 105, 96, 20);
		contentPane.add(lblDate_1);
		Date date =new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("MM / dd / yyyy");
        newDate = ft.format(date);
		lblDate_1.setText(newDate);
		setVisible(true);
<<<<<<< HEAD
		
		
	}

	public static JTextField getTrackText() {
		return TrackText;
	}

	public static void setTrackText(JTextField trackText) {
		TrackText = trackText;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getNameText() {
		return NameText;
	}

	public JTextField getStopText() {
		return StopText;
	}

	public JTextField getBoxText() {
		return BoxText;
	}

	public JTextField getLastNameText() {
		return LastNameText;
	}

	public JLabel getLblDate_1() {
		return lblDate_1;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public String getNewDate() {
		return newDate;
	}

	public Date getDate() {
		return date;
=======
		packageExistCheck();
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				packageExistCheck();
				
			}
			
		});
		t.start();
		
	}
	public void packageExistCheck(){
		if(noStops){
			//setVisible(false);
			dispose();
		}
>>>>>>> origin/Tom
	}
}
