


package com.client.richardson;
 
<<<<<<< HEAD
//hi
=======

>>>>>>> origin/Nick
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
 
/**
 * TableDemo is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */
public class Table extends JPanel {
    private boolean DEBUG = false;
<<<<<<< HEAD
    private boolean delivered= true;
=======
    private boolean delivered=false;
    private boolean print=false;
>>>>>>> origin/Nick
    private boolean picked_up;
    private String pickdate;
    
   
    
    public Table() {
        super(new GridLayout(1,0));
 
        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
<<<<<<< HEAD
 
=======
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(6).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(6).setResizable(false);
        
>>>>>>> origin/Nick
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
<<<<<<< HEAD
=======
        
>>>>>>> origin/Nick
       
    }
 
    class MyTableModel extends AbstractTableModel {
    	  Date date =new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
	String pickdate=ft.format(date);
	
	
<<<<<<< HEAD
        private String[] columnNames = {"First Name",
=======
        private String[] columnNames = {"Print" ,
        		"First Name",
>>>>>>> origin/Nick
                "Last Name",
                "Stop",
                "Tracking #",
                "Date",
                "Delivered"};
        
        private Object[][] data = {
<<<<<<< HEAD
                {"Kathy", "Smith","Rex",
                    "5452548313",  ft.format(date), new Boolean(false)},
                   {"John", "Doe","SUB", 
                     "5846421596", ft.format(date), new Boolean(true)},
                   {"Sue", "Black","Bookstore",
                     "2684359112",  ft.format(date), new Boolean(false)},
                   {"Jane", "White", "Plachy",
                     "2059872641", ft.format(date), new Boolean(true)},
                   {"Joe", "Brown","SUB",
                     "1024861834",  ft.format(date), new Boolean(false)}
=======
                {print,"Kathy", "Smith","Rex",
                    "5452548313",  ft.format(date), delivered},
                   {print, "John", "Doe","SUB", 
                     "5846421596", ft.format(date), delivered},
                   {print, "Sue", "Black","Bookstore",
                     "2684359112",  ft.format(date), delivered},
                   {print, "Jane", "White", "Plachy",
                     "2059872641", ft.format(date), delivered},
                   {print, "Joe", "Brown","SUB",
                     "1024861834",  ft.format(date), delivered}
>>>>>>> origin/Nick
                   };
 
        
        public int getColumnCount() {
            return columnNames.length;
        }
 
        public int getRowCount() {
            return data.length;
        }
 
        public String getColumnName(int col) {
            return columnNames[col];
        }
 
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
 
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
<<<<<<< HEAD
            if (col < 2) {
                return false;
            } else {
                return true;
=======
            if (col == 0) {
            	
                return true;
            } if(col<6) {
                return false;
            } else{
            	return true;
>>>>>>> origin/Nick
            }
        }
 
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }
 
            data[row][col] = value;
            fireTableCellUpdated(row, col);
 
            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
 
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();
 
            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        Table newContentPane = new Table();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
<<<<<<< HEAD
=======

>>>>>>> origin/Nick
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

