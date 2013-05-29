package split.mailrooms;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.client.common.*;
import com.client.common.Package;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

import java.util.Date;
import com.client.common.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JTextArea;



public class SUBScanPackage extends JFrame {

	private JPanel contentPane;
	private JTextField NameText;
	ArrayList<Person> personsFound;
	Person selectedPerson;
	private static JTextField TrackText;
	private JTextField BoxText;
	private JTextField LastNameText;
	private JLabel lblDate_1;
	JComboBox comboBox;
	String newDate;
	Date date =new Date();
	DatabaseManager manager;
	String loggedIn;
	JComboBox comboBox_1;
	
	 String packDate= DateFormat.getDateInstance(DateFormat.SHORT).format(date);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					SUBScanPackage frame = new SUBScanPackage(new DatabaseManager(), "Someone");
					
					frame.addWindowListener(new WindowAdapter(){ 
						  public void windowOpened( WindowEvent e){ 
						    TrackText.requestFocus();
						  } 
						});

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

	public SUBScanPackage(DatabaseManager manager, String loggedIn) {
		
		this.manager=manager;
		this.loggedIn=loggedIn;
		
		setResizable(false);
		setTitle("Scan My Package");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/images.jpg"));
		setIconImage(icon.getImage());
		
		setBackground(new Color(0, 102, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\MailRoom\\images.jpg"));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 545, 246);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(153, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NameText = new JTextField();
		NameText.setBounds(110, 80, 179, 20);
		contentPane.add(NameText);
		NameText.setColumns(10);

		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setBounds(35, 85, 76, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setBounds(35, 163, 46, 14);

		contentPane.add(lblDate);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(227, 188, 89, 23);
		contentPane.add(btnSave);

		btnSave.addActionListener(new SaveListener());

		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(35, 188, 89, 23);
		contentPane.add(btnClear);
		
	    btnClear.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
              clear();
              
            }
        });
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(406, 188, 89, 23);
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

		
		btnAutoFill.addActionListener(new AutoFillListener(this));
	
		
		BoxText = new JTextField();
		BoxText.setBounds(375, 106, 151, 20);
		contentPane.add(BoxText);
		BoxText.setColumns(10);
		
		final JLabel lblBox = new JLabel("Box #");
		lblBox.setForeground(new Color(255, 255, 255));
		lblBox.setBounds(299, 109, 34, 14);
		contentPane.add(lblBox);
		
	    comboBox = new JComboBox();
		DefaultComboBoxModel stopNames=null;
		boolean noStops =false;
		try{
		ArrayList<Stop> stops = (ArrayList<Stop>) manager.getStops();
		String[] sa= new String[stops.size()];
		
		for(Stop s: stops){
			sa[stops.indexOf(s)]= s.getName();
			
		}
		 stopNames = new DefaultComboBoxModel(sa);
		}
		catch(NegativeArraySizeException ex){
			JOptionPane.showMessageDialog(this, "Please create Stops before scanning in packages");
			
		}
		
		comboBox.setModel(stopNames);
		
		/*comboBox.setModel(new DefaultComboBoxModel(new String[] {"AAO", "Academic Affairs", "Admissions\t", "AITC", "Alumni/Foundation", "Art", "AS&F", "Bookstore", "Business Office", "Communications", 
				"Community Partnership", "Computing Services", "Counseling & Career", "Counselor Education", "EEO", "English/ Communication", "Enrollment", "Extended Studies", "Facilities Office", "Facilities Warehouse", 
				"Finance/ Administration", "Financial Aid", "Gingerbread House", "Graduate School", "HGPPSL", "Hold for Pickup", "Housing", "HPPE", "Human Resources", "Institutional Research", "Library", "Museum", "Music",
				"Nursing", "One Stop", "Payroll", "Plachy", "Police Department", "President", "Print Shop", "Purchasing", "Radio Station", "Records", "REX", "School of Business", "SMT", "SODEXO", "Student Affairs", 
				"Student Life", "SUB Office", "SUB Mailroom", "SVP Enrollment Manager", "Teacher Education", "Theatre", "Title V", "Upward Bound"}));*/
		comboBox.setBounds(110, 105, 179, 20);
		
		contentPane.add(comboBox);
		/*comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {	
				 Object tsub = comboBox.getSelectedItem();
				 String sub = tsub.toString();			       
			       
				if(sub=="SUB Mailroom"){
				lblBox.setVisible(true);
				BoxText.setVisible(true);
				}else{
					BoxText.setVisible(false);
					lblBox.setVisible(false);
				}
					
				}
        });*/
		contentPane.add(comboBox);
		
	
	
		
		
		JLabel lblStop = new JLabel("Stop:");
		lblStop.setForeground(Color.WHITE);
		lblStop.setBounds(32, 110, 46, 14);

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

		lblDate_1.setBounds(166, 159, 96, 20);
		contentPane.add(lblDate_1);
		
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        newDate = ft.format(date);
		lblDate_1.setText(newDate);
		
		JLabel lblOtherStop = new JLabel("Other Stop:");
		lblOtherStop.setForeground(new Color(255, 255, 255));
		lblOtherStop.setBounds(32, 134, 79, 14);
		contentPane.add(lblOtherStop);
		
		JButton btnNewStop = new JButton("Create New Stop");
		btnNewStop.setBounds(110, 130, 179, 23);
		contentPane.add(btnNewStop);
		
		JLabel lblCarrier = new JLabel("Carrier: ");
		lblCarrier.setForeground(new Color(255, 255, 255));
		lblCarrier.setBounds(299, 134, 66, 14);
		contentPane.add(lblCarrier);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(375, 131, 151, 20);
		
		String[] ca = new String[manager.getCouriers().size()];
		
		for(Courier c: manager.getCouriers()){
			ca[manager.getCouriers().indexOf(c)]=c.getName();
		}
		
		comboBox_1.setModel(new DefaultComboBoxModel(ca));
		contentPane.add(comboBox_1);
		
		JLabel lblOtherCarrier = new JLabel("Other Carrier:");
		lblOtherCarrier.setForeground(Color.WHITE);
		lblOtherCarrier.setBounds(292, 163, 79, 14);
		contentPane.add(lblOtherCarrier);
		
		JButton btnCreateNewCarrier = new JButton("Create New Carrier");
		btnCreateNewCarrier.setBounds(375, 159, 151, 23);
		contentPane.add(btnCreateNewCarrier);
		setVisible(true);
		
		btnCreateNewCarrier.addActionListener(new NewCourierListener());
		
		
		btnNewStop.addActionListener(new NewStopListener());
		
		
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
		

		lblDate_1.setBounds(110, 130, 96, 20);
		contentPane.add(lblDate_1);
		Date date =new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        String newDate = ft.format(date);
        System.out.println(newDate);
		lblDate_1.setText(newDate);
		return date;

	}
	public void clear(){
		 TrackText.setText("");
         NameText.setText("");
         LastNameText.setText("");
         BoxText.setText("");
	}
	public class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String stop=(String)comboBox.getSelectedItem();
			String tDate = newDate;
			Package p = null;
			
			
			if(TrackText.getText().length()<4 ||  NameText.getText().equals("") || LastNameText.getText().equals("")){
				JOptionPane.showMessageDialog(null,"You have empty fields. Please be fure you have entered information for all the above fields.");
				return;
			}
			
			
			try
			{
				tDate = newDate;
			}
			catch(Exception e)
			{
				//Unknown
				System.out.println(e.getMessage());
			}
			
