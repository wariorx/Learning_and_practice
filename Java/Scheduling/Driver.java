import java.util.InputMismatchException;
//import javafx.stage.*;
import java.util.Scanner;
import java.io.IOException;

public class Driver {
	
	private final static String FORMAT = ".csv";
	
	public static void main (String [] args)
	{
		Scanner keyboard = new Scanner(System.in);
		char choice = '0';
		
		do
		{	
			choice = menu(keyboard);
			
			if(choice != '1' && choice != '2' && choice != '3' && choice != '4' && choice != '5')
				System.out.println("Please enter a valid option");
			
		switch (choice)
		{		
			case '1':
				add(keyboard);
				
				break;
			case '2':
				modify(keyboard);
				break;
				
			case '5':
				System.out.println("Closing program");
				break;
		}
		
		}while(choice != '5');
		
	}
	
	private static char menu(Scanner keyboard)
	{
		System.out.println("Select an option: ");
		System.out.println("1. Add file");
		System.out.println("2. Modify file");
		System.out.println("3. Review a file");
		System.out.println("4. Remove file");
		System.out.println("5.Quit");
		
		return keyboard.nextLine().charAt(0);
		
	}
	
	private static boolean check(String name)
	{
		char [] array = name.toCharArray();
		int i = 0;
		
		return supportCheck(array, i);
	}
	
	/*
	 * Recursively checks that every character is a letter
	 */
	private static boolean supportCheck(char [] array, int i)
	{
		if(i == array.length)
			return true;
		
		if ( !Character.isLetter(array[i]) && !Character.isWhitespace(array[i]))
			return false;
		
		return supportCheck(array, i+1);
	}
	
	/*
	 * Adds the information entered to a file. If the file doesn't exist, it creates one.
	 */
	private static void add(Scanner keyboard)
	{
		boolean flag = false;
		Hours time = null;
		String name = "", fileName = "";
		double hours = 0;
		
	do{ //path input
		System.out.println("Enter the file path. If blank, a file is created in the program's directory");
		String path = keyboard.nextLine();
			
		flag = true; //the flag is set to true, since here it'll only be false when the path is not valid
		if(path.equals(""))
			time = new Hours();
		else
		{
			try
			{
			time = new Hours(path);	
			}
			catch(IOException e)
			{
			System.out.println("Invalid path, please enter a valid one");
			flag = false;
			}
		
		}
	}while(!flag);
	
	do{// name input 
		System.out.println("Enter a name for the file:");
		fileName = keyboard.nextLine();
		
		System.out.println("Enter an employee's name:");
		name = keyboard.nextLine();
		
		//input validation
		flag = check(name);
		
		if(flag)
			time.setName(fileName);
		else
			System.out.println("Please enter a valid name (Letters and Spaces only)");
		
	  }while (!flag);
	
	do{//time input
		flag = true;
		try{
		System.out.println("Enter the number of hours (5-20)");
		hours = keyboard.nextDouble();
		
		}
		catch(InputMismatchException e)
		{
			flag = false;
			System.out.println("Please be sure to enter a number");
			keyboard = new Scanner(System.in); // scanner broke, so a new one is made
		}
		
		if(flag && (hours < 5 || hours > 20) )
		{
			System.out.println("Invalid amount of hours");
			flag = false;
		}
		
	}while(!flag);
	
	if(!time.createFile())
		System.out.println("Something went wrong with the creation");
	
	else if(!time.setUp(name,hours))
		System.out.println("Something went wrong with the input");
	else
		System.out.println("File created!");
	
	keyboard.nextLine(); // flush the buffer
	
	
				
	}//end of add
	
	private static void modify(Scanner keyboard)
	{
		char choice = 'a';
		
		do{
			choice = modMenu(keyboard);
			
			if(choice != '1' && choice != '2' && choice != '3')
				System.out.println("Please select a valid option");
			
			switch(choice)
			{
				case '1':
					overWrite(keyboard);
					break;
				case '2':
					expand(keyboard);
					break;
			}
		}while(choice != '3');
		
		System.out.println("Returning to main menu...");
	}		
	
	
	private static char modMenu(Scanner keyboard)
	{
		System.out.println("Modification options:");
		System.out.println("1.Overwrite an existing file");
		System.out.println("2.Add to an existing file");
		System.out.println("3.Return to main menu");
		return keyboard.nextLine().charAt(0);
	}
	
	private static void overWrite(Scanner keyboard)
	{
		char choice = 'a';
		
		do{
			choice = overMenu(keyboard);
			
			switch(choice)
			{
				case '1':
					hourChange(keyboard);
					break;
				case '2':
					nameChange(keyboard);
					break;
				case '3':
					
					break;
			}
			
		}while(choice != '4');
		
	}
	
