package split.mailrooms;

import java.awt.BorderLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.lang.model.element.Element;

import java.awt.EventQueue;


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
import java.awt.GridBagLayout;
import com.client.common.*;
import com.client.common.Package;

public class RoutePrint extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;

	
	private JPanel rtHolder_1;
	boolean ready = false;
	JTextArea toPrint = new JTextArea();

	ArrayList<JCheckBox> checkBoxes;
	List<Route> routes;

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
		routes=(ArrayList<Route>)manager.getRoutes();
		rtHolder_1=new JPanel();
		
		for(Route r: routes){
			JCheckBox ch=new JCheckBox(r.getName());
			ch.setBackground(new Color(51, 204, 0));
			checkBoxes.add(ch);
		}
		
		
		
		for(JCheckBox c: checkBoxes){
			rtHolder_1.add(c);
		}
		



		setTitle("Print a Route");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/compass.jpg"));
		setIconImage(icon.getImage());
		//setIconImage(Toolkit.getDefaultToolkit().getImage("src\\compass.jpg"));
		setBackground(new Color(0, 102, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 535, 245);
		contentPane = new JPanel();
		setResizable(false);

		
		contentPane_1 = new JPanel();

		contentPane_1.setBackground(new Color(0, 102, 0));
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblPleaseSelectA = new JLabel("Please Select a Route To Print: ");
		lblPleaseSelectA.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPleaseSelectA.setForeground(new Color(255, 255, 255));
		lblPleaseSelectA.setBackground(new Color(255, 255, 255));
		lblPleaseSelectA.setBounds(36, 31, 204, 14);
		contentPane_1.add(lblPleaseSelectA);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrint.setBounds(401, 185, 89, 23);
		contentPane_1.add(btnPrint);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 56, 444, 118);
		contentPane_1.add(scrollPane);
		
		scrollPane.setViewportView(rtHolder_1);
		
		rtHolder_1.setBackground(new Color(51, 204, 0));
		rtHolder_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		btnPrint.addActionListener(new ActionListener() {
			Date date =new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yy");
            String newDate = ft.format(date);
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	boolean packagesFound=false;
            	
            	
            	
            	
            	toPrint.setFont(new Font("Monospaced",Font.PLAIN,12));
 
            	toPrint.setLineWrap(true);
            	JScrollPane sp = new JScrollPane();
            	sp.setViewportView(toPrint);
            	sp.setBounds(10,300,500,250);
            	contentPane_1.add(sp);
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
            	
            	List<Stop> stops = new ArrayList<Stop>();
            	ArrayList<Package> packages = new ArrayList<Package>();
            	System.out.println("Running");
            	for(JCheckBox c: checkBoxes){
            		if(c.isSelected()){
            			 			
            			stops = manager.getStopsFromRoute(c.getText());
            			
            			
            		
            		
            	
            	
            	
            	if(stops.size()==0){
            		JOptionPane.showMessageDialog(null,"No stops were found. Please add stops before Printing");
            		return;
            	}
            	
            	
            	
            	
            	
            	
            	//A standard piece of papaer is (470 X 646) pixels
            	//136 spaces for the width
            	JLabel message = new JLabel("Please review the routes and select print again.");
            	message.setForeground(Color.WHITE);
            	message.setBounds(50,250,800,20);
            	contentPane_1.add(message);
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	String equ = "======================================" +
        				"===========================\n";
            	String newHead;
            	String tab ="       ";
            	String sign = "________________________\n\n";
            	
            	String fieldHeader= "Last"+tab+"First"+tab+"Box#."+tab+"Track#"+tab+"Sign Here";
            	
            	addSpacing(33-c.getText().length());
            	toPrint.setText(toPrint.getText()+"Route: "+c.getText()+"\n");
            	
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
            		
            		packages = (ArrayList<Package>) manager.getPackagesFromStop(s.getName());
            		
            		
            		/*packages.add(new Package("Thomas","Aehirng", "asdfadsfgdfgsdb@grizzlies.adams.ede", date,"93","234234235346541111532"));
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
            		*/
            	
            		
            		
            		
            		
            		

            		
            		
            		String[] packageArray = new String[packages.size()];
            		for(Package p: packages){
            			packageArray[packages.indexOf(p)]=p.getLName();
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
            			
            			
            			String printName = "Print Name "+sign+"\n";
            			addSpacing(68-printName.length());
            			toPrint.setText(toPrint.getText()+printName);
            		
          
            		}
            	
            	
             	
           		
            
             	
            	
             	
          }
           toPrint.setText(toPrint.getText()+"\n\n\n\n");
         
        }
            	
        }
            	setBounds(100,100,535,600);
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
