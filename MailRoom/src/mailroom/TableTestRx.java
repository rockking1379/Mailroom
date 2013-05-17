package mailroom;
import java.awt.*;  
import javax.swing.*;  
import javax.swing.border.Border;  
import javax.swing.border.EmptyBorder;  
import javax.swing.event.*;  
import javax.swing.table.TableCellRenderer;  
import javax.swing.table.DefaultTableCellRenderer;  
import javax.swing.table.DefaultTableModel;  
   
public class TableTestRx extends JFrame  
{  
    public TableTestRx()  
    {  
        getContentPane().add(new MyTable());  
    }  
   
    public static void main(String[] args)  
    {  
        TableTestRx frame = new TableTestRx();  
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);  
        frame.pack();  
        frame.setVisible(true);  
    }  
   
    public class MyTable extends JTable  
    {  
        public MyTable()  
        {  
            super(new MyTableModel());  
            putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);  
            setDefaultRenderer(String.class, new Renderer());  
            DefaultCellEditor dce = new DefaultCellEditor(new JTextField());  
            dce.setClickCountToStart(1);  
            setDefaultEditor(String.class, dce);  
            setOpaque(false);  
            setShowGrid(false);  
            configure();  
        }  
   
        private void configure()  
        {  
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
            setCellSelectionEnabled(true);  
            // Add SelectionListeners to track selection changes  
            // across columns.  
            getColumnModel().getSelectionModel().addListSelectionListener(  
                        new ExploreSelectionListener());  
        }  
   
        private class ExploreSelectionListener implements ListSelectionListener  
        {  
            public void valueChanged(ListSelectionEvent e)  
            {  
                if(!e.getValueIsAdjusting())  
                {  
                    int row = getSelectedRow();  
                    int col = getSelectedColumn();  
                    // Make sure we start with legal values.  
                    while(col < 0) col++;  
                    while(row < 0) row++;  
                    // Find the next editable cell.  
                    while(!isCellEditable(row, col))  
                    {  
                        col++;  
                        if(col > getColumnCount()-1)  
                        {  
                            col = 1;  
                            row = (row == getRowCount()-1) ? 1 : row+1;  
                        }  
                    }  
                    // Select the cell in the table.  
                    final int r = row, c = col;  
                    EventQueue.invokeLater(new Runnable()  
                    {  
                        public void run()  
                        {  
                            changeSelection(r, c, false, false);  
                        }  
                    });  
                    // Edit.  
                    if(isCellEditable(row, col))  
                    {  
                        editCellAt(row, col);  
                        ((JTextField)editorComp).selectAll();  
                        editorComp.requestFocusInWindow();  
                    }  
                }  
            }  
        }  
   
        private class Renderer implements TableCellRenderer  
        {  
            DefaultTableCellRenderer renderer;  
            JTextField textField;  
            protected Border border = new EmptyBorder(1, 1, 1, 1);  
   
            public Renderer()  
            {  
                renderer = new DefaultTableCellRenderer();  
                textField = new JTextField();  
                textField.setHorizontalAlignment(SwingConstants.RIGHT);  
            }  
   
            public Component getTableCellRendererComponent(JTable table,  
                                                           Object value,  
                                                           boolean isSelected,  
                                                           boolean hasFocus,  
                                                           int row, int column)  
            {  
                if (!isCellEditable(row, column))  
                {  
                    renderer.getTableCellRendererComponent(table, value,  
                        isSelected, hasFocus, row, column);  
                    renderer.setHorizontalAlignment(column == 0  
                        ? SwingConstants.LEFT  
                        : SwingConstants.RIGHT);  
                    renderer.setBackground(Color.GRAY.brighter());  
                    renderer.setOpaque(false);  
                    renderer.setFont(  
                        table.getFont().deriveFont(9f).deriveFont(Font.BOLD));  
                    renderer.setForeground(Color.BLACK);  
                    renderer.setBorder(border);  
                    return renderer;  
                }  
                textField.setText(value.toString());  
                return textField;  
            }  
        }  
    }  
   
    class MyTableModel extends DefaultTableModel  
    {  
        String[][] data = new String[][]  
                {  
                        {"", "col 1", "col 2"},  
                        {"row 1", "1", "2"},  
                        {"row 2", "3", "4"},  
                        {"row 3", "5", "6"},  
                };  
   
        public MyTableModel()  
        {  
   
        }  
   
        public int getRowCount() { return 4; }  
   
        public int getColumnCount() { return 3; }  
   
        public Object getValueAt(int row, int column)  
        {  
            return data[row][column];  
        }  
   
        public void setValueAt(Object value, int row, int column)  
        {  
            data[row][column] = value.toString();  
        }  
   
        public boolean isCellEditable(int row, int column)  
        {  
            return row != 0 && column != 0;  
        }  
   
        public Class getColumnClass(int column)  
        {  
            return String.class;  
        }  
    }  
}  