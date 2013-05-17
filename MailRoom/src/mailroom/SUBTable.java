package mailroom;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import mailroom.TableDemo.MyTableModel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class SUBTable extends JPanel {
    private boolean DEBUG = false;
    private boolean delivered= true;
    private boolean picked_up=false;;
    private String pickdate;
    
    public SUBTable() {
        super(new GridLayout(1,0));
 
        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
 
    class MyTableModel extends AbstractTableModel {
    	  Date date =new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
	String pickdate="";
	String trackNum= "0214123574841245546";
	String L4= trackNum.substring(trackNum.length()-4,trackNum.length());
    
    	
        private String[] columnNames = {"First Name",
                "Last Name",
                "Box #",
                "Tracking #",
                "Date",
                "Delivered",
                "Picked Up",
                "Pick Up Date"};
        
        private Object[][] data = {
        		{"Kathy", "Smith",
        	         "678", L4,  ft.format(date), delivered, picked_up,pickdate},
        	        {"John", "Doe", 
        	         "1041", L4, ft.format(date), delivered, picked_up,pickdate},
        	        {"Sue", "Black",
        	         "386", L4,  ft.format(date), delivered,picked_up,pickdate},
        	        {"Jane", "White",
        	         "1437", L4, ft.format(date), delivered, picked_up,pickdate},
        	        {"Joe", "Brown",
        	         "19", L4,  ft.format(date), delivered,  picked_up,pickdate}
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
            if (col < 6) {
                return false;
            } 
            if (col >6){
            	return false;
            }
            else {
                return true;
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
            System.out.println(value);
           
            picked_up=(boolean) value;
            System.out.println(picked_up);
            fireTableCellUpdated(row, col);
            
            if(picked_up==false){
            	pickdate="";
            	System.out.println("In Flase");
            	Object value1 = null;
            	value1="";
				data[row][col+1] = value1;
           	 	
            	 fireTableDataChanged();
            }
            if(picked_up==true){
            	pickdate=ft.format(date);
            	System.out.println("In True");
            	Object value1 = null;
            	value1=ft.format(date);
				data[row][col+1] = value1;
           	 
                 fireTableDataChanged();
            }
 
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
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        SUBTable newContentPane = new SUBTable();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
      
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

	public boolean isPicked_up() {
		return picked_up;
	}

	public boolean setPicked_up(boolean picked_up) {
		this.picked_up = picked_up;
		return picked_up;
	}

	public String getPickdate() {
		return pickdate;
	}

	public void setPickdate(String pickdate) {
		this.pickdate = pickdate;
	}
}
