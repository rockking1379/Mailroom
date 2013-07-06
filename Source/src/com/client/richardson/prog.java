package com.client.richardson;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.client.common.DatabaseManager;

public class prog
{
	// Start Database Manager
	// Start Login Screen
	// Run Client
	static DatabaseManager dbManager;

	public static void main(String[] args)
	{
		dbManager = new DatabaseManager();
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
						dbManager.setDatabase(temp);
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
							dbManager.setFile(temp);
						}
					}
				}
				br.close();
			}
			catch (Exception e)
			{
				// Do nothing
			}
			dbManager.setup();
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

	public static DatabaseManager getDbManager()
	{
		return dbManager;
	}
}
