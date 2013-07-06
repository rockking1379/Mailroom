package com.client.richardson;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import com.client.common.*;

import javax.swing.*;
import com.client.common.*;

public class DelStop extends JFrame
{
	private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
	private static final String ADD_BUTTON_LABEL = "Add >>";
	private static final String REMOVE_BUTTON_LABEL = "<< Remove";
	private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available Choices";
	private static final String DEFAULT_DEST_CHOICE_LABEL = "Your Choices";
	private JList sourceList;
	private SortedListModel1 sourceListModel;
	private JList destList;
	private SortedListModel1 destListModel;
	private JButton addButton;
	private JButton removeButton;

	ArrayList<String> inDest = new ArrayList<String>();
	private JLabel lblAvailableStops;
	private JLabel lblStopsToBe;
	DatabaseManager manager;

	public void clearSourceListModel()
	{
		sourceListModel.clear();
	}

	public void clearDestinationListModel()
	{
		destListModel.clear();
	}

	public void addSourceElements(ListModel newValue)
	{
		fillListModel(sourceListModel, newValue);
	}

	public void setSourceElements(ListModel newValue)
	{
		clearSourceListModel();
		addSourceElements(newValue);
	}

	public void addDestinationElements(ListModel newValue)
	{
		fillListModel(destListModel, newValue);
	}

	private void fillListModel(SortedListModel1 model, ListModel newValues)
	{
		int size = newValues.getSize();
		for (int i = 0; i < size; i++)
		{
			model.add(newValues.getElementAt(i));
		}
	}

	public void addSourceElements(Object newValue[])
	{
		fillListModel(sourceListModel, newValue);
	}

	public void setSourceElements(Object newValue[])
	{
		clearSourceListModel();
		addSourceElements(newValue);
	}

	public void addDestinationElements(Object newValue[])
	{
		fillListModel(destListModel, newValue);
	}

	private void fillListModel(SortedListModel1 model, Object newValues[])
	{
		model.addAll(newValues);
	}

	public Iterator sourceIterator()
	{
		return sourceListModel.iterator();
	}

	public Iterator destinationIterator()
	{
		return destListModel.iterator();
	}

	public void setSourceCellRenderer(ListCellRenderer newValue)
	{
		sourceList.setCellRenderer(newValue);
	}

	public ListCellRenderer getSourceCellRenderer()
	{
		return sourceList.getCellRenderer();
	}

	public void setDestinationCellRenderer(ListCellRenderer newValue)
	{
		destList.setCellRenderer(newValue);
	}

	public ListCellRenderer getDestinationCellRenderer()
	{
		return destList.getCellRenderer();
	}

	public void setVisibleRowCount(int newValue)
	{
		sourceList.setVisibleRowCount(newValue);
		destList.setVisibleRowCount(newValue);
	}

	public int getVisibleRowCount()
	{
		return sourceList.getVisibleRowCount();
	}

	public void setSelectionBackground(Color newValue)
	{
		sourceList.setSelectionBackground(newValue);
		destList.setSelectionBackground(newValue);
	}

	public Color getSelectionBackground()
	{
		return sourceList.getSelectionBackground();
	}

	public void setSelectionForeground(Color newValue)
	{
		sourceList.setSelectionForeground(newValue);
		destList.setSelectionForeground(newValue);
	}

	public Color getSelectionForeground()
	{
		return sourceList.getSelectionForeground();
	}

