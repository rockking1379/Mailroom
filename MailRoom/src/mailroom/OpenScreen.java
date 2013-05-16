package mailRoom;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import java.awt.Dialog.ModalExclusionType;

public class OpenScreen extends JFrame {

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
	public OpenScreen(boolean admin) {
		setVisible(true);
		setTitle("My Mail Room");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/Untitled.jpg"));
		setIconImage(icon.getImage());
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Untitled.jpg"));
		setBackground(new Color(0, 102, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 447);
		
		JMenuBar menuBar = new JMenuBar();
		if(admin){
		setJMenuBar(menuBar);
		}
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmCreateNewAccount = new JMenuItem("Create New Account");
		
		mntmCreateNewAccount.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddAccount a = new AddAccount();
				
			}
			
		});
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
		mnFile.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	dispose();
            }
        });
		
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
                ScanPackage scan = new ScanPackage();
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
	                RoutePrint route = new RoutePrint();
	                route.setVisible(true);
	            }
	        });
		
	         Table newContentPane = new Table();
	         newContentPane.setBounds(151, 11, 560, 366);
	         contentPane.add(newContentPane);
	         newContentPane.setOpaque(true);
	         
	         JButton btnSearch = new JButton("Search");
	         btnSearch.setToolTipText("Search for a package");
	         btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
	         btnSearch.setBounds(20, 204, 121, 80);
	         contentPane.add(btnSearch);
	         
	         btnSearch.addActionListener(new ActionListener() {
		    	 
	             public void actionPerformed(ActionEvent e)
	             {
	                 AdvSearch search = new AdvSearch();
	                 search.setVisible(true);
	             }
	         });
	}
}
