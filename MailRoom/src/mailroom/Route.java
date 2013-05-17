package mailroom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;



public class Route extends JFrame {

	private JPanel contentPane;
	 private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
	  private static final String ADD_BUTTON_LABEL = "Add >>";
	  private static final String REMOVE_BUTTON_LABEL = "<< Remove";
	  private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available Choices";
	  private static final String DEFAULT_DEST_CHOICE_LABEL = "Your Choices";
	  private JLabel sourceLabel;
	  private JList sourceList;
	  private SortedListModel sourceListModel;
	  private JList destList;
	  private SortedListModel destListModel;
	  private JLabel destLabel;
	  private JButton addButton;
	  private JButton removeButton;
	  private JTextField RouteField;

	  

	  public String getSourceChoicesTitle() {
	    return sourceLabel.getText();
	  }

	  public void setSourceChoicesTitle(String newValue) {
	    sourceLabel.setText(newValue);
	  }

	  public String getDestinationChoicesTitle() {
	    return destLabel.getText();
	  }

	  public void setDestinationChoicesTitle(String newValue) {
	    destLabel.setText(newValue);
	  }

	  public void clearSourceListModel() {
	    sourceListModel.clear();
	  }

	  public void clearDestinationListModel() {
	    destListModel.clear();
	  }

	  public void addSourceElements(ListModel newValue) {
	    fillListModel(sourceListModel, newValue);
	  }

	  public void setSourceElements(ListModel newValue) {
	    clearSourceListModel();
	    addSourceElements(newValue);
	  }

	  public void addDestinationElements(ListModel newValue) {
	    fillListModel(destListModel, newValue);
	  }

	  private void fillListModel(SortedListModel model, ListModel newValues) {
	    int size = newValues.getSize();
	    for (int i = 0; i < size; i++) {
	      model.add(newValues.getElementAt(i));
	    }
	  }

	  public void addSourceElements(Object newValue[]) {
	    fillListModel(sourceListModel, newValue);
	  }

	  public void setSourceElements(Object newValue[]) {
	    clearSourceListModel();
	    addSourceElements(newValue);
	  }

	  public void addDestinationElements(Object newValue[]) {
	    fillListModel(destListModel, newValue);
	  }

	  private void fillListModel(SortedListModel model, Object newValues[]) {
	    model.addAll(newValues);
	  }

	  public Iterator sourceIterator() {
	    return sourceListModel.iterator();
	  }

	  public Iterator destinationIterator() {
	    return destListModel.iterator();
	  }

	  public void setSourceCellRenderer(ListCellRenderer newValue) {
	    sourceList.setCellRenderer(newValue);
	  }

	  public ListCellRenderer getSourceCellRenderer() {
	    return sourceList.getCellRenderer();
	  }

	  public void setDestinationCellRenderer(ListCellRenderer newValue) {
	    destList.setCellRenderer(newValue);
	  }

	  public ListCellRenderer getDestinationCellRenderer() {
	    return destList.getCellRenderer();
	  }

	  public void setVisibleRowCount(int newValue) {
	    sourceList.setVisibleRowCount(newValue);
	    destList.setVisibleRowCount(newValue);
	  }

	  public int getVisibleRowCount() {
	    return sourceList.getVisibleRowCount();
	  }

	  public void setSelectionBackground(Color newValue) {
	    sourceList.setSelectionBackground(newValue);
	    destList.setSelectionBackground(newValue);
	  }

	  public Color getSelectionBackground() {
	    return sourceList.getSelectionBackground();
	  }

	  public void setSelectionForeground(Color newValue) {
	    sourceList.setSelectionForeground(newValue);
	    destList.setSelectionForeground(newValue);
	  }

	  public Color getSelectionForeground() {
	    return sourceList.getSelectionForeground();
	  }

	  private void clearSourceSelected() {
	    Object selected[] = sourceList.getSelectedValues();
	    for (int i = selected.length - 1; i >= 0; --i) {
	      sourceListModel.removeElement(selected[i]);
	    }
	    sourceList.getSelectionModel().clearSelection();
	  }

