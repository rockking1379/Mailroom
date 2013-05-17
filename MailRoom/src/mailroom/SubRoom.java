package mailroom;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SubRoom extends JFrame {

	private JPanel contentPane;
	private JTextField SearchField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubRoom frame = new SubRoom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public SubRoom() throws IOException {
		
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Untitled.jpg"));
		setTitle("Student Union Mail Room");
		ImageIcon icon= new ImageIcon(getClass().getResource("/image/Untitled.jpg"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 523);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
		
		 SUBTable newContentPane = new SUBTable();
         newContentPane.setBounds(10, 11, 688, 434);
         contentPane.add(newContentPane);
         newContentPane.setOpaque(true);
         getContentPane().repaint();
         
       
         
         SearchField = new JTextField();
         SearchField.setBounds(20, 456, 297, 20);
         contentPane.add(SearchField);
         SearchField.setColumns(10);
         
         JButton btnSearch = new JButton("Search");
         btnSearch.setBounds(345, 455, 89, 23);
         contentPane.add(btnSearch);
         
         JButton btnAdvancedSearch = new JButton("Advanced Search");
         btnAdvancedSearch.setBounds(444, 455, 144, 23);
         contentPane.add(btnAdvancedSearch);
         
         JButton btnRefresh = new JButton("Refresh");
         btnRefresh.setBounds(609, 456, 89, 23);
         contentPane.add(btnRefresh);
       
         
         btnAdvancedSearch.addActionListener(new ActionListener() {
	    	 
             public void actionPerformed(ActionEvent e)
             {
                 AdvSearch search = new AdvSearch();
                 search.setVisible(true);
             }
         });
	}
}
