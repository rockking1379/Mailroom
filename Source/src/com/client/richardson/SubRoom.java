package com.client.richardson;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;



import com.client.common.DatabaseManager;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 
public class SubRoom extends JPanel {
	
	 
	    private boolean delivered=false;
	    private boolean print=false;

	    private boolean picked_up;
	    private String pickdate;
	    Table displayTable;

    private boolean DEBUG = false;
    private JTable table;
    private JTextField filterText;
    private TableRowSorter<MyTableModel> sorter;
    private DatabaseManager manager;
    TableColumn tc;  
    MyItemListener it;  
    CheckBoxHeader cbh;  
    CheckBoxHeader rendererComponent;  
      
    class MyItemListener implements ItemListener{     
        public void itemStateChanged(ItemEvent e){    
            Object source = e.getSource();     
            if (source instanceof AbstractButton == false) return;     
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;     
            for(int x = 0, y = table.getRowCount(); x < y; x++){     
              table.setValueAt(new Boolean(checked),x,0);     
            }  
        }     
    }  
      
    class MyMouseListener extends MouseAdapter{  
        public void mouseClicked(MouseEvent mouseEvent) {  
            int checkedCount = 0;  
            rendererComponent.removeItemListener(it);  
            if (rendererComponent instanceof JCheckBox) {  
                boolean[] flags = new boolean[table.getRowCount()];  
                for (int i = 0; i < table.getRowCount(); i++) {  
                    flags[i] = ((Boolean) table.getValueAt(i, 0)).booleanValue();  
                    if(flags[i]){  
                        checkedCount++;  
                    }  
                }  
                if(checkedCount== table.getRowCount()){  
                    ((JCheckBox)rendererComponent).setSelected(true);                 
                }  
                if(checkedCount!= table.getRowCount()){  
                    ((JCheckBox)rendererComponent).setSelected(false);      
                }  
            }  
            rendererComponent.addItemListener(it);  
            table.getTableHeader().repaint();  
        }  
    }  
    public CheckBoxHeader getRendererComponent() {  
        return rendererComponent;  
    }  
    public void setRendererComponent(CheckBoxHeader rendererComponent) {  
        rendererComponent.setText("Delivered");  
        this.rendererComponent = rendererComponent;  
    }  
    