	  private void clearDestinationSelected() {
	    Object selected[] = destList.getSelectedValues();
	    for (int i = selected.length - 1; i >= 0; --i) {
	      destListModel.removeElement(selected[i]);
	    }
	    destList.getSelectionModel().clearSelection();
	  }


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Route frame = new Route();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					    frame.setSize(493, 360);
					    frame.setVisible(true);
					    frame.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Route() {
		setTitle("Create a Route");
		setSize(493, 360);
		setResizable(false);
		getContentPane().setBackground(new Color(0, 102, 0));
		sourceLabel = new JLabel("Available Stops");
	    sourceLabel.setForeground(new Color(255, 255, 255));
	    sourceLabel.setBounds(45, 72, 116, 14);
	    sourceListModel = new SortedListModel();
	    sourceList = new JList(sourceListModel);
	    getContentPane().setLayout(null);
	    getContentPane().add(sourceLabel);
	    JScrollPane scrollPane = new JScrollPane(sourceList);
	    addSourceElements(new String[] {  "AAO", "Academic Affairs", "Admissions\t", "AITC", "Alumni/Foundation", "Art", "AS&F", "Bookstore", "Business Office", "Communications", "Community Partnership", "Computing Services",
	    		"Counseling & Career", "Counselor Education", "EEO", "English/ Communication", "Enrollment", "Extended Studies", "Facilities Office", "Facilities Warehouse", "Finance/ Administration", "Financial Aid", 
	    		"Gingerbread House", "Graduate School", "HGPPSL", "Hold for Pickup", "Housing", "HPPE", "Human Resources", "Institutional Research", "Library", "Museum", "Music", "Nursing", "One Stop", "Payroll", "Plachy", 
	    		"Police Department", "President", "Print Shop", "Purchasing", "Radio Station", "Records", "REX", "School of Business", "SMT", "SODEXO", "Student Affairs", "Student Life", "SUB Office", "SUB Mailroom", 
	    		"SVP Enrollment Manager", "Teacher Education", "Theatre", "Title V", "Upward Bound" });

	   
	   
	    scrollPane.setBounds(5, 97, 183, 221);
	    getContentPane().add(scrollPane);

	    addButton = new JButton(ADD_BUTTON_LABEL);
	    addButton.setBounds(193, 97, 110, 23);
	    getContentPane().add(addButton);
	    addButton.addActionListener(new AddListener());
	    removeButton = new JButton(REMOVE_BUTTON_LABEL);
	    removeButton.setBounds(193, 131, 110, 23);
	    getContentPane().add(removeButton);
	    removeButton.addActionListener(new RemoveListener());

	    destLabel = new JLabel("Stops in Route");
	    destLabel.setForeground(new Color(255, 255, 255));
	    destLabel.setBounds(356, 72, 97, 14);
	    destListModel = new SortedListModel();
	    destList = new JList(destListModel);
	    getContentPane().add(destLabel);
	    JScrollPane scrollPane_1 = new JScrollPane(destList);
	    scrollPane_1.setBounds(310, 97, 162, 221);
	    getContentPane().add(scrollPane_1);
	    
	    JLabel lblRouteName = new JLabel("Route Name:");
	    lblRouteName.setForeground(new Color(255, 255, 255));
	    lblRouteName.setBounds(10, 31, 84, 14);
	    getContentPane().add(lblRouteName);
	    
	    RouteField = new JTextField();
	    RouteField.setBounds(92, 28, 242, 20);
	    getContentPane().add(RouteField);
	    RouteField.setColumns(10);
	    
	    JButton NewStop = new JButton("New Stop");
	    NewStop.setBounds(193, 277, 110, 23);
	    getContentPane().add(NewStop);
	    
	    JButton btnCreateRoute = new JButton("Create Route");
	    btnCreateRoute.setBounds(356, 27, 110, 23);
	    getContentPane().add(btnCreateRoute);
	    
	    ImageIcon icon= new ImageIcon(getClass().getResource("/image/compass.jpg"));
		setIconImage(icon.getImage());
	}
	private class AddListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      Object selected[] = sourceList.getSelectedValues();
	      addDestinationElements(selected);
	      clearSourceSelected();
	    }
	  }

	  private class RemoveListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      Object selected[] = destList.getSelectedValues();
	      addSourceElements(selected);
	      clearDestinationSelected();
	    }
	  }
	}

	
	class SortedListModel1 extends AbstractListModel {

	SortedSet model;

	  public SortedListModel1() {
	    model = new TreeSet();
	  }

	  public int getSize() {
	    return model.size();
	  }

	  public Object getElementAt(int index) {
	    return model.toArray()[index];
	  }

	  public void add(Object element) {
	    if (model.add(element)) {
	      fireContentsChanged(this, 0, getSize());
	    }
	  }

	  public void addAll(Object elements[]) {
	    Collection c = Arrays.asList(elements);
	    model.addAll(c);
	    fireContentsChanged(this, 0, getSize());
	  }

	  public void clear() {
	    model.clear();
	    fireContentsChanged(this, 0, getSize());
	  }

	  public boolean contains(Object element) {
	    return model.contains(element);
	  }

	  public Object firstElement() {
	    return model.first();
	  }

	  public Iterator iterator() {
	    return model.iterator();
	  }

	  public Object lastElement() {
	    return model.last();
	  }

	  public boolean removeElement(Object element) {
	    boolean removed = model.remove(element);
	    if (removed) {
	      fireContentsChanged(this, 0, getSize());
	    }
	    return removed;
	  }
	
}
