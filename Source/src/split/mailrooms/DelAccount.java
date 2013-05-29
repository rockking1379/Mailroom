package split.mailrooms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.client.common.DatabaseManager;

public class DelAccount extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
			DatabaseManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelAccount frame = new DelAccount(new DatabaseManager());
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
	public DelAccount(DatabaseManager manager) {
		this.manager=manager;
		setTitle("Delete an Account");
		setBackground(new Color(0, 128, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 136);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseSelectAn = new JLabel("Please Input the username of the account you would like to delete.");
		lblPleaseSelectAn.setForeground(new Color(255, 255, 255));
		lblPleaseSelectAn.setBounds(5, 11, 379, 14);
		contentPane.add(lblPleaseSelectAn);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(142, 64, 89, 23);
		btnDelete.addActionListener(new DeletAccountListener());
		contentPane.add(btnDelete);
		
		textField = new JTextField();
		textField.setBounds(74, 36, 229, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	public class DeletAccountListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			manager.deleteUser(textField.getText());
			new AddAccount(manager);
			dispose();
			
			
		}
		
	}
}
