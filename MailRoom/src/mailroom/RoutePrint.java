package mailRoom;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutePrint extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoutePrint frame = new RoutePrint();
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
	public RoutePrint() {
		setTitle("Print a Route");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/compass.jpg"));
		setIconImage(icon.getImage());
		//setIconImage(Toolkit.getDefaultToolkit().getImage("src\\compass.jpg"));
		setBackground(new Color(0, 102, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	Print printScreen = new Print();
            	printScreen.setVisible(true);
            }
        });
	}
}
