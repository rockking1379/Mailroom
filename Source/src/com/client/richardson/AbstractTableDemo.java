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

public class AbstractTableDemo extends JPanel
{
	private boolean DEBUG = false;
	private boolean delivered = false;
	private boolean print = false;

	private boolean picked_up;
	private String pickdate;

	public AbstractTableDemo()
	{
		super(new GridLayout(1, 0));
		final JTable table = new JTable(new MyTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

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

		// Object[] values = {"String", 10, 20.0, 30.2, new Boolean(false)};
		MyTableModel a = (MyTableModel) table.getModel();
		// a.insertData(values);
		Object[] row2 = a.row2;
		a.insertData(row2);
		Object[] row3 = a.row3;
		Object[] row4 = a.row4;
		Object[] row5 = a.row5;
		a.insertData(row3);
		a.insertData(row4);
		a.insertData(row5);
		table.setAutoCreateRowSorter(true);

		// Create the scroll pane and add the table to it.

		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.

		add(scrollPane);
	}

	class MyTableModel extends AbstractTableModel
	{
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
		String pickdate = ft.format(date);

		private String[] columnNames = { "Print", "First Name", "Last Name",
				"Stop", "Tracking #", "Date", "Delivered" };

		private Vector data = new Vector();
		public final Object[] row1 = { print, "Kathy", "Smith", "Rex",
				"5452548313", ft.format(date), delivered };
		public final Object[] row2 = { print, "John", "Doe", "SUB",
				"5846421596", ft.format(date), delivered };
		public final Object[] row3 = { print, "Sue", "Black", "Bookstore",
				"2684359112", ft.format(date), delivered };
		public final Object[] row4 = { print, "Jane", "White", "Plachy",
				"2059872641", ft.format(date), delivered };
		public final Object[] row5 = { print, "Joe", "Brown", "SUB",
				"1024861834", ft.format(date), delivered };

		@Override
		public int getColumnCount()
		{
			return columnNames.length;
		}

		@Override
		public int getRowCount()
		{
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col)
		{
			return ((Vector) data.get(row)).get(col);
		}

		public String getColumnName(int col)
		{
			return columnNames[col];
		}

		public Class getColumnClass(int c)
		{
			return getValueAt(0, c).getClass();
		}

		public void setValueAt(Object value, int row, int col)
		{
			((Vector) data.get(row)).setElementAt(value, col);
			fireTableCellUpdated(row, col);
		}

		public boolean isCellEditable(int row, int col)
		{
			if (col < 5 && col >= 3)
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		public void insertData(Object[] values)
		{
			data.add(new Vector());
			for (int i = 0; i < values.length; i++)
			{
				((Vector) data.get(data.size() - 1)).add(values[i]);
			}
			fireTableDataChanged();
		}

		public void removeRow(int row)
		{
			data.removeElementAt(row);
			fireTableDataChanged();
		}

	}

	private static void createAndShowGUI()
	{
		JFrame frame = new JFrame("Abstract Table Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AbstractTableDemo newContentPane = new AbstractTableDemo();
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}

}