			String stopName=(String)comboBox.getSelectedItem();
			if(selectedPerson==null){
				int stopId=0;
				
				for(Stop s: manager.getStops()){
					if(s.getName().equals((String)comboBox.getSelectedItem()));
					stopId=s.getID();
					stopName=s.getName();
					break;
				}
				selectedPerson=new Person(NameText.getText(),LastNameText.getText(), "Unknown@", "Unknown", BoxText.getText(),stop);
				manager.addPerson(selectedPerson);
				//manager.createPerson(sps);
				p = new Package(NameText.getText(),LastNameText.getText(),selectedPerson.getEmail(),tDate,BoxText.getText(),stop,TrackText.getText(),loggedIn,(String)comboBox_1.getSelectedItem());
			}
			else{
				selectedPerson.setStop(stopName);
				manager.updatePerson(selectedPerson);
				p = new Package(NameText.getText(),LastNameText.getText(),selectedPerson.getEmail(),tDate,BoxText.getText(),stop,TrackText.getText(),loggedIn,(String)comboBox_1.getSelectedItem());
				System.out.println(packDate);
			}
			
			manager.addPackage(p);
			clear();
			
		}
	}
	public class AutoFillListener implements ActionListener{
		SUBScanPackage frame;
		public AutoFillListener(JFrame frame){
			this.frame=(SUBScanPackage)frame;
		}
		
		
		public void actionPerformed(ActionEvent e) {
			ArrayList<Person> p=(ArrayList<Person>) manager.findPerson(NameText.getText(),LastNameText.getText());
			if(p==null){
				JOptionPane.showMessageDialog(null,"no one was found by that name.");
				return;
			}
			if(p.size()==0){
				JOptionPane.showMessageDialog(null,"no one was found by that name.");
				return;
			}
			if(p.size()>1){
				new SUBMultipleResultDisplay(frame,p);
			}
			else{
			selectedPerson=p.get(0);
			BoxText.setText(selectedPerson.getBox());
			comboBox.setSelectedItem(selectedPerson.getStop());
			NameText.setText(selectedPerson.getFirstName());
			LastNameText.setText(selectedPerson.getLastName());
			}
				
			}
	}
	JFrame f=this;
	public class NewStopListener implements ActionListener{
		 public void actionPerformed(ActionEvent e)
         {
             new CreateStop(manager,loggedIn,f);
             dispose();
             
         }
	}
	public class NewCourierListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new CreateCarrier(manager,loggedIn);
			dispose();
		}
		
	}
}
