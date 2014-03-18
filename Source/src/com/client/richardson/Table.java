package com.client.richardson;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import com.client.common.*;
import com.client.common.Package;
import com.client.richardson.SubRoom.CheckBoxHeader;
import com.client.richardson.SubRoom.MyItemListener;
import com.client.richardson.SubRoom.MyMouseListener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

/**
 * TableRenderDemo is just like TableDemo, except that it explicitly initializes
 * column sizes and it uses a combo box as an editor for the Sport column.
 */
public class Table extends JPanel
{
	private boolean DEBUG = false;
	private boolean delivered = false;
	private boolean print = false;
	MyTableModel atable;
	private boolean picked_up;
	private String pickdate;
	private JTable table;

	TableColumn tc;
	MyItemListener it;
	CheckBoxHeader cbh;
	CheckBoxHeader rendererComponent;
	ArrayList<Package> inTabel = new ArrayList<Package>();

	class MyItemListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			Object source = e.getSource();
			if (source instanceof AbstractButton == false)
				return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for (int x = 0, y = table.getRowCount(); x < y; x++)
			{
				table.setValueAt(new Boolean(checked), x, 0);
			}
		}
	}

	class MyMouseListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent mouseEvent)
		{
			int checkedCount = 0;
			rendererComponent.removeItemListener(it);
			if (rendererComponent instanceof JCheckBox)
			{
				boolean[] flags = new boolean[table.getRowCount()];
				for (int i = 0; i < table.getRowCount(); i++)
				{
					flags[i] = ((Boolean) table.getValueAt(i, 0))
							.booleanValue();
					if (flags[i])
					{
						checkedCount++;
					}
				}
				if (checkedCount == table.getRowCount())
				{
					((JCheckBox) rendererComponent).setSelected(true);
				}
				if (checkedCount != table.getRowCount())
				{
					((JCheckBox) rendererComponent).setSelected(false);
				}
			}
			rendererComponent.addItemListener(it);
			table.getTableHeader().repaint();
		}
	}

	public CheckBoxHeader getRendererComponent()
	{
		return rendererComponent;
	}

	public void setRendererComponent(CheckBoxHeader rendererComponent)
	{
		rendererComponent.setText("Delivered");
		this.rendererComponent = rendererComponent;
	}

	class CheckBoxHeader extends JCheckBox implements TableCellRenderer,
			MouseListener
	{
		protected int column;
		protected boolean mousePressed = false;
		ItemListener it1;

		public CheckBoxHeader(ItemListener itemListener)
		{
			setRendererComponent(this);
			this.it1 = itemListener;
			rendererComponent.addItemListener(it1);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column)
		{
			if (table != null)
			{
				JTableHeader header = table.getTableHeader();
				if (header != null)
				{
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

		protected void setColumn(int column)
		{
			this.column = column;
		}

		public int getColumn()
		{
			return column;
		}

		protected void handleClickEvent(MouseEvent e)
		{
			if (mousePressed)
			{
				mousePressed = false;
				JTableHeader header = (JTableHeader) (e.getSource());
				JTable tableView = header.getTable();
				TableColumnModel columnModel = tableView.getColumnModel();
				int viewColumn = columnModel.getColumnIndexAtX(e.getX());
				int column = tableView.convertColumnIndexToModel(viewColumn);
				if (viewColumn == this.column && e.getClickCount() == 1
						&& column != -1)
				{
					doClick();
				}
			}
		}

		public void mouseClicked(MouseEvent e)
		{
			handleClickEvent(e);
			((JTableHeader) e.getSource()).repaint();
		}

		public void mousePressed(MouseEvent e)
		{
			mousePressed = true;
		}

		public void mouseReleased(MouseEvent e)
		{
		}

		public void mouseEntered(MouseEvent e)
		{
		}

		public void mouseExited(MouseEvent e)
		{
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("TableRenderDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		Table newContentPane = new Table(new DatabaseManager());
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	DatabaseManager manager;

	public Table(DatabaseManager manager)
	{

		super(new GridLayout(1, 0));

		this.manager = manager;

		MyTableModel model = new MyTableModel();

		table = new JTable(model);

		table.setPreferredScrollableViewportSize(new Dimension(716, 366));

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(84);
		table.getColumnModel().getColumn(6).setPreferredWidth(82);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);

		tc = table.getColumnModel().getColumn(0);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		it = new MyItemListener();
		cbh = new CheckBoxHeader(it);
		tc.setHeaderRenderer(cbh);
		table.addMouseListener(new MyMouseListener());

		table.getTableHeader().setReorderingAllowed(false);

		atable = (MyTableModel) table.getModel();

		ArrayList<Package> undilivered = (ArrayList<Package>) manager
				.findPackage(false, false);

		if (undilivered.size() == 0)
		{
			atable.insertData(new Object[] { false, "", "", "", "", "", "", "",
					"" });
		}

		for (Package p : undilivered)
		{
			SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
			Date sdDate = null;
			try
			{
				sdDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
						.parse(p.getDate());
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String date = ft.format(sdDate);
			inTabel.add(p);
			
			String last = "..." + p.getTrackNum().substring(p.getTrackNum().length() - 4, p.getTrackNum().length());
			
			atable.insertData(new Object[] { false, p.getFName(), p.getLName(),
					p.getStop(), last, p.getCourier(), date,
					p.getUser(), "", });
		}

		/*
		 * atable.insertData(atable.row1); atable.insertData(atable.row2);
		 * atable.insertData(atable.row3); atable.insertData(atable.row4);
		 * atable.insertData(atable.row5a); atable.insertData(atable.yourmom);
		 */
		table.setFillsViewportHeight(true);

		table.setAutoCreateRowSorter(true);
		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Set up column sizes.
		// initColumnSizes(table);

		// Fiddle with the Sport column's cell editors/renderers.
		setUpStopColumn(table, table.getColumnModel().getColumn(3));

		// Add the scroll pane to this panel.
		add(scrollPane);
	}

	/*
	 * This method picks good column sizes. If all column heads are wider than
	 * the column's cells' contents, then you can just use
	 * column.sizeWidthToFit().
	 */
	private void initColumnSizes(JTable table)
	{
		MyTableModel model = (MyTableModel) table.getModel();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValues = model.row1;
		TableCellRenderer headerRenderer = table.getTableHeader()
				.getDefaultRenderer();

		for (int i = 0; i < 5; i++)
		{
			column = table.getColumnModel().getColumn(i);

			comp = headerRenderer.getTableCellRendererComponent(null, column
					.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;

			comp = table.getDefaultRenderer(model.getColumnClass(i))
					.getTableCellRendererComponent(table, longValues[i], false,
							false, 0, i);
			cellWidth = comp.getPreferredSize().width;

			if (DEBUG)
			{
				System.out.println("Initializing width of column " + i + ". "
						+ "headerWidth = " + headerWidth + "; cellWidth = "
						+ cellWidth);
			}

			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
		}
	}

	public void setUpStopColumn(JTable table, TableColumn sportColumn)
	{
		// Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		DefaultComboBoxModel stopNames = null;
		boolean noStops = false;
		try
		{
			ArrayList<Stop> stops = (ArrayList<Stop>) manager.getStops();
			String[] sa = new String[stops.size()];

			for (Stop s : stops)
			{
				sa[stops.indexOf(s)] = s.getName();
			}
			Arrays.sort(sa);
			stopNames = new DefaultComboBoxModel(sa);
		}
		catch (NegativeArraySizeException ex)
		{
			JOptionPane.showMessageDialog(this,
					"Please create Stops before scanning in packages");

		}

		comboBox.setModel(stopNames);
		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

		// Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		sportColumn.setCellRenderer(renderer);
	}

	class MyTableModel extends AbstractTableModel
	{
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
		String pickdate = ft.format(date);

		private String[] columnNames = { "Delivered", "First Name",
				"Last Name", "Stop", "Tracking #", "Carrier", "Date",
				"Username", "Delivered Date" };

		private Vector data = new Vector();
		public final Object[] row1 = { delivered, "Kathy", "Smith", "Rex",
				"5452548313", "", ft.format(date), "", "" };

		/*
		 * public final Object[] row2 = {print, "John", "Doe","SUB",
		 * "5846421596", ft.format(date),"", delivered}; public final Object[]
		 * row3 = {print, "Sue", "Black","Bookstore", "2684359112",
		 * ft.format(date),"", delivered}; public final Object[] row4 = {print,
		 * "Jane", "White", "Plachy", "2059872641", ft.format(date),"",
		 * delivered}; public final Object[] row5a = {print, "Joe",
		 * "Brown","SUB","1024861834", ft.format(date),"", delivered}; public
		 * final Object[] yourmom = {print, "Jill",
		 * "Brown","REX","1024544861834", ft.format(date),"", delivered};
		 */

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
			if (col != 0 && col != 3)
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

		/**
		 * Create the GUI and show it. For thread safety, this method should be
		 * invoked from the event-dispatching thread.
		 */
	}

	public void setSearchResults(ArrayList<Package> results)
	{
		// int i=atable.getRowCount()-1;
		inTabel.clear();

		if (results.size() == 0 || atable.getRowCount() <= 1)
		{
			atable.insertData(new Object[] { false, "", "", "", "", "", "", "",
					"" });
			atable.removeRow(0);

		}

		atable.insertData(new Object[] { false, "", "", "", "", "", "", "", "" });
		while (atable.getRowCount() >= 1
				|| !(atable.getValueAt(0, 5).equals("")))
		{

			atable.removeRow(atable.getRowCount() - 1);
			System.out.println(atable.getRowCount() - 1);
			if (atable.getRowCount() == 1)
			{
				atable.insertData(new Object[] { false, "", "", "", "", "", "",
						"", "" });
				atable.removeRow(0);
				break;
			}
		}

		for (Package p : results)
		{
			inTabel.add(p);
			SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
			Date sdDate = null;
			try
			{
				sdDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
						.parse(p.getDate());
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String date = ft.format(sdDate);
			
			String last = "..." + p.getTrackNum().substring(p.getTrackNum().length() - 4, p.getTrackNum().length());

			atable.insertData(new Object[] { p.getDelivered(), p.getFName(),
					p.getLName(), p.getStop(), last, p.getCourier(),
					date, p.getUser(), p.getPickedUpDate() + "" });

		}
		if (atable.getRowCount() > 1)
		{
			atable.removeRow(0);
		}

	}

	public void updateTabel()
	{

		for (int i = 0; i < atable.getRowCount(); i++)
		{
			System.out.println(atable.getValueAt(0, 0));
			for (Package p : inTabel)
			{
				String last = "..." + p.getTrackNum().substring(p.getTrackNum().length() - 4, p.getTrackNum().length());

				if (last.equals(atable.getValueAt(i, 4)))
				{

					if (!p.getStop().equals(atable.getValueAt(i, 3))
							|| p.getDelivered() != (boolean) atable.getValueAt(
									i, 0))
					{
						manager.updatePackage(p.getTrackNum(),
								(boolean) atable.getValueAt(i, 0),
								(boolean) atable.getValueAt(i, 0),
								(String) atable.getValueAt(i, 3));

					}
				}
			}
		}
		manager.loadPackages(true, null);
		setSearchResults((ArrayList<Package>) manager.findPackage(false, false));
	}

}
