package com.client.richardson;

import java.awt.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class MultipleSelectionTest extends JFrame
{
	private JList colorList, copyList;
	private JButton copyButton;
	private final String colorNames[] = { "AAO", "Academic Affairs",
			"Admissions\t", "AITC", "Alumni/Foundation", "Art", "AS&F",
			"Bookstore", "Business Office", "Communications",
			"Community Partnership", "Computing Services",
			"Counseling & Career", "Counselor Education", "EEO",
			"English/ Communication", "Enrollment", "Extended Studies",
			"Facilities Office", "Facilities Warehouse",
			"Finance/ Administration", "Financial Aid", "Gingerbread House",
			"Graduate School", "HGPPSL", "Hold for Pickup", "Housing", "HPPE",
			"Human Resources", "Institutional Research", "Library", "Museum",
			"Music", "Nursing", "One Stop", "Payroll", "Plachy",
			"Police Department", "President", "Print Shop", "Purchasing",
			"Radio Station", "Records", "REX", "School of Business", "SMT",
			"SODEXO", "Student Affairs", "Student Life", "SUB Office",
			"SUB Mailroom", "SVP Enrollment Manager", "Teacher Education",
			"Theatre", "Title V", "Upward Bound" };

	// set up GUI
	public MultipleSelectionTest()
	{
		super("Multiple Selection Lists");

		// get content pane and set its layout
		Container container = getContentPane();
		getContentPane().setLayout(null);

		// set up JList colorList
		colorList = new JList(colorNames);
		colorList.setVisibleRowCount(5);
		colorList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane(colorList);
		scrollPane.setBounds(21, 51, 164, 197);
		container.add(scrollPane);

		// create copy button and register its listener
		copyButton = new JButton("Add>>>");
		copyButton.setBounds(195, 60, 105, 23);
		copyButton.addActionListener(

		new ActionListener()
		{ // anonymous inner class

					// handle button event
					public void actionPerformed(ActionEvent event)
					{
						// place selected values in copyList
						copyList.setListData(colorList.getSelectedValues());
					}

				} // end anonymous inner class

				); // end call to addActionListener

		container.add(copyButton);

		// set up JList copyList
		copyList = new JList();
		copyList.setVisibleRowCount(5);
		copyList.setFixedCellWidth(100);
		copyList.setFixedCellHeight(15);
		copyList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane scrollPane_1 = new JScrollPane(copyList);
		scrollPane_1.setBounds(315, 52, 164, 197);
		container.add(scrollPane_1);
		JButton button = new JButton("<<<Remove");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
			}
		});
		button.setBounds(195, 94, 105, 23);
		getContentPane().add(button);

		setSize(553, 294);
		setVisible(true);

	} // end constructor MultipleSelectionTest

	public static void main(String args[])
	{
		MultipleSelectionTest application = new MultipleSelectionTest();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
} // end class MultipleSelectionTest