    class CheckBoxHeader extends JCheckBox implements TableCellRenderer, MouseListener {     
        protected int column;     
        protected boolean mousePressed = false;    
        ItemListener it1;   
        public CheckBoxHeader(ItemListener itemListener) {     
            setRendererComponent(this);  
            this.it1 = itemListener;  
            rendererComponent.addItemListener(it1);     
        }     
        public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {  
          if (table != null) {     
            JTableHeader header = table.getTableHeader();    
            if (header != null) {     
              rendererComponent.setForeground(header.getForeground());     
              rendererComponent.setBackground(header.getBackground());     
              rendererComponent.setFont(header.getFont());  
              header.addMouseListener(rendererComponent);     
            }     
          }     
          setColumn(column);     
          setBorder(UIManager.getBorder("TableHeader.cellBorder"));     
          return rendererComponent;     
        }     
        protected void setColumn(int column) {     
          this.column = column;     
        }     
        public int getColumn() {     
          return column;     
        }     
        protected void handleClickEvent(MouseEvent e) {     
          if (mousePressed) {     
            mousePressed=false;           
            JTableHeader header = (JTableHeader)(e.getSource());     
            JTable tableView = header.getTable();     
            TableColumnModel columnModel = tableView.getColumnModel();     
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());     
            int column = tableView.convertColumnIndexToModel(viewColumn);     
            if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {     
                doClick();     
            }   
          }     
        }     
        public void mouseClicked(MouseEvent e) {     
          handleClickEvent(e);     
          ((JTableHeader)e.getSource()).repaint();     
        }     
        public void mousePressed(MouseEvent e) {     
          mousePressed = true;     
        }     
        public void mouseReleased(MouseEvent e) {     
        }     
        public void mouseEntered(MouseEvent e) {     
        }     
        public void mouseExited(MouseEvent e) {     
        }   
      }   
 
    public SubRoom() {
        super();
        setBackground(new Color(0, 102, 0));
 
        //Create a table with a sorter.
        MyTableModel model = new MyTableModel();
        sorter = new TableRowSorter<MyTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

       MyTableModel atable = (MyTableModel) table.getModel();
       Object[] values = atable.row1;
       Object[] row2= atable.row2;
       Object[] row3= atable.row3;
       Object[] row4= atable.row4;
       Object[] row5= atable.row5;


       atable.insertData(values);
       atable.insertData(row2);
       atable.insertData(row3); 
       atable.insertData(row4);
       atable.insertData(row5);
       table.setFillsViewportHeight(true);
       
       tc = table.getColumnModel().getColumn(0);     
       tc.setCellEditor(table.getDefaultEditor(Boolean.class));     
       tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));     
       it = new MyItemListener();  
       cbh = new CheckBoxHeader(it);   
       tc.setHeaderRenderer(cbh);   
       table.addMouseListener(new MyMouseListener());   
       
 
        //For the purposes of this example, better to have a single
        //selection.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
        //When selection changes, provide user with row numbers for
        
        //both view and model.
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = table.getSelectedRow();
                        if (viewRow < 0) {
                            //Selection got filtered away.
                          
                        } else {
                            int modelRow =  table.convertRowIndexToModel(viewRow);
                            
                        }
                    }
                }
        );
        setLayout(null);
 
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 67, 641, 435);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
 
        //Create a separate form for filterText and statusText
        JPanel form = new JPanel();
        form.setBackground(new Color(0, 102, 0));
        form.setBounds(0, 513, 661, 62);
        form.setLayout(null);
        JLabel l1 = new JLabel("Search:", SwingConstants.LEFT);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        l1.setForeground(new Color(255, 255, 255));
        l1.setBounds(10, 10, 86, 20);
        form.add(l1);
        filterText = new JTextField();
        filterText.setBounds(73, 11, 210, 20);
        //Whenever filterText changes, invoke newFilter.
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        l1.setLabelFor(filterText);
        form.add(filterText);

        add(form);
        
        JButton btnAdvancedSearch = new JButton("Advanced Search");
        btnAdvancedSearch.setBounds(382, 10, 155, 23);
        form.add(btnAdvancedSearch);
        btnAdvancedSearch.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                SUBAdvSearch search = new SUBAdvSearch(displayTable);
                search.setVisible(true);
            }
        });
        
        JButton btnRefesh = new JButton("Refresh");
        btnRefesh.setBounds(562, 10, 89, 23);
        form.add(btnRefesh);
        
        JLabel lblPleaseSelectA = new JLabel("Please Select a Stop: ");
        lblPleaseSelectA.setForeground(Color.WHITE);
        lblPleaseSelectA.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPleaseSelectA.setBounds(117, 25, 136, 14);
        add(lblPleaseSelectA);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(268, 23, 174, 20);
        add(comboBox);
    }
 
    /** 
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
        RowFilter<MyTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText(), table.getSelectedColumns());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
 
 
 
 
    class MyTableModel extends AbstractTableModel {
    	  Date date =new Date();
    	  Object[][] data1 = null;
    	    SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
    	    String pickdate="";
    		String trackNum= "0214123574841245546";
    		String trackNum1= "021412357484124465465455875346";
    		String L4= trackNum.substring(trackNum.length()-4,trackNum.length());
    		String L41= trackNum1.substring(trackNum1.length()-4,trackNum1.length());
    		
     private String[] columnNames = {"First Name", "Last Name", "Box #", "Tracking #", "Carrier","Date", "Delivered", "Picked Up"};
     private Vector data = new Vector();
     public final Object[] row1 ={delivered,"Kathy", "Smith", 678, L4, "FedEx", ft.format(date),  picked_up };
     public final Object[] row2 =  {delivered,"John", "Doe", 1041, L41, "USPS Mail", ft.format(date),  picked_up };
     public final Object[] row3 ={ delivered,"Sue", "Black", 386, L4,  "FedEx Express", ft.format(date),picked_up };
     public final Object[] row4 ={ delivered,"Jane", "White", 1437, L41, "DHL", ft.format(date), picked_up };
     public final Object[] row5 ={ delivered,"Joe", "Brown",  19, L4, "FedEx", ft.format(date),  picked_up };

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
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Student Union Mail Room");
        frame.setSize(667, 590);
        //ImageIcon icon= new ImageIcon(frame.getClass().getResource("/image/Untitled.jpg"));
		//frame.setIconImage(icon.getImage());
       
        SubRoom newContentPane = new SubRoom();
        newContentPane.setOpaque(true); 
       
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
      
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
