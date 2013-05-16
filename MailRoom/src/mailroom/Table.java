package mailroom;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class Table extends JPanel {
    private boolean DEBUG = false;
 
    public Table() {
        super(new GridLayout(1,0));
        Date date =new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
 
        String[] columnNames = {"First Name",
                                "Last Name",
                                "Stop",
                                "Box #",
                                "Tracking #",
                                "Date",
                                "Delivered"};
 
        Object[][] data = {
        {"Kathy", "Smith","Rex",
         "", "5452548313",  ft.format(date), new Boolean(false)},
        {"John", "Doe","SUB", 
         "1041", "5846421596", ft.format(date), new Boolean(true)},
        {"Sue", "Black","Bookstore",
         "", "2684359112",  ft.format(date), new Boolean(false)},
        {"Jane", "White", "Plachy",
         "", "2059872641", ft.format(date), new Boolean(true)},
        {"Joe", "Brown","SUB",
         "19", "1024861834",  ft.format(date), new Boolean(false)}
        };
 
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(558, 355));
        table.setFillsViewportHeight(true);
 
        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
 
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
 
        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
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
        Table newContentPane = new Table();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
      
    }
 
   /* public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }*/
}
