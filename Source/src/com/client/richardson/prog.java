package com.client.richardson;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class prog 
{
	//Start Database Manager
	//Start Login Screen
	//Run Client
	//hi there
	//how are you?
	public static void main(String[] args)
	{
		File settings = new File("./properties.prop");
		if(settings.exists())
		{
			try
			{
				FileInputStream fStream = new FileInputStream(settings);
				DataInputStream dis = new DataInputStream(fStream);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		
				String settingLine;
				while((settingLine = br.readLine()) != null)
				{
					//Read Settings
					String setting = "";				
					int index = 0;
					while(settingLine.charAt(index) != ':')
					{
						setting += settingLine.charAt(index);
						index++;
					}
					if(setting.toUpperCase().equals("DATABASE"))
					{
						//Read in Database configuration
					}
					else
					{
						if(setting.toUpperCase().equals("PERSONS"))
						{
							//Read Persons File location
						}
					}
				}
			}
			catch(Exception e)
			{
				//Do nothing
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Settings File Not Found.");
			
			JFileChooser fc = new JFileChooser();
			fc.showDialog(null, "SELECT");
			fc.setFileFilter(null);
			File database = fc.getSelectedFile();
			fc.showDialog(null, "SELECT");
			File persons = fc.getSelectedFile();
			
			try 
			{
				if(settings.createNewFile())
				{
					FileWriter fOutput = new FileWriter(settings);
					BufferedWriter bw = new BufferedWriter(fOutput);
					bw.write("DATABASE:" + database.getAbsolutePath() + "\n");
					bw.write("PERSONS:" + persons.getAbsolutePath() + "\n");
					bw.close();
					fOutput.close();
				}
			} 
			catch (Exception e) 
			{
				//Ignore the exceptions
			}
			
			
		}
	}
}
