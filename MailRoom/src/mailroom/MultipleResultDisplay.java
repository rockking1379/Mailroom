package mailroom;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class MultipleResultDisplay extends JFrame {

	private JPanel contentPane;
	ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	/**
	 * Launch the application.
	 */
	ArrayList<Person> results;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultipleResultDisplay frame = new MultipleResultDisplay(new ScanPackage(new DatabaseManager()),new ArrayList());
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
	public MultipleResultDisplay(final ScanPackage frame,final List<Person> results) {
		this.results=(ArrayList<Person>) results;
		setBackground(new Color(0, 128, 0));
		setTitle("Multiple Results");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 323);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("There were multiple results to your search.");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 11, 434, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblrnPleaseSelect = new JLabel("Please select the person you would like to use.");
		lblrnPleaseSelect.setForeground(new Color(255, 255, 255));
		lblrnPleaseSelect.setBounds(43, 82, 361, 14);
		contentPane.add(lblrnPleaseSelect);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBounds(53, 107, 361, 139);
		contentPane.add(resultPanel);
		resultPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnSelect = new JButton("OK");
		btnSelect.setBounds(315, 257, 89, 23);
		contentPane.add(btnSelect);
		
		
		final ButtonGroup group = new ButtonGroup();
		
		for(Person p: results){
			String s = p.getLastName()+" "+p.getFirstName()+" "+p.getBox();
			JCheckBox c = new JCheckBox(s);
			checkBoxes.add(c);
			group.add(c);
			resultPanel.add(c);
		}
		
		btnSelect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JCheckBox c = (JCheckBox) group.getSelection();
				int i = checkBoxes.indexOf(c);
				Person selected = results.get(i);
				frame.getBoxText().setText(selected.getBox());
				frame.getComboBox().setSelectedItem(selected.getStop());
				dispose();
				
					
				}
				
			
			
		});
	}
}
