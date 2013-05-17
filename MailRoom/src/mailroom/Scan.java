package mailroom;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class Scan extends JFrame {

	private JPanel contentPane;
	private JTextField NameText;
	private JTextField StopText;
	private JTextField TrackText;
	private JTextField BoxText;
	private JTextField LastNameText;
	private JLabel lblDate_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scan frame = new Scan();
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
	public Scan() {
		setResizable(false);
		setTitle("Scan My Package");
		setBackground(new Color(0, 102, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\MailRoom\\images.jpg"));
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
		TrackText.setColumns(10);
		
		JLabel lblTracking = new JLabel("Tracking #");
		lblTracking.setForeground(new Color(255, 255, 255));
		lblTracking.setBounds(35, 60, 66, 14);
		contentPane.add(lblTracking);
		
		JButton btnAutoFill = new JButton("Auto Fill");
		btnAutoFill.setBounds(437, 23, 89, 20);
		contentPane.add(btnAutoFill);
		
		BoxText = new JTextField();
		BoxText.setBounds(375, 105, 151, 20);
		contentPane.add(BoxText);
		BoxText.setColumns(10);
		
		JLabel lblBox = new JLabel("Box #");
		lblBox.setForeground(new Color(255, 255, 255));
		lblBox.setBounds(299, 108, 34, 14);
		contentPane.add(lblBox);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AAO", "Academic Affairs", "Admissions\t", "AITC", "Alumni/Foundation", "Art", "AS&F", "Bookstore", "Business Office", "Communications", "Community Partnership", "Computing Services", "Counseling & Career", "Counselor Education", "EEO", "English/ Communication", "Enrollment", "Extended Studies", "Facilities Office", "Facilities Warehouse", "Finance/ Administration", "Financial Aid", "Gingerbread House", "Graduate School", "HGPPSL", "Hold for Pickup", "Housing", "HPPE", "Human Resources", "Institutional Research", "Library", "Museum", "Music", "Nursing", "One Stop", "Payroll", "Plachy", "Police Department", "President", "Print Shop", "Purchasing", "Radio Station", "Records", "REX", "School of Business", "SMT", "SODEXO", "Student Affairs", "Student Life", "SUB Office", "SUB Mailroom", "SVP Enrollment Manager", "Teacher Education", "Theatre", "Title V", "Upward Bound"}));
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
        String newDate = ft.format(date);
		lblDate_1.setText(newDate);
		
	}
}
