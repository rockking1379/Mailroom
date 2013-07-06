package com.client.richardson;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import sun.security.util.Length;

import com.client.common.DatabaseManager;
import com.client.common.Package;
import com.client.common.Stop;

import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class SubRoom extends JPanel
{

	private boolean delivered = false;
	private boolean print = false;

	private boolean picked_up;
	private String pickdate;
	Table displayTable;
	MyTableModel atable;
	private boolean DEBUG = false;
	private JTable table;
	private JTextField filterText;
	private TableRowSorter<MyTableModel> sorter;
	DatabaseManager manager;
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

	JComboBox comboBox;

	public SubRoom()
	{
		super();
		setBackground(new Color(0, 102, 0));
		manager = new DatabaseManager();
		loadSettings();
		manager.loadPackages(true, null);

		// Create a table with a sorter.
		MyTableModel model = new MyTableModel();
		sorter = new TableRowSorter<MyTableModel>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));

		atable = (MyTableModel) table.getModel();

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(84);
		table.getColumnModel().getColumn(6).setPreferredWidth(82);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);

		table.setFillsViewportHeight(true);

		tc = table.getColumnModel().getColumn(0);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		it = new MyItemListener();
		cbh = new CheckBoxHeader(it);
		tc.setHeaderRenderer(cbh);
		table.addMouseListener(new MyMouseListener());

		// For the purposes of this example, better to have a single
		// selection.
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// When selection changes, provide user with row numbers for

		// both view and model.
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener()
				{
					public void valueChanged(ListSelectionEvent event)
					{
						int viewRow = table.getSelectedRow();
						if (viewRow < 0)
						{
							// Selection got filtered away.

						}
						else
						{
							int modelRow = table
									.convertRowIndexToModel(viewRow);

						}
					}
				});
		setLayout(null);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 67, 828, 435);

		// Add the scroll pane to this panel.
		add(scrollPane);

		// Create a separate form for filterText and statusText
		JPanel form = new JPanel();
		form.setBackground(new Color(0, 102, 0));
		form.setBounds(79, 510, 661, 62);
		form.setLayout(null);
		JLabel l1 = new JLabel("Search:", SwingConstants.LEFT);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l1.setForeground(new Color(255, 255, 255));
		l1.setBounds(10, 10, 86, 20);
		form.add(l1);
		filterText = new JTextField();
		filterText.setBounds(73, 11, 274, 20);
		// Whenever filterText changes, invoke newFilter.
		filterText.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent e)
			{
				newFilter();
			}

			public void insertUpdate(DocumentEvent e)
			{
				newFilter();
			}

			public void removeUpdate(DocumentEvent e)
			{
				newFilter();
			}
		});
		l1.setLabelFor(filterText);
		form.add(filterText);

		add(form);

		final SubRoom table = this;

		JButton btnAdvancedSearch = new JButton("Advanced Search");
		btnAdvancedSearch.setBounds(496, 10, 155, 23);
		form.add(btnAdvancedSearch);
		btnAdvancedSearch.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				SUBAdvSearch search = new SUBAdvSearch(table);
				search.setVisible(true);
			}
		});

		JLabel lblPleaseSelectA = new JLabel("Please Select a Stop: ");
		lblPleaseSelectA.setForeground(Color.WHITE);
		lblPleaseSelectA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseSelectA.setBounds(120, 25, 136, 14);
		add(lblPleaseSelectA);

		comboBox = new JComboBox();
		comboBox.setBounds(249, 23, 217, 20);
		add(comboBox);

		ArrayList<Stop> stops = (ArrayList<Stop>) manager.getStops();
		String[] names = new String[stops.size()];

		for (Stop s : stops)
		{
			names[stops.indexOf(s)] = s.getName();
		}
		Arrays.sort(names);
		comboBox.setModel(new DefaultComboBoxModel(names));

		comboBox.addItemListener(new StopListener());
		comboBox.setSelectedItem("SUB Mailroom");

		JButton btnRefesh = new JButton("Refresh");
		btnRefesh.setBounds(645, 22, 89, 23);
		add(btnRefesh);

		btnRefesh.addActionListener(new RefreshListener());

		String firstStop = (String) comboBox.getSelectedItem();

		addPackagesToTable((ArrayList<Package>) manager
				.getPackagesFromStop(firstStop));

	}

	/**
	 * Update the row filter regular expression from the expression in the text
	 * box.
	 */
	private void newFilter()
	{
		RowFilter<MyTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try
		{
			rf = RowFilter.regexFilter(filterText.getText(), table
					.getSelectedColumns());
		}
		catch (java.util.regex.PatternSyntaxException e)
		{
			return;
		}
		sorter.setRowFilter(rf);
	}

	class MyTableModel extends AbstractTableModel
	{
		public final DatabaseManager manager = null;
		Date date = new Date();
		Object[][] data1 = null;
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
		String pickdate = "";
		String trackNum = "0214123574841245546";
		String trackNum1 = "021412357484124465465455875346";
		String L4 = trackNum
				.substring(trackNum.length() - 4, trackNum.length());
		String L41 = trackNum1.substring(trackNum1.length() - 4, trackNum1
				.length());

		private String[] columnNames = { "Delivered", "First Name",
				"Last Name", "Box #", "Tracking #", "Carrier", "Date",
				" Picked Up", "Picked Up Date" };
		private Vector data = new Vector();

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
			if (col != 0 && col != 7)
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

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI()
	{
		// Create and set up the window.
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Student Union Mail Room");
		frame.setSize(865, 590);
		ImageIcon icon = new ImageIcon(frame.getClass().getResource(
				"/image/Untitled.jpg"));
		frame.setIconImage(icon.getImage());

		SubRoom newContentPane = new SubRoom();
		newContentPane.setOpaque(true);

		frame.setContentPane(newContentPane);
		// frame.setResizable(false);
		frame.setVisible(true);
		frame.setResizable(false);

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

	public void loadSettings()
	{

		File settings = new File("./properties.prop");
		if (settings.exists())
		{
			try
			{
				FileInputStream fStream = new FileInputStream(settings);
				DataInputStream dis = new DataInputStream(fStream);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						dis));

				String settingLine;
				while ((settingLine = br.readLine()) != null)
				{
					// Read Settings
					String setting = "";
					int index = 0;
					while (settingLine.charAt(index) != ';')
					{
						setting += settingLine.charAt(index);
						index++;
					}
					index++;
					if (setting.toUpperCase().equals("DATABASE"))
					{
						// Read in Database configuration
						String temp = "";
						for (int i = index; i < settingLine.length(); i++)
						{
							temp += settingLine.charAt(i);
						}
						manager.setDatabase(temp);
					}
					else
					{
						if (setting.toUpperCase().equals("PERSONS"))
						{
							String temp = "";

							for (int i = index; i < settingLine.length(); i++)
							{
								temp += settingLine.charAt(i);
							}
							manager.setFile(temp);
						}
					}
				}
				br.close();
			}
			catch (Exception e)
			{
				// Do nothing
			}
			manager.setup();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Settings File Not Found.");

			JFileChooser fc = new JFileChooser();
			JOptionPane.showMessageDialog(null, "Select Database");
			fc.showDialog(null, "SELECT");
			fc.setFileFilter(null);
			File database = fc.getSelectedFile();
			JOptionPane.showMessageDialog(null, "Select Person File");
			fc.showDialog(null, "SELECT");
			File persons = fc.getSelectedFile();

			try
			{
				if (settings.createNewFile())
				{
					FileWriter fOutput = new FileWriter(settings);
					BufferedWriter bw = new BufferedWriter(fOutput);
					bw.write("DATABASE;" + database.getAbsolutePath());
					bw.newLine();
					bw.write("PERSONS;" + persons.getAbsolutePath() + "\n");
					bw.close();
					fOutput.close();
				}
			}
			catch (Exception e)
			{
				// Ignore the exceptions
			}
			JOptionPane.showMessageDialog(null,
					"Restart Application for changes to take effect.\nThanks!");
		}
	}

	public class StopListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			String currentStop = (String) comboBox.getSelectedItem();

			addPackagesToTable((ArrayList<Package>) manager
					.getPackagesFromStop(currentStop));

			if (atable.getRowCount() > 1)
			{
				// atable.removeRow(0);
			}

		}

	}

	public void addPackagesToTable(ArrayList<Package> results)
	{
		// int i=atable.getRowCount()-1;
		inTabel.clear();

		if (results.size() == 0 || atable.getRowCount() == 0)
		{
			atable.insertData(new Object[] { false, "", "", "", "", "", "",
					false, "" });

		}

		atable.insertData(new Object[] { false, "", "", "", "", "", "", false,
				"" });
		while (atable.getRowCount() > 1
				|| !(atable.getValueAt(0, 4).equals("")))
		{

			atable.removeRow(atable.getRowCount() - 1);
			System.out.println(atable.getRowCount() - 1);
			if (atable.getRowCount() == 1)
			{
				atable.insertData(new Object[] { false, "", "", "", "", "", "",
						false, "" });
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
			String trackNum = p.getTrackNum();
			String last4 = trackNum.substring(trackNum.length() - 4, trackNum
					.length());

			atable.insertData(new Object[] { p.getDelivered(), p.getFName(),
					p.getLName(), p.getBoxNum(), "..." + last4, p.getCourier(),
					date, p.getPickedUp(), p.getPickedUpDate() + "" });

		}
		if (atable.getRowCount() > 1)
		{
			atable.removeRow(0);
		}
	}

	public class RefreshListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{

			updateTabel();

		}

	}

	public void updateTabel()
	{

		for (int i = 0; i < atable.getRowCount(); i++)
		{

			for (Package p : inTabel)
			{

				String a = ((String) atable.getValueAt(i, 4));
				a = a.substring(a.length() - 4, a.length());

				String b = p.getTrackNum();
				b = b.substring(b.length() - 4, b.length());

				if (a.equals(b))
				{

					if (p.getDelivered() != (boolean) atable.getValueAt(i, 0)
							|| p.getPickedUp() != (boolean) atable.getValueAt(
									i, 7))
					{
						manager.updatePackage(p.getTrackNum(), (boolean) atable
								.getValueAt(i, 0), (boolean) atable.getValueAt(
								i, 7), p.getStop());

					}
				}

			}

		}

		String stopSelected = (String) comboBox.getSelectedItem();
		manager.loadPackages(false, stopSelected);

		ArrayList<Package> allPacs = new ArrayList<Package>();

		for (Package p : manager.getPackagesFromStop(stopSelected))
		{
			allPacs.add(p);
		}

		addPackagesToTable(allPacs);
	}
}
