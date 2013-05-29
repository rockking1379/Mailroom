package split.mailrooms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import com.client.common.*;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;



public class RouteManager extends JFrame {


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
	  JComboBox RouteBox;
	 // DatabaseManager manager;
	  ArrayList<String> inDest = new ArrayList<String>();

	  

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
					RouteManager frame = new RouteManager(new DatabaseManager(),"Someone");
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
	ArrayList<Route> routes = new ArrayList<Route>();
	final DatabaseManager manager;
	String loggedIn;
	public RouteManager(final DatabaseManager manager,final String loggedIn) {
		this.manager=manager;
		this.loggedIn=loggedIn;
		setTitle("Manage Routes");
		setSize(493, 360);
		setSize(493, 360);
		 setVisible(true);
		  
		
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
	    
	    JLabel lblRoute = new JLabel("Select A Route to Edit: ");
	    lblRoute.setForeground(new Color(255, 255, 255));
	    lblRoute.setBounds(10, 31, 136, 14);
	    getContentPane().add(lblRoute);
	    
	    JButton NewStop = new JButton("New Stop");
	    NewStop.setBounds(193, 277, 110, 23);
	    getContentPane().add(NewStop);
	    
	    final JFrame frame = this;
	    NewStop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				    new CreateStop(manager,loggedIn,frame);
				 
			}
		});
	    
	    JButton btnSave = new JButton("Save");
	    btnSave.setBounds(356, 27, 110, 23);
	    getContentPane().add(btnSave);
	    btnSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList selected=new ArrayList();
				
				for(int i=0; i<destListModel.getSize();i++){
					selected.add(i, destListModel.getElementAt(i));
					System.out.println(destListModel.getElementAt(i));
					
				}
				int ind=0;
				for(Object o: selected){
					manager.updateStop((String)o, true, (String)RouteBox.getSelectedItem(),ind);
					ind++;
					System.out.println(RouteBox.getSelectedItem());
					System.out.println((String)o);
				}
				 
				selected=new ArrayList();
				for(int i=0;i<sourceListModel.getSize();i++){
					selected.add(i,sourceListModel.getElementAt(i));
					
					
				}
				ind=0;
				for(Object o: selected){
					manager.updateStop((String)o, false, "unassigned",ind);
					
				}
				
			}
	    	
	    });
	    
	    RouteBox = new JComboBox();
	    RouteBox.setBounds(156, 28, 116, 20);
	    getContentPane().add(RouteBox);
	    
	    
	    DefaultComboBoxModel model;
	    routes=(ArrayList<Route>)manager.getRoutes();
	    String[] rtNames = new String[routes.size()];
	    for(Route r: routes){
	    	rtNames[routes.indexOf(r)] =r.getName();
	    }
	    model = new DefaultComboBoxModel(rtNames);
	    RouteBox.setModel(model);
	    RouteBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String selectedRoute= (String)RouteBox.getSelectedItem();
				System.out.println(selectedRoute);
				ArrayList<Stop> stUsed = (ArrayList<Stop>)manager.getStopsFromRoute(selectedRoute);
				String[] stopNames= new String[stUsed.size()];
				
				for(Stop s: stUsed){
					stopNames[stUsed.indexOf(s)]=s.getName();
				}
				destListModel.clear();
				addDestinationElements(stopNames);
				
			}
	    	
	    });
	    
	    
	    List<Stop> stops = manager.getUnassignedStops();
	    String[] sNames = new String[stops.size()];
	    for(Stop s: stops){
	    	sNames[stops.indexOf(s)]= s.getName();
	    }
	    addSourceElements(sNames);
	    
	    
	    
	    ImageIcon icon= new ImageIcon(getClass().getResource("/image/compass.jpg"));
		setIconImage(icon.getImage());
	}
	  private class AddListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		      Object selected[] = sourceList.getSelectedValues();
		      for(Object o: selected){
		    	  inDest.add((String)o);
		      }
		      addDestinationElements(selected);
		      clearSourceSelected();
		    }
		  }

		  private class RemoveListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		      Object selected[] = destList.getSelectedValues();
		      for(Object o: selected){
		    	inDest.remove((String)o);
		    	
		    	  
		      }
		      addSourceElements(selected);
		      clearDestinationSelected();
		    }
		  }
		/*  private class CreateRouteListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				ArrayList<Stop> stops = new ArrayList<Stop>();
				for(String s: inDest){
					
					System.out.println(s);
					stops.add(new Stop(s));
					
				}
				Route r = new Route(RouteField.getText(),-1,stops);
				DatabaseManager manager = new DatabaseManager();
				manager.addRoute(r);
				
			}
			  
		  }*/
		}

	
class SortedListModel1 extends AbstractListModel<Object> {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
