/**
 * The CommandHandler class handles user input from the in-game command line. The command system in this game is based on the Command Design Pattern; therefore each in-game command has been refactored out
 * into its own class, all implementing the ICommand interface. When the player enters in a command, this class will try to create an instance of the specified command, however if the word does not 
 * reflect with the name of a command, an error message will display to the player.
 * 
 * @author Chris Wing (cjmw2)
 * */
package commands;

import java.lang.reflect.InvocationTargetException;

import com.Game;

import output.Mode;
import output.OutputHandler;
import parser.Parser;

public class CommandHandler {

	private Game game;
	
	/** CommandHandler constructor. Allows the game object to be saved and used later.
	 * */
	public CommandHandler(Game game) {
		
		this.game = game;
	}
	
	/** Takes the users input, splits it up into individual words. Takes the first word, processes it, attempts to find the corresponding class name, creates an instance of that class and executes the 'execute'
	 * method within it. We know it will have 'execute' as the command classes all implements an interface 'ICommand' with method execute. 
	 * 
	 * If no class is found, for e.g. if the user inputs 'Hello, World', an error message is displayed onto the screen.*/
	public void handleCommand(Parser parser) {
		
		// The input from the user via the command line.
		String[] userInput = parser.getUserInput();
		

			
			// The first word of the users command string. We will be comparing this to the available commands in pkg commands.
			// Formats it to match the class name of commands, i.e. first letter is uppercase.
			String firstWord = formatCommand(userInput[0]);
			
			try {
				
				// Based on what the user inputs, we generate a new command object for that specific comamnd.
				ICommand command = this.commandFactory(firstWord);
				
				// Now we execute that command.
				command.execute(this.game, userInput);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				
				// In the case the the user tries to get funny and input a command that actually isnt in the game, give them an error message.
				OutputHandler.output("Sorry, I'm not sure what you mean by '" + userInput[0] + "'", Mode.CONSOLE);
			}

	}
	
	/** Will simply attempt to create a new instance of class 'commands.valid.ClassName'.
	 * */
	private ICommand commandFactory(String name) throws ClassNotFoundException, 
													  InstantiationException, 
													  IllegalAccessException, 
													  IllegalArgumentException, 
													  InvocationTargetException,
													  NoSuchMethodException, 
													  SecurityException{
		 
		return (ICommand)Class.forName("commands.valid." + name).getDeclaredConstructor().newInstance();
	}
	
	/** Formats the users input to match the naming convention of the commands within the 'commands.valid' package.
	 * i.e. turns 'look' into 'Look' to match the class 'commands.valid.Look'.
	 * */
	private String formatCommand(String word) {
		
		if(word.contentEquals("")) {
			
			return "Error";
		}
		
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
}
