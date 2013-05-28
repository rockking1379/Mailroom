package com.client.richardson;





 
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import com.client.common.*;
import com.client.common.Package;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
 
/** 
 * TableRenderDemo is just like TableDemo, except that it
 * explicitly initializes column sizes and it uses a combo box
 * as an editor for the Sport column.
 */
public class Table extends JPanel {
    private boolean DEBUG = false;
    private boolean delivered=false;
    private boolean print=false;
    MyTableModel atable;
    private boolean picked_up;
    private String pickdate;
    private JTable table;
    public static void main(String[] args) {
   	 JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        Table newContentPane = new Table(new DatabaseManager());
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
   }
    DatabaseManager manager;
    public Table(DatabaseManager manager) {
    	
        super(new GridLayout(1,0));
        
        this.manager=manager;
 
        MyTableModel model = new MyTableModel();
        
        table = new JTable(model);
       
        table.setPreferredScrollableViewportSize(new Dimension(716, 366));
        

        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(144);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(82);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(6).setResizable(false);
        
        

       atable = (MyTableModel) table.getModel();
       
       ArrayList<Package> undilivered = (ArrayList<Package>) manager.findPackage(false,false);
      
       for(Package p: undilivered){
    	  atable.insertData(new Object[] {false,p.getFName(),p.getLName(),p.getStop(),p.getTrackNum(),p.getDate(),p.getUser(),false});
       }
   
     /* atable.insertData(atable.row1);
       atable.insertData(atable.row2);
       atable.insertData(atable.row3); 
       atable.insertData(atable.row4);
       atable.insertData(atable.row5a);
       atable.insertData(atable.yourmom);
       */
       table.setFillsViewportHeight(true);
 
       	table.setAutoCreateRowSorter(true);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Set up column sizes.
        //initColumnSizes(table);
 
        //Fiddle with the Sport column's cell editors/renderers.
        setUpSportColumn(table, table.getColumnModel().getColumn(3));
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
 
    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.row1;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();
 
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
 
            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
 
            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
 
            if (DEBUG) {
                System.out.println("Initializing width of column "
                                   + i + ". "
                                   + "headerWidth = " + headerWidth
                                   + "; cellWidth = " + cellWidth);
            }
 
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }
 
    public void setUpSportColumn(JTable table, TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"AAO", "Academic Affairs", "Admissions\t", "AITC", "Alumni/Foundation", "Art", "AS&F", "Bookstore", "Business Office", "Communications", 
		"Community Partnership", "Computing Services", "Counseling & Career", "Counselor Education", "EEO", "English/ Communication", "Enrollment", "Extended Studies", "Facilities Office", "Facilities Warehouse", 
		"Finance/ Administration", "Financial Aid", "Gingerbread House", "Graduate School", "HGPPSL", "Hold for Pickup", "Housing", "HPPE", "Human Resources", "Institutional Research", "Library", "Museum", "Music",
		"Nursing", "One Stop", "Payroll", "Plachy", "Police Department", "President", "Print Shop", "Purchasing", "Radio Station", "Records", "REX", "School of Business", "SMT", "SODEXO", "Student Affairs", 
		"Student Life", "SUB Office", "SUB Mailroom", "SVP Enrollment Manager", "Teacher Education", "Theatre", "Title V", "Upward Bound"}));
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
 
        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }
 
    class MyTableModel extends AbstractTableModel {
    	 Date date =new Date();
  	    SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
  		String pickdate=ft.format(date);
  		
   private String[] columnNames = {"Print" ,"First Name", "Last Name", "Stop","Tracking #","Carrier", "Date","Username","Delivered"};
   
   private Vector data = new Vector();
   public final Object[] row1 ={print,"Kathy", "Smith","Rex","5452548313","",  ft.format(date),"", delivered};
  /* public final Object[] row2 = {print, "John", "Doe","SUB",  "5846421596", ft.format(date),"", delivered};
   public final Object[] row3 = {print, "Sue", "Black","Bookstore", "2684359112",  ft.format(date),"", delivered};
   public final Object[] row4 = {print, "Jane", "White", "Plachy", "2059872641", ft.format(date),"", delivered};
   public final Object[] row5a = {print, "Joe", "Brown","SUB","1024861834",  ft.format(date),"", delivered};
   public final Object[] yourmom = {print, "Jill", "Brown","REX","1024544861834",  ft.format(date),"", delivered};*/

   @Override

   public int getColumnCount() {
   return columnNames.length;
   }

  @Override

   public int getRowCount() {
   return data.size();
   }

  @Override
   public Object getValueAt(int row, int col) {
   return ((Vector) data.get(row)).get(col);
   }

   public String getColumnName(int col){
   return columnNames[col];
   }

   public Class getColumnClass(int c){
   return getValueAt(0,c).getClass();
   }
   

   public void setValueAt(Object value, int row, int col){
  ((Vector) data.get(row)).setElementAt(value, col);
   fireTableCellUpdated(row,col);
   }

   public boolean isCellEditable(int row, int col){
  	 if (col < 7&& col>=4) {
           return false;
       } else {
           return true;
       }
  }

   public void insertData(Object[] values){
   data.add(new Vector());
   for(int i =0; i<values.length; i++){
   ((Vector) data.get(data.size()-1)).add(values[i]);
   }
   fireTableDataChanged();
   }

   public void removeRow(int row){
   data.removeElementAt(row);
   fireTableDataChanged();
   }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    }
    
    public void setSearchResults(ArrayList<Package> results){
    	//int i=atable.getRowCount()-1;
    	
    	if(results.size()==0){
    		atable.insertData(new Object []  {false,"","","","","","",false});
			atable.removeRow(0);
			
    	
    		
    	}
    	
    	atable.insertData(new Object []  {false,"","","","","","",false});
    	while(atable.getRowCount()>=1 || !(atable.getValueAt(0, 5).equals(""))){
    		
    		atable.removeRow(atable.getRowCount()-1);
    		System.out.println(atable.getRowCount()-1);
    		if(atable.getRowCount()==1 ){
    			atable.insertData(new Object []  {false,"","","","","","",false});
    			atable.removeRow(0);
    			break;
    		}
    	}
    	
    	for(Package p: results){
    		
    		atable.insertData(new Object[]{false,p.getFName(),p.getLName(),p.getStop(),p.getTrackNum(),p.getDate(),p.getUser(),false});
    		
    		
    	}
    	if(atable.getRowCount()>1){
    		atable.removeRow(0);
    	}
    	
    }
    
    
}
