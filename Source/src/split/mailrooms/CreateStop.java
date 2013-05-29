package split.mailrooms;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.client.common.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;


public class CreateStop extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	ArrayList<Route> routes;
	JFrame frame;
	String loggedIn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateStop frame = new CreateStop(new DatabaseManager(),"Someone",new JFrame());
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
	public CreateStop(final DatabaseManager manager,final String loggedIn,final JFrame frame) {
		this.frame=frame;
		ArrayList<Route> routes;
		setResizable(false);
		setVisible(true);
		setTitle("Create Stop");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 316, 221);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNameOfThe = new JLabel("Name of the New Stop:");
		lblNameOfThe.setForeground(new Color(255, 255, 255));
		lblNameOfThe.setBounds(83, 31, 163, 14);
		contentPane.add(lblNameOfThe);
		
		textField = new JTextField();
		textField.setBounds(61, 56, 180, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSelectRouteTo = new JLabel("Select Route to add Stop To:");
		lblSelectRouteTo.setForeground(new Color(255, 255, 255));
		lblSelectRouteTo.setBounds(71, 88, 163, 14);
		contentPane.add(lblSelectRouteTo);
		
		routes=(ArrayList<Route>)manager.getRoutes();
		
		String[] rtNames = new String[routes.size()];
		for(Route r: routes){
			rtNames[routes.indexOf(r)] =r.getName();
			
		}
		
		
		final JComboBox comboBox = new JComboBox();
		DefaultComboBoxModel model = new DefaultComboBoxModel(rtNames);
		comboBox.setBounds(94, 113, 116, 20);
		comboBox.setModel(model);
		
		contentPane.add(comboBox);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(104, 144, 89, 23);
		contentPane.add(btnCreate);
		
		final JCheckBox chcforStudent = new JCheckBox("Student Route");
		chcforStudent.setForeground(new Color(255, 255, 255));
		chcforStudent.setBackground(new Color(0, 102, 0));
		chcforStudent.setBounds(207, 163, 97, 23);
		contentPane.add(chcforStudent);
		
		btnCreate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Stop> stops;
				int largestSequence=0;
				if(comboBox.getSelectedIndex()==-1){
					
					stops = (ArrayList<Stop>) manager.getStopsFromRoute("unassigned");
					for(Stop s: stops){
						if(s.getroutePos()>largestSequence){
							largestSequence=s.getroutePos();
						}
					}
					
					
					manager.addStop(textField.getText(), false, "unassigned",largestSequence+1,chcforStudent.isSelected());
					
				}
				else{
					
					String route = (String)comboBox.getSelectedItem();
					stops=(ArrayList<Stop>) manager.getStopsFromRoute(route);
					
					for(Stop s: stops){
						if(s.getroutePos()>largestSequence){
							largestSequence=s.getroutePos();
						}
					}
					
					
					manager.addStop(textField.getText(), true, route,largestSequence+1,chcforStudent.isSelected());
				}
				
				if(frame.getTitle().equals("Scan My Package")){
					new ScanPackage(manager,loggedIn);
				}
				else{
					JFrame f = new JFrame("Create A Route");
	                
	                
	                RouteMaker dual = new RouteMaker(manager,f,loggedIn);
	                
	                String[] stopNames= new String[manager.getStops().size()];
	                
	                
	                
	                for(Stop s: manager.getStops()){
	                	stopNames[manager.getStops().indexOf(s)] = s.getName();
	                }
	                try{
	                dual.addSourceElements(stopNames);
	            	}
	                catch(NullPointerException ex){
	            		
	            		
	            		
	            	}
	               
	                f.getContentPane().add(dual, BorderLayout.CENTER);
	                f.setSize(493, 360);
	                f.setVisible(true);
	                f.setResizable(false);
				}
					
				manager.loadStops();
				dispose();
			}
			
			
		});
	}
}