	private void clearSourceSelected()
	{
		Object selected[] = sourceList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i)
		{
			sourceListModel.removeElement(selected[i]);
		}
		sourceList.getSelectionModel().clearSelection();
	}

	private void clearDestinationSelected()
	{
		Object selected[] = destList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i)
		{
			destListModel.removeElement(selected[i]);
		}
		destList.getSelectionModel().clearSelection();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					DelStop frame = new DelStop(new DatabaseManager());
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					frame.setSize(493, 313);
					frame.setVisible(true);
					frame.setResizable(false);

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DelStop(DatabaseManager manager)
	{
		this.manager = manager;
		setTitle("Delete Stop");
		setSize(493, 313);

		setResizable(false);
		getContentPane().setBackground(new Color(0, 102, 0));
		sourceListModel = new SortedListModel1();
		sourceList = new JList(sourceListModel);
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane(sourceList);

		List<Stop> stops = manager.getUnassignedStops();
		String[] sNames = new String[stops.size()];
		for (Stop s : stops)
		{
			sNames[stops.indexOf(s)] = s.getName();
		}
		addSourceElements(sNames);

		scrollPane.setBounds(5, 64, 183, 214);
		getContentPane().add(scrollPane);

		addButton = new JButton(ADD_BUTTON_LABEL);
		addButton.setBounds(193, 97, 110, 23);
		getContentPane().add(addButton);
		addButton.addActionListener(new AddListener());
		removeButton = new JButton(REMOVE_BUTTON_LABEL);
		removeButton.setBounds(193, 131, 110, 23);
		getContentPane().add(removeButton);
		removeButton.addActionListener(new RemoveListener());
		destListModel = new SortedListModel1();
		destList = new JList(destListModel);
		JScrollPane scrollPane_1 = new JScrollPane(destList);
		scrollPane_1.setBounds(310, 64, 162, 214);
		getContentPane().add(scrollPane_1);

		JButton btnDelete = new JButton("Delete Stops");
		btnDelete.setBounds(193, 244, 110, 23);
		getContentPane().add(btnDelete);

		btnDelete.addActionListener(new DeleteStop());

		lblAvailableStops = new JLabel("Available Stops:");
		lblAvailableStops.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAvailableStops.setForeground(new Color(255, 255, 255));
		lblAvailableStops.setBounds(24, 30, 139, 23);
		getContentPane().add(lblAvailableStops);

		lblStopsToBe = new JLabel("Stops to be Deleted:");
		lblStopsToBe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStopsToBe.setForeground(new Color(255, 255, 255));
		lblStopsToBe.setBounds(327, 30, 139, 23);
		getContentPane().add(lblStopsToBe);

		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/image/compass.jpg"));
		setIconImage(icon.getImage());
	}

	public class DeleteStop implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			ArrayList selected = new ArrayList();

			for (int i = 0; i < destListModel.getSize(); i++)
			{
				selected.add(i, destListModel.getElementAt(i));

			}
			int ind = 0;
			for (Object o : selected)
			{
				manager.updateStop((String) o, false, "unassigned", ind);
				ind++;

				System.out.println((String) o);
			}

			dispose();
		}

	}

	private class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object selected[] = sourceList.getSelectedValues();
			for (Object o : selected)
			{
				inDest.add((String) o);
			}
			addDestinationElements(selected);
			clearSourceSelected();
		}
	}

	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object selected[] = destList.getSelectedValues();
			for (Object o : selected)
			{
				inDest.remove(inDest.indexOf((String) o));

			}
			addSourceElements(selected);
			clearDestinationSelected();
		}
	}
	/*
	 * private class CreateRouteListener implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * 
	 * 
	 * ArrayList<Stop> stops = new ArrayList<Stop>(); for(String s: inDest){
	 * 
	 * System.out.println(s); stops.add(new Stop(s));
	 * 
	 * } Route r = new Route(RouteField.getText(),-1,stops); DatabaseManager
	 * manager = new DatabaseManager(); manager.addRoute(r);
	 * 
	 * }
	 * 
	 * }
	 */
}

class SortedListModel extends AbstractListModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SortedSet model;

	public SortedListModel()
	{
		model = new TreeSet();
	}

	public int getSize()
	{
		return model.size();
	}

	public Object getElementAt(int index)
	{
		return model.toArray()[index];
	}

	public void add(Object element)
	{
		if (model.add(element))
		{
			fireContentsChanged(this, 0, getSize());
		}
	}

	public void addAll(Object elements[])
	{
		Collection c = Arrays.asList(elements);
		model.addAll(c);
		fireContentsChanged(this, 0, getSize());
	}

	public void clear()
	{
		model.clear();
		fireContentsChanged(this, 0, getSize());
	}

	public boolean contains(Object element)
	{
		return model.contains(element);
	}

	public Object firstElement()
	{
		return model.first();
	}

	public Iterator iterator()
	{
		return model.iterator();
	}

	public Object lastElement()
	{
		return model.last();
	}

	public boolean removeElement(Object element)
	{
		boolean removed = model.remove(element);
		if (removed)
		{
			fireContentsChanged(this, 0, getSize());
		}
		return removed;
	}

} // end class MultipleSelectionTest