	private static char overMenu(Scanner keyboard)
	{
		char choice = 'a';
		
		System.out.println("Overwrite options: ");
		System.out.println("1.Make changes to an employee's hours");
		System.out.println("2.Rename an employee");
		System.out.println("3.Change an employee's schedule");
		System.out.println("4.Return to the file modification menu");
		
		choice = keyboard.nextLine().charAt(0);
		
		return choice;
	}
	/*
	 * Gets the input necessary to make changes to an employee's hours on a schedule file
	 */
	private static void hourChange(Scanner keyboard)
	{
		Hours file = new Hours();
		boolean flag = false;
		String name = null;
		
		System.out.println("Select a file in the Java window that just opened");
		
		String path = file.pickAFile();
				
		System.out.println("File selected is: " + path);
		
		do{
			do{
				System.out.println("Enter the name of the employee whose hours you want to change");
				name = keyboard.nextLine();
				
				flag = check(name);
				
				if(!flag)
					System.out.println("Please enter a valid name (Letters and Spaces only)");
				
			}while(!flag);
				
				int row = file.seekRow(name);
				
				if(row == -1)
				{
					if(name.toLowerCase().equals("x"))
						hourChange(keyboard); //return to the file selection, at the beginning of the method
					else
					{
					System.out.println("Employee \"" + name + "\" wasn't found in the file, please try again, or enter \"x\" for the name to go back to the file selection");
					flag = false; // this should be the only place where flag is set to false, to prevent infinite looping on the recursive call made by the else if
					}
				
				}
				else if(row == -2) //note that this is unlikely since the user is allowed to manually pick the file
				{
					System.out.println("Invalid file,you will be returned to the file chooser option");
					hourChange(keyboard); //use recursion to return user to the beginning of this method
				}
				else
				{
					System.out.println("Employee found in row " + (row +1) );
					
					double hours = 0;
					
					do{//time input
						flag = true;
						try{
						System.out.println("Enter the new number of hours (5-20)");
						hours = keyboard.nextDouble();
						keyboard.nextLine(); // clean scanner 
						}
						catch(InputMismatchException e)
						{
							flag = false;
							System.out.println("Please be sure to enter a number");
							keyboard = new Scanner(System.in); // scanner broke, so a new one is made
						}
						
						if(flag && (hours < 5 || hours > 20) )
						{
							System.out.println("Invalid amount of hours");
							flag = false;
						}
						
					}while(!flag);
					
					char choice = 'a';
					do{
						System.out.println("The hours for " + name + " will be set to " + hours + ", is that okay? Y/N");
						choice = keyboard.nextLine().toLowerCase().charAt(0);
						
						if(choice == 'y')
							flag = file.replaceContent(file.getNumHours(-1,name), new Double(hours).toString(), row); //figure out how to get the value to be replaced
						
						else if(choice != 'n' && choice != 'y')
							System.out.println("Please enter \"Y\" or \"N\"");
					
					}while(choice != 'n' && choice != 'y');
					
					if(!flag)//IOException thrown
						System.out.println("There was an error, please try again");
					else
						System.out.println("Hours changed!");
					
				}
				
		}while(!flag);
	}
	
	private static void nameChange(Scanner keyboard)
	{
		
	}
	
	private static void expand(Scanner keyboard)
	{
		boolean flag = false;
		Hours time = new Hours();
		String name = "";
		double hours = 0;
		
	do{ //path input
		System.out.println("Select a file in the Java window that just opened");
		
		String path =time.pickAFile();
				
		System.out.println("File selected is: " + path);
		
		flag = true; //the flag is set to true, since here it'll only be false when the path is not valid
		/*
		try
		{
			time = new Hours(path);
		}
		catch(IOException e)
		{
			System.out.println("Invalid path, please enter a valid one");
			flag = false;
		}
		*/
		
	}while(!flag);
	
	do{// name input 
		System.out.println("Enter the person's name:");
		name = keyboard.nextLine();
		
		//input validation
		flag = check(name);
		
		if(flag)	
			time.setName(name);
		else
			System.out.println("Please enter a valid name (Letters and Spaces only)");
		
	  }while (!flag);
	
	do{//time input
		flag = true;
		try{
		System.out.println("Enter the number of hours (5-20)");
		hours = keyboard.nextDouble();
		
		}
		catch(InputMismatchException e)
		{
			flag = false;
			System.out.println("Please be sure to enter a number");
			keyboard = new Scanner(System.in); // scanner broke, so a new one is made
		}
		
		if(flag && (hours < 5 || hours > 20) )
		{
			System.out.println("Invalid amount of hours");
			flag = false;
		}
		
	}while(!flag);
	
	if(!time.expand(name,hours))
		System.out.println("Something went wrong with the input");
	else
		System.out.println("Data added to file");
	
	keyboard.nextLine(); // flush the buffer
	}
	
}
