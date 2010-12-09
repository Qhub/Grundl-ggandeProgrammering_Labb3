import java.util.Random;
import java.util.Scanner;

/**
 * This is a program that lets the user handle 100 positive values between 0 and 900.
 * The user can either:
 * Randomly generate 100 integers, sort the 100 values, get min-max-average-median values, and search for specific numbers.
 * The integers is presented in a table of data with 10 columns and 10 rows.
 * 
 * @version 1.0.0
 * @author Patrik 'Quimby' Danielsson
 *
 */

public class Solution_v1
{
	private int generatedNumbers[][] = new int[10][10]; //The array that'll hold our 100 positive integers
	
	/**
	 * The main function of the class.
	 * It'll only create an object of the start method, that'll handle the software for us.
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		Solution_v1 test = new Solution_v1();
		test.start(); //Starts our program
	}
	
	/**
	 * This is method that handles the program and choices the users makes.
	 * It will call for the Menu() method, and call for the methods that correspond to the user input.
	 * Theese methods are: Generate(), Sorting(), Values() and Searching() method.
	 * 
	 * It starts off with calling the Menu() method and a scanner.
	 * The user will make an input/choice in a do-while loop.
	 * The different methods will be called upon depending on which choice the user makes.
	 * 1 - Generate() : Will generate 100 positive numbers between 1-900.
	 * 2 - Sorting() : Will sort the 100 positive numbers generated from the Generate() method. If numbers have not been generated, it will tell the user so.
	 * 3 - Values() : Gives the user information about which values are the smallest, biggest, average and median. If the user have not generated and/or sorted numbers, it will tell the user that, and it won't call the method.
	 * 4 - Searching() : User can search for an integer, and get information if and where in the table of numbers it exist.
	 * 0 - Terminates the program.
	 */
	public void start() //Runs progrsm
	{
		int choice;
		Scanner input = new Scanner(System.in);
		Menu(); //Calls for the menu
		boolean generated = false; // Used to check if the numbers have been generated or not.
		boolean sorted = false; // Used to check if the numbers have been sorted.
		do
		{
			System.out.print("What do you want to do?: ");
			choice = input.nextInt();
			if (choice == 1) //If user wants to generate new numbers
			{
				for (int row = 0; row < 10; row++)
				{
					for (int counter = 0; counter < 10; counter++)
					{
						int tempGeneratedNumber = Generate();
						generatedNumbers[row][counter] = tempGeneratedNumber;
					}
				}
				printTable();
				generated = true; //Numbers have been generated
			}
			else if (choice == 2) //If user wants to sort the numbers
			{
				if(generated == true)
				{
					Sorting();
					sorted = true; //Numbers have been sorted
				}
				else
				{
					System.out.println("Numbers haven't been generated! Please generate numbers first.");
				}
			}
			else if (choice == 3) // Find smallest, biggest, average and median values.
			{
				if (sorted == true)
				{
					Values(); // Calls for method
				}
				else
				{
					System.out.println("The numbers haven't been sorted, or they haven't been generated.");
				}
			}
			else if (choice == 4)
			{
				if (generated == true && sorted == true)
				{
					Searching(); //Calls for the searching method
				}
				else
				{
					System.out.println("Either numbers haven't been generated, or they haven't been sorted.");
				}
			}
			else if (choice > 4) // If choice is faulty
			{
				System.out.println("Invalid input. Please pick between 1-4. 0 to exit.");
			}
		} while (choice != 0);
		System.out.println("Software terminated!");
	}

	/**
	 * This is the method for the Menu, and a smaller description of the program. 
	 * The menu is presented when the program runs, and gives the user a number of possible options.
	 */
	void Menu() // Outputs Description and Menu
	{
		System.out.println("*************************************************************");
		System.out.println("** With this software you can generate 100 positive        **");
		System.out.println("** numbers, sort them, get the max - min - average and     **");
		System.out.println("** median value, and search for numbers.                   **");
		System.out.println("*************************************************************");
		System.out.println("*************************************************************");
		System.out.println("** Menu:                                                   **");
		System.out.println("** 1. Generate new numbers                                 **");
		System.out.println("** 2. Sort numbers                                         **");
		System.out.println("** 3. Find the smallest, biggest, median and average value **");
		System.out.println("** 4. Search for numbers                                   **");
		System.out.println("** 0. Exit                                                 **");
		System.out.println("*************************************************************");
		
	}
	
