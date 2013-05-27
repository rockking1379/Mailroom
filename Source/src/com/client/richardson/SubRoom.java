package com.client.richardson;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;


import com.client.common.*;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
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
	private DatabaseManager manager;
	
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
		manager=new DatabaseManager();
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Untitled.jpg"));
		setTitle("Student Union Mail Room");
		//ImageIcon icon= new ImageIcon(getClass().getResource("/image/Untitled.jpg"));
		//setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 523);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
		
		 SUBTable table = new SUBTable();
		 final JTable table1 = table.table;
		 
		
         table1.setBounds(10, 11, 688, 434);
         contentPane.add(table1);
         table1.setOpaque(true);
         getContentPane().repaint();
        
         

         SearchField = new JTextField();
         SearchField.setBounds(20, 456, 297, 20);
         contentPane.add(SearchField);
         SearchField.setColumns(10);
         SearchField.addActionListener(new ActionListener() {

        	    public void actionPerformed(ActionEvent e) {

        	        String value = SearchField.getText();

        	        for (int row = 0; row <= table1.getRowCount() - 1; row++) {

        	            for (int col = 0; col <= table1.getColumnCount() - 1; col++) {

        	                if (value.equals(table1.getValueAt(row, col))) {

        	                    // this will automatically set the view of the scroll in the location of the value
        	                    table1.scrollRectToVisible(table1.getCellRect(row, 0, true));

        	                    // this will automatically set the focus of the searched/selected row/value
        	                    table1.setRowSelectionInterval(row, row);

        	                    for (int i = 0; i <= table1.getColumnCount() - 1; i++) {

        	                        table1.getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
        	                      
        	                    } 
        	                } 
        	               
        	            }
        	        }
        	      
        	    }
        	});
         
         JButton btnSearch = new JButton("Search");
         btnSearch.setBounds(345, 455, 89, 23);
         contentPane.add(btnSearch);
         
         JButton btnAdvancedSearch = new JButton("Advanced Search");

         JButton btnRefresh = new JButton("Refresh");
         btnRefresh.setBounds(609, 455, 89, 23);
         contentPane.add(btnRefresh);

         btnAdvancedSearch.setBounds(455, 455, 144, 23);
         contentPane.add(btnAdvancedSearch);

       
         
         btnAdvancedSearch.addActionListener(new ActionListener() {
	    	 
             public void actionPerformed(ActionEvent e)
             {
                 SUBAdvSearch search = new SUBAdvSearch();
                 search.setVisible(true);
             }
         });
	}
	private class HighlightRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		    // everything as usual
		    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		    // added behavior
		    if(row == table.getSelectedRow()) {

		        // this will customize that kind of border that will be use to highlight a row
		        setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.GREEN)); 
		        //table.getColumnClass(column);
		    }

		    return this;
		  }
		}
}
