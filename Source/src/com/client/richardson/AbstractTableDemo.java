package com.client.richardson;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class AbstractTableDemo extends JPanel{
    private boolean DEBUG = false;
    private boolean delivered=false;
    private boolean picked_up=false;;

    
	public AbstractTableDemo() {
 super(new GridLayout(1,0));
 final JTable table = new JTable(new MyTableModel());
 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
 table.setFillsViewportHeight(true);
 //Object[] values = {"String", 10, 20.0, 30.2, new Boolean(false)};
 MyTableModel a = (MyTableModel) table.getModel();
 //a.insertData(values);
 Object[] row2= a.row2;
a.insertData(row2);
Object[] row3= a.row3;
Object[] row4= a.row4;
Object[] row5= a.row5;
a.insertData(row3);
a.insertData(row4);
a.insertData(row5);
table.setAutoCreateRowSorter(true);
 

 //Create the scroll pane and add the table to it.

 JScrollPane scrollPane = new JScrollPane(table);

 //Add the scroll pane to this panel.

 add(scrollPane);
 }


class MyTableModel extends AbstractTableModel {
	  Date date =new Date();
	  Object[][] data1 = null;
	    SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
	    String pickdate="";
		String trackNum= "0214123574841245546";
		String L4= trackNum.substring(trackNum.length()-4,trackNum.length());
		
 private String[] columnNames = {"First Name", "Last Name", "Box #", 
 		"Tracking #", "Date", "Delivered", "Picked Up"};
 private Vector data = new Vector();
 public final Object[] row1 ={"Kathy", "Smith", 678, L4,  ft.format(date), delivered, picked_up };
 public final Object[] row2 =  {"John", "Doe", 1041, L4, ft.format(date), delivered, picked_up };
 public final Object[] row3 ={"Sue", "Black", 386, L4,  ft.format(date), delivered,picked_up };
 public final Object[] row4 ={"Jane", "White", 1437, L4, ft.format(date), delivered, picked_up };
 public final Object[] row5 ={"Joe", "Brown",  19, L4,  ft.format(date), delivered,  picked_up };
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
	 if (col < 5&& col>=3) {
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


 }

 private static void createAndShowGUI(){
 JFrame frame = new JFrame("Abstract Table Demo");
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 AbstractTableDemo newContentPane = new AbstractTableDemo();
 frame.setContentPane(newContentPane);

 //Display the window.
 frame.pack();
 frame.setVisible(true);
 }

 public static void main(String[] args){
 javax.swing.SwingUtilities.invokeLater(new Runnable() {
public void run() {
 createAndShowGUI();
 }
 });
 }

}