	/**
	 * Generates positive integers between 1 - 900.
	 * 
	 * @return Random number between, and including, 1 - 900.
	 */
	private int Generate() // Generates random numbers
	{
		Random generator = new Random();
		int number = generator.nextInt(899) +1 ;
		return number ;
	}
	
	/**
	 * Sorts the 100 integers generated from the Generate() method, from highest to lowest.
	 * Will call for printTable() method last of all, to present the table of data for the user.
	 */
	private void Sorting() // Sorts numbers, highest to lowest
	{
		System.out.println("There's generated numbers..");
		System.out.println("Sorting them for you....");
		
		for (int row = 0; row < 10; row++)
		{
			for (int column = 0; column < 10; column++)
			{
				for (int counter = 0; counter < 10; counter++)
				{
					for (int i = 0; i < 10; i++)
					{
						if (generatedNumbers[row][column] > generatedNumbers[counter][i])
						{
							int tempHoldingSpace = generatedNumbers[row][column];
							generatedNumbers[row][column] = generatedNumbers[counter][i];
							generatedNumbers[counter][i] = tempHoldingSpace;
						}
					}
				}
			}
		}
		
		printTable(); //Calls for output method.
	} 
	
	/**
	 * Looks for the smallest, biggest, the average and the median value, in the table of data.
	 * Outputs the result for the user.
	 */
	public void Values() // Finds smallest, biggest, average and the median values
	{
		double sum = 0;
		double medianValues = (generatedNumbers[4][9] + generatedNumbers[5][0]);
		double medianValueAverage = medianValues / 2;
		for (int row = 0; row < 10; row++)
		{
			for (int column = 0; column < 10; column++)
			{
				sum += generatedNumbers[row][column];
			}
		}
		double averageValue = sum / 100;
		System.out.println("The biggest number is: " + generatedNumbers[0][0]);
		System.out.println("The smallest number is: " + generatedNumbers[9][9]);
		System.out.println("The average value is: " + averageValue);
		System.out.println("The median value is: " + medianValueAverage);
	}
	
	/**
	 * Method used for searching for certain integers, inputed by the user.
	 * It will go thru the entire two-dimensional array,
	 * and if found, present which column and row the number is on.
	 * It'll also change the state of the foundNumber boolean to "true", to indicate a number have been found.
	 * If the number have not been found, the method will tell the user so.
	 */
	public void Searching() // Method for searching for a certain value
	{
		System.out.print("Which number are you looking for?: ");
		Scanner input = new Scanner(System.in);
		int userNumber = input.nextInt();
		boolean foundNumber = false; // Used to see if the number have been found or not
		for (int k = 0; k < 10; k++)
		{
			for (int i = 0; i < 10; i++)
			{
				if (userNumber == generatedNumbers[k][i])
				{
					System.out.println("Number found!");
					System.out.println("In column: " + (i + 1));
					System.out.println("Row: " + (k + 1));
					foundNumber = true;
				}
			}
		}
		if (foundNumber == false)
		{
			System.out.println("Sorry, your number could not be found.");
		}
	}
	
	/**
	 * A method which formats and presents the numbers, in the form of a table with 10 columns and 10 rows.
	 */
	public void printTable() // Outputs table of numbers on the screen
	{
		System.out.print("	| Column 1 |	| Column 2 |	| Column 3 |	| Column 4 |	| Column 5 |	| Column 6 |	| Column 7 |	| Column 8 |	| Column 9 |	| Column 10 |");
		System.out.println();
		int rows = 0;
		for (int row = 0; row < 10; row++)
		{
			rows += 1;
			System.out.print("Row " + rows);
			for (int i = 0; i < 10; i++)
			{
				System.out.print("		" + generatedNumbers[row][i]);
			}
			System.out.println();
		}
	}
	
}
