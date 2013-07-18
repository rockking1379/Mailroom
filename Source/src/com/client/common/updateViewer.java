package com.client.common;

import javax.swing.*;

import sun.util.calendar.BaseCalendar.Date;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class updateViewer extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	private JButton okButton = null;
	private File update = null;
	JTextArea updateText = null;
	Thread uThread;

	// /---ETL Constructor---///
	public updateViewer()
	{
		// Threading
		uThread = new Thread(this);
		uThread.setName("ETL Viewer");
	}

	// /---Running---///
	public void run()
	{
		// Frame
		this.setVisible(false);
		this.setLocation(100, 100);
		this.setSize(480, 640);
		this.setBackground(Color.CYAN);
		this.setLayout(null);
		this.addKeyListener(new keyListener());
		this.setTitle("About This Update");

		// Text Area
		updateText = new JTextArea();
		updateText.setEditable(false);
		updateText.setSize(440, 540);
		updateText.setLocation(10, 10);
		updateText.addKeyListener(new keyListener());
		updateText.setAutoscrolls(true);
		this.add(updateText);

		// Okay Button
		okButton = new JButton();
		okButton.setText("OK");
		okButton.addActionListener(new actionListener());
		okButton.setLocation(10, 555);
		okButton.setSize(440, 40);
		this.add(okButton);
		okButton.setEnabled(false);

		this.setVisible(true);

		try
		{
			uThread.sleep(500);
		}
		catch (Exception e)
		{
			// Ignore
		}
	}

	public void start()
	{
		uThread.start();
	}

	// /---DBM Constructor---///
	public updateViewer(File file)
	{
		this.setVisible(false);
		// Frame
		this.setLocation(100, 100);
		this.setSize(480, 640);
		this.setBackground(Color.CYAN);
		this.setLayout(null);
		this.addKeyListener(new keyListener());
		this.setTitle("About This Update");

		// File setting
		update = file;

		// Text Area
		updateText = new JTextArea();
		updateText.setEditable(false);
		updateText.setSize(440, 540);
		updateText.setLocation(10, 10);
		updateText.addKeyListener(new keyListener());

		FileInputStream fStream = null;
		DataInputStream dis = null;
		BufferedReader br = null;

		try
		{
			fStream = new FileInputStream(update);
			dis = new DataInputStream(fStream);
			br = new BufferedReader(new InputStreamReader(dis));

			String uTxt = "";
			String line;
			while ((line = br.readLine()) != null)
			{
				uTxt += line + "\n";
			}
			updateText.setText(uTxt);
		}
		catch (Exception e)
		{
			// Don't worry
			// This is an add-on
		}
		finally
		{
			try
			{
				if (fStream != null)
				{
					fStream.close();
				}
				if (dis != null)
				{
					dis.close();
				}
				if (br != null)
				{
					br.close();
				}
			}
			catch (Exception e)
			{
				// Ignore Exceptions
			}
		}
		this.add(updateText);

		// Okay Button
		okButton = new JButton();
		okButton.setText("OK");
		okButton.addActionListener(new actionListener());
		okButton.setLocation(10, 555);
		okButton.setSize(440, 40);
		this.add(okButton);
	}

	// /---ETL Methods---///
	public void etlComplete(java.util.Date dDate)
	{
		updateText.setText("Routine Update Complete\nThank You!\nTime To Complete: " + dDate.getMinutes() + ":" + dDate.getSeconds());
		okButton.setEnabled(true);
	}

	public void etlUpdate(String info)
	{
		updateText.setText(updateText.getText() + "\n" + info);
	}

	// /---Action Listener---///
	private class actionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			if (ae.getSource() == okButton)
			{
				if (update != null)
				{
					update.delete();
				}
				dispose();
			}
		}
	}

	// /---Key Listener---///
	private class keyListener implements KeyListener
	{
		public void keyPressed(KeyEvent ke)
		{
			if (ke.getKeyCode() == KeyEvent.VK_ENTER)
			{
				okButton.doClick();
			}
		}

		public void keyReleased(KeyEvent ke)
		{
			if (ke.getKeyCode() == KeyEvent.VK_ENTER)
			{
				okButton.doClick();
			}
		}

		public void keyTyped(KeyEvent ke)
		{
			if (ke.getKeyCode() == KeyEvent.VK_ENTER)
			{
				okButton.doClick();
			}
		}

	}
}
