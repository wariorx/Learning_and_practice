import java.io.*;
import java.awt.FileDialog;
import java.awt.Frame;

public class Hours {
	
	private String path, name;
	//private BufferedReader read;
	private String split;
	private final static String FORMAT = ".csv";
	
	public Hours()
	{
		path = "";
		name = "newFile";
		//read = null;
		split = ",";
	}
	
	public Hours(String path) throws IOException
	{
		this.path = path;
		name = "newFile";
		split = ",";
		//read = new BufferedReader(new FileReader(this.path));
	}
	
	public Hours(String path, String split)throws IOException
	{
		this.path = path;
		this.split = split;
		name = "newPath";
		//read = new BufferedReader(new FileReader(this.path));
	}
	
	//setters and getters
	
	public String getPath()
	{
		return this.path;
	}
		
	public String getName()
	{
		return this.name;
	}
	
	/*
	 * Not yet tested
	 * Returns the number of hours for a specified row (person). If row has a value of -1, it'll search by name. If name is null,
	 * it'll search by row. If both are filled, it defaults to name.
	 */
	public String getNumHours(int row, String name)
	{
		BufferedReader read = null;
		String hours = null;
		try{
			read = new BufferedReader(new FileReader (this.path));
			String line = null;
			
			if(name!= null)
			{
				while( (line = read.readLine()) != null)
				{
					String [] line_array = line.split(split);
					
					if(name.equals(line_array[0]))
						hours = line_array[1];
				}
				
			}
			else if (row != -1)
			{
				int count = 0;
			
				while( (line = read.readLine()) != null)
				{
					if(row == count)
						hours = line.split(split)[1];
				
					count++;	
				}
			}
				
			
			read.close();
		}
		catch(IOException e)
		{
			return null;
		}
		
		return hours;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
		
	public void setName(String name)
	{
		this.name = name;
	}
	
	/*
	 * Creates a file with the file path. If blank, file path = name
	 */
	public boolean createFile()
	{	
		if(this.path.equals(""))
			this.path = this.name + FORMAT;
		else
			this.path = this.path + "\\" + this.name + FORMAT;
		
		File newFile = new File(path);
		
		try{
		newFile.createNewFile();
		}
		catch(IOException e)
		{
			return false;
		}
		
		
		return true;
	}
	
	/*
	 * Adds the name of the person that file is supposed to represent.
	 * calls for the support method hours to append the hours after the name
	 * 
	 */
	public boolean setUp(String name, double time)
	{	
		try
		{
			File newFile = new File(path);
			PrintWriter write = new PrintWriter(newFile);
			write.append(name + split);
			hours(time, write);
		}
		catch(IOException e)
		{
			return false;
		}
		
		return true;
	}
	
	/*
	 * Deals with the doubles to be appended to the file, supports setUp(). Also closes the file stream,
	 */
	private void hours(double time, PrintWriter write)
	{	
		write.append(new Double(time).toString());
		write.close();
	}
	
	/*
	 * Adds content to an already existing file
	 */
	
	public boolean expand(String name, double hours)
	{
		PrintWriter write = null;
		
		try{
			//BufferedReader read = new BufferedReader(new FileReader(this.path));
			FileWriter file = new FileWriter(this.path, true);
			write = new PrintWriter(file);
		}
		catch(IOException e) // the file does not exist
		{
			return false;
		}
		
		
		write.write("\n" + name + this.split + new Double(hours).toString());
		write.close();
		
		return true;
	}
	
	
	/*
	 * not yet tested
	 * Replaces ALL content in the current file with the paramater. Returns true if replacing was successful, flase otherwise
	 */
	public boolean overWrite(String content)
	{
		PrintWriter write = null;
		
		try{
			//BufferedReader read = new BufferedReader(new FileReader(this.path));
			FileWriter file = new FileWriter(this.path, false);
			write = new PrintWriter(file);
		}
		catch(IOException e) // the file does not exist
		{
			return false;
		}
		
		write.write(content);
		
		write.close();
		return true;
	}
	
	/*
	 * Looks through the file to find the employee's name. Returns -2 if file path isn't found; -1 if employee name isn't found.
	 */
	public int seekRow(String name)
	{
		BufferedReader read = null;
		String line = null;
		
		try{
			 read = new BufferedReader(new FileReader(this.path));
			 int row = 0;
			 
			 while((line = read.readLine()) != null)
			 {
				 String [] array = line.split(split);
				 
				 if(array[0].toLowerCase().equals(name.toLowerCase())) //prevent capitalization issues
				 {
					 read.close();
					 return row; // employee was found in specific row		 
				 }
				 
				row++;
					 
			 }
			 
			 read.close();
		}
		catch(IOException e)
		{
			return -2; //incorrect file path / IOException
		}
		
		return -1; // employee was not found
		
	}
	/*
	 * Replaces a specific set of characters in a specific row (line) of a file
	 */
	public boolean replaceContent(String replaced, String input, int row)
	{
	
		BufferedReader read = null;
		String line = null;
		StringBuilder content = new StringBuilder(); //this will temporarily hold the current contents of the file
		int count = 0;
		
		try
		{
			 read = new BufferedReader(new FileReader(this.path));
			 
			 while((line = read.readLine()) != null) 
			 {	
				 
				 if(count == row)
				 	line = line.replaceAll(replaced, input);
			 
				  
				 content.append(line);
				 content.append("\n");
				 
				 count++;
			 }
			 
			 
			 read.close();
		}
		catch(IOException e)
		{
		return false;
		}
		
		String toFile = content.toString();
		
		
		return overWrite(toFile); //calls for overwrite method to add the new modified information to the file
	}
	
	/*
	 * Makes use of FileDialog to allow the user to select a file
	 */
	public String pickAFile()
	{
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    //String file = dialog.getFile();
	    
	    this.name = dialog.getFile();
	    this.path = dialog.getDirectory() + this.name; //+ "\\" + this.name;
	    
		return this.path;
	}
	

}


