package split.mailrooms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import com.client.common.*;
public class CreateCarrier extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
			DatabaseManager manager;
			String loggedIn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCarrier frame = new CreateCarrier(new DatabaseManager(),"Someone");
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
	public CreateCarrier(DatabaseManager manager,String loggedIn) {
		this.loggedIn=loggedIn;
		this.manager=manager;
		setResizable(false);
		setTitle("Add Carrier");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 261, 124);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterNameOf = new JLabel("Enter Name of the New Carrier:");
		lblEnterNameOf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnterNameOf.setForeground(new Color(255, 255, 255));
		lblEnterNameOf.setBounds(43, 11, 204, 14);
		contentPane.add(lblEnterNameOf);
		
		textField = new JTextField();
		textField.setBounds(53, 36, 156, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(83, 67, 89, 23);
		btnAdd.addActionListener(new CreateCarrierListener());
		contentPane.add(btnAdd);
	}
	
	public class CreateCarrierListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			manager.addCourier(textField.getText(),true);
			new ScanPackage(manager,loggedIn).setVisible(true);
			dispose();
		}
		
	}
}
