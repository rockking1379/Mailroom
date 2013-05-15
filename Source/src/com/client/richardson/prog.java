package com.client.richardson;

import java.io.*;

public class prog 
{
	//Start Database Manager
	//Start Login Screen
	//Run Client
	public static void main(String[] args)
	{
		try
		{
			FileInputStream fStream = new FileInputStream("./properties.prop");
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
			//Probably means file doesn't exist, start to set up mode
			e.printStackTrace();
		}
	}
}
