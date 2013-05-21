package mailroom;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> origin/Tom
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.lang.model.element.Element;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RoutePrint extends JFrame {

	private JPanel contentPane;
	JPanel rtHolder;
	boolean ready = false;
	JTextArea toPrint;

	/**
	 * Launch the application.
	 */
	ArrayList<JCheckBox> checkBoxes;
<<<<<<< HEAD
	List<Route> routes;
=======
	ArrayList<Route> routes;
>>>>>>> origin/Tom
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoutePrint frame = new RoutePrint(new DatabaseManager());
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
	public RoutePrint(final DatabaseManager manager) {
		checkBoxes = new ArrayList<JCheckBox>();
		
		routes=manager.getRoutes();
		
		for(Route r: routes){
			checkBoxes.add(new JCheckBox(r.getName()));
		}
		
		rtHolder = new JPanel();
		rtHolder.setLayout(new GridLayout());
		
		for(JCheckBox c: checkBoxes){
			rtHolder.add(c);
		}
		
		setTitle("Print a Route");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/compass.jpg"));
		setIconImage(icon.getImage());
		//setIconImage(Toolkit.getDefaultToolkit().getImage("src\\compass.jpg"));
		setBackground(new Color(0, 102, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 300);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseSelectA = new JLabel("Please Select a Route To Print: ");
		lblPleaseSelectA.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPleaseSelectA.setForeground(new Color(255, 255, 255));
		lblPleaseSelectA.setBackground(new Color(255, 255, 255));
		lblPleaseSelectA.setBounds(36, 31, 204, 14);
		contentPane.add(lblPleaseSelectA);
		
		JCheckBox chckbxRoute = new JCheckBox("Route 1");
		chckbxRoute.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxRoute.setBounds(36, 52, 97, 23);
		contentPane.add(chckbxRoute);
		
		JCheckBox chckbxRoute_1 = new JCheckBox("Route 2");
		chckbxRoute_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxRoute_1.setBounds(36, 78, 97, 23);
		contentPane.add(chckbxRoute_1);
		
		JCheckBox chckbxRoute_2 = new JCheckBox("Route 3");
		chckbxRoute_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxRoute_2.setBounds(36, 104, 97, 23);
		contentPane.add(chckbxRoute_2);
		
		JCheckBox chckbxRoute_3 = new JCheckBox("Route 4");
		chckbxRoute_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxRoute_3.setBounds(36, 130, 97, 23);
		contentPane.add(chckbxRoute_3);
		
		JCheckBox chckbxRoute_4 = new JCheckBox("Route 5");
		chckbxRoute_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxRoute_4.setBounds(36, 156, 97, 23);
		contentPane.add(chckbxRoute_4);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrint.setBounds(335, 228, 89, 23);
		contentPane.add(btnPrint);
		
		
		
		btnPrint.addActionListener(new ActionListener() {
			Date date =new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("MM / dd / yyyy");
            String newDate = ft.format(date);
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	if(ready){
            		try {
						toPrint.print();
						String s = "Route Print form "+date.getMonth()+"-"+date.getDay()+"-"+date.getYear()+" time"+ date.getTime()+".txt";
						BufferedWriter writer = new BufferedWriter(new FileWriter(new File(s)));
						String[] doc =toPrint.getText().split("\n");
						for(String str: doc){
						writer.write(str);
						writer.newLine();
						
						}
						writer.close();
						
						dispose();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "An error occured while trying to print\n"+e1.getMessage());
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "The file could not be created\n"+ e1.getMessage());
					}
            		
            		
            	}
            	
<<<<<<< HEAD
            	List<Stop> stops = new ArrayList<Stop>();
=======
            	ArrayList<Stop> stops = new ArrayList<Stop>();
>>>>>>> origin/Tom
            	ArrayList<Package> packages = new ArrayList<Package>();
            	for(JCheckBox c: checkBoxes){
            		if(c.isSelected()){
            			 			
<<<<<<< HEAD
            			stops = manager.getStopsFromRoute(c.getText());
            			for(Stop st: stops){
=======
            			for(Stop st: manager.getStopsFromRoute(c.getText())){
>>>>>>> origin/Tom
            			stops.add(st);
            			}
            			
            		}
            	}
            	
            	
            	
            	
            	
            	
            	
            	//A standard piece of papaer is (470 X 646) pixels
            	//136 spaces for the width
            	JLabel message = new JLabel("Please review the routes and select print again.");
            	message.setForeground(Color.WHITE);
            	message.setBounds(50,250,800,20);
            	contentPane.add(message);
            	
            	
            	
            	toPrint = new JTextArea();
            	
            	toPrint.setFont(new Font("Monospaced",Font.PLAIN,12));
 
            	toPrint.setLineWrap(true);
            	JScrollPane sp = new JScrollPane();
            	sp.setViewportView(toPrint);
            	sp.setBounds(10,300,500,250);
            	contentPane.add(sp);
            	
            	setBounds(100,100,535,600);
            	stops.add(new Stop("SUwerrrrrrrrrrrrrrrrrrrrwerrrrrrrrrrB"));
            	
            	
            	
            	
            	
            	String equ = "======================================" +
        				"===========================\n";
            	String newHead;
            	String tab ="       ";
            	String sign = "________________________\n";
            	
            	String fieldHeader= "Last"+tab+"First"+tab+"Addr."+tab+"Track#"+tab+"Sign Here";
            	for(Stop s: stops){
            		String stn=s.getName();
            		if(stn.length()>16){
            			stn=stn.substring(0,16);
            		}
            		newHead = "Package Delivery for mail stop "+stn+" on "+newDate;
            		toPrint.setText(toPrint.getText()+equ);
            		int j = 32-newHead.length();
            		
            		addSpacing(j);
            		
            		toPrint.setText(toPrint.getText()+newHead+"\n"+equ+"\n"+fieldHeader+"\n");            		
            		packages = manager.getPackagesFromStop(s.getID());
            		
            		
            		
            		packages.add(new Package("Thomas","Aehirng", "asdfadsfgdfgsdb@grizzlies.adams.ede", date,"93","234234235346541111532"));
            		packages.add(new Package("sdf","Yehriwerng", "nehringtb@sfdd.ede", date, "93", "23423423534654111112308"));
            		packages.add(new Package("dfs","Xdfsd", "nehringtb@sfdd.ede", date,"909", "2342342353465411112347"));
            		packages.add(new Package("sdafsdf","Ddd", "nehringtb@sfdd.ede", date, "324", "234234235346541111676908"));
            		packages.add(new Package("sdfsdf","Edfsdfsdfdf", "nehringtb@sfdd.ede", date,"234", "234234235346541111456456"));
            		packages.add(new Package("sdfsdf","Edfsdfg", "nehringtb@sfdd.ede", date,"234", "234234235346541111789763"));
            		packages.add(new Package("sdfsdfs","Ddfsdsdf", "nehringtb@sfdd.ede", date,"34","234234235346541111797807"));
            		packages.add(new Package("asdfasd","Wdfsdf", "nehringtb@sfdd.ede", date,"2343", "234234235346541111532"));
            		packages.add(new Package("sdf","Rehriwerng", "nehringtb@sfdd.ede", date,"234", "23423423534654111112308"));
            		packages.add(new Package("dfs","Hdfsd", "nehringtb@sfdd.ede", date,"345", "2342342353465411112347"));
            		packages.add(new Package("sdafsdf","Add", "nehringtb@sfdd.ede", date,"345", "234234235346541111676908"));
            		packages.add(new Package("sdfsdf","Ldfsdfsdfdf", "nehringtb@sfdd.ede", date,"098", "234234235346541111456456"));
            		packages.add(new Package("sdfsdf","Mdfsdfg", "nehringtb@sfdd.ede", date,"34", "234234235346541111789763"));
            		packages.add(new Package("sdfsdfs","dfsdsdf", "nehringtb@sfdd.ede", date,"234", "234234235346541111797807"));
            		
            		String[] packageArray = new String[packages.size()-1];
            		for(int i=0; i<packages.size()-1;i++){
            			packageArray[i]=packages.get(i).getLName();
            		}
            		
            		Arrays.sort(packageArray);
            		int i=0;
            		for(String str: packageArray){
            			for(int k=0;k<packages.size()-1;k++){
            				Package pac = packages.get(k);
            				if(str.equals(pac.getLName())){
            					packages.remove(pac);
            					packages.add(i,pac);
            				}
            			}
            			i++;
            		}
            		
            		
            		
            		
            		
            		for (Package p: packages){
            			
            			String var =p.getLName();
            			if(var.length()>=11){
            				var= var.substring(0, 8);
            			}
            			toPrint.setText(toPrint.getText()+var);
            			addSpacing(11-var.length());
            			
            			
            			var=p.getFName();
            			if(var.length()>=12){
            				var=var.substring(1,10);
            			}
            			toPrint.setText(toPrint.getText()+var);
            			addSpacing(12-var.length());
            			
            			
            			var=p.getBoxNum();
            			toPrint.setText(toPrint.getText()+var);
            			addSpacing(12-var.length());
            			
            			
            			
            			String tn = p.getTrackNum().substring(p.getTrackNum().length()-4);
            			toPrint.setText(toPrint.getText()+tn);
            			addSpacing(7-tn.length());
            			
            			
            			
            			toPrint.setText(toPrint.getText()+sign);
            		}
            		
            		
            		
            	}
            	
            	
            	
            	
            	
            	
            	
            	ready=true;
            	
            }
           private void addSpacing(int j){
            	for(int i=0; i<j;i++){
        			toPrint.setText(toPrint.getText()+" ");
        		}
            }
        });

	}
}
