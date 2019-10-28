/** This is the games input parser. Will wait for input from the user and hand this off to the CommandHandler class.
 * 
 * @author Chris Wing (cjmw2)
 * */

package parser;

import java.util.NoSuchElementException;
import java.util.Scanner;

import gui.GuiContainer;
import output.Mode;
import output.OutputHandler;

public class Parser {

	private Scanner scanner;
	
	public Parser() {
		
		this.scanner = new Scanner(System.in);
	}
	
	/*
	 * Uses the scanner object to get the users input from the command line.
	 * */
	public String[] getUserInput(Mode gameMode) throws NoSuchElementException{
		
		try {
			if(gameMode == Mode.CONSOLE) {
				
				OutputHandler.output("> ");
				
				return this.scanner.nextLine().toLowerCase().split(" ");
			}
			else if(gameMode == Mode.GUI){
				
				// return GuiContainer.getConsole().getText().toLowerCase().split(" ");
				
				// return GuiContainer.getConsole().
				
				return null;
			}
		}
		catch(NoSuchElementException e) {
			
			OutputHandler.output("You shouldn't be doing that! o.O");
			
			return "erorr".split(" ");
		}
		
		return null;
	}
	
	/** When the game is done, we can close the scanner to prevent memory leaks.
	 * */
	public void cleanup() {
		
		this.scanner.close();
	}
}
