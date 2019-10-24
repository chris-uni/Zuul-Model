/** This is the games input parser. Will wait for input from the user and hand this off to the CommandHandler class.
 * 
 * @author Chris Wing (cjmw2)
 * */

package parser;

import java.util.Scanner;

import output.OutputHandler;

public class Parser {

	private Scanner scanner;
	
	public Parser() {
		
		this.scanner = new Scanner(System.in);
	}
	
	/*
	 * Uses the scanner object to get the users input from the command line.
	 * */
	public String[] getUserInput() {
		
		OutputHandler.output("> ");
		return this.scanner.nextLine().toLowerCase().split(" ");
	}
	
	/** When the game is done, we can close the scanner to prevent memory leaks.
	 * */
	public void cleanup() {
		
		this.scanner.close();
	}
}
