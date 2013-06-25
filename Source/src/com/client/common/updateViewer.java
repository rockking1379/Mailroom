package com.client.common;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class updateViewer extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JButton okButton = null;
	private File update = null;
	
	public updateViewer(File file)
	{
		this.setVisible(false);
		//Frame
		this.setLocation(100, 100);
		this.setSize(480,640);
		this.setBackground(Color.CYAN);
		this.setLayout(null);
		this.addKeyListener(new keyListener());
		this.setTitle("About This Update");
		
		//File setting
		update = file;
		
		//Text Area
		JTextArea updateText = new JTextArea();
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
			while((line = br.readLine()) != null)
			{
				uTxt += line + "\n";
			}
			updateText.setText(uTxt);
		}
		catch(Exception e)
		{
			//Don't worry
			//This is an add-on
		}
		finally
		{
			try
			{
				if(fStream != null)
				{
					fStream.close();
				}
				if(dis != null)
				{
					dis.close();
				}
				if(br != null)
				{	
					br.close();
				}
			}
			catch(Exception e)
			{
				//Ignore Exceptions
			}
		}
		this.add(updateText);
		
		//Okay Button
		okButton = new JButton();
		okButton.setText("OK");
		okButton.addActionListener(new actionListener());
		okButton.setLocation(10, 555);
		okButton.setSize(440, 40);
		this.add(okButton);
	}
	
	private class actionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource() == okButton)
			{
				update.delete();
				dispose();
			}
		}
	}
	
	private class keyListener implements KeyListener
	{
		public void keyPressed(KeyEvent ke) 
		{
			if(ke.getKeyCode() == KeyEvent.VK_ENTER)
			{
				okButton.doClick();
			}
		}

		public void keyReleased(KeyEvent ke) 
		{
			if(ke.getKeyCode() == KeyEvent.VK_ENTER)
			{
				okButton.doClick();
			}
		}

		public void keyTyped(KeyEvent ke) 
		{
			if(ke.getKeyCode() == KeyEvent.VK_ENTER)
			{
				okButton.doClick();
			}
		}
		
	}
}
