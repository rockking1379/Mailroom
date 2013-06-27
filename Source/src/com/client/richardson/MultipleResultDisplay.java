package com.client.richardson;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import com.client.common.*;
import com.client.common.Package;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

public class MultipleResultDisplay extends JFrame
{

	private JPanel contentPane;
	ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	/**
	 * Launch the application.
	 */
	ArrayList<Person> results;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { MultipleResultDisplay frame = new
	 * MultipleResultDisplay(new ScanPackage(), new List<Package>());
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	public MultipleResultDisplay(final ScanPackage frame,
			final List<Person> results)
	{
		this.results = (ArrayList<Person>) results;
		setBackground(new Color(0, 128, 0));
		setTitle("Multiple Results");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 232);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"There were multiple results to your search.");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(73, 11, 434, 37);
		contentPane.add(lblNewLabel);

		JLabel lblrnPleaseSelect = new JLabel(
				"Please select the person you would like to use.");
		lblrnPleaseSelect.setForeground(new Color(255, 255, 255));
		lblrnPleaseSelect.setBounds(73, 59, 361, 14);
		contentPane.add(lblrnPleaseSelect);

		JButton btnSelect = new JButton("OK");
		btnSelect.setBounds(307, 175, 89, 23);
		contentPane.add(btnSelect);

		JPanel resultPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(resultPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(21, 84, 361, 80);
		contentPane.add(scrollPane);

		// scrollPane.setViewportView(resultPanel);
		resultPanel.setForeground(Color.WHITE);
		resultPanel.setBackground(new Color(51, 204, 0));
		resultPanel.setLayout(new GridLayout(0, 2, 0, 0));

		final ButtonGroup group = new ButtonGroup();

		for (Person p : results)
		{
			String s = p.getLastName() + " " + p.getFirstName() + " "
					+ p.getBox();
			JCheckBox c = new JCheckBox(s);
			c.setBackground(new Color(51, 204, 0));
			c.setForeground(Color.WHITE);
			checkBoxes.add(c);
			group.add(c);
			resultPanel.add(c);
		}

		btnSelect.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JCheckBox ch = null;
				for (JCheckBox c : checkBoxes)
				{
					if (c.isSelected())
					{
						ch = c;
					}
				}
				int i = checkBoxes.indexOf(ch);
				Person selected = results.get(i);
				frame.getBoxText().setText(selected.getBox());
				frame.getComboBox().setSelectedItem(selected.getStop());
				frame.getNameText().setText(selected.getFirstName());
				frame.getLastNameText().setText(selected.getLastName());
				frame.selectedPerson = selected;
				dispose();

			}

		});
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
