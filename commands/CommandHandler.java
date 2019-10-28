/**
 * The CommandHandler class handles user input from the in-game command line. The command system in this game is based on the Command Design Pattern; therefore each in-game command has been refactored out
 * into its own class, all implementing the ICommand interface. When the player enters in a command, this class will try to create an instance of the specified command, however if the word does not 
 * reflect with the name of a command, an error message will display to the player.
 * 
 * @author Chris Wing (cjmw2)
 * */
package commands;

import com.Game;

import output.OutputHandler;

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
	public void handleCommand(String[] userInput) {
				
		// The first word of the users command string. We will be comparing this to the available commands in package commands.valid.
		// Formats it to match the class name of commands, i.e. first letter is upper-case.
		String firstWord = formatCommand(userInput[0]);
		
		ICommand command = game.getCommand(firstWord);
		
		if(command != null) {
			
			command.execute(game, userInput);
		}
		else {
			
			// In the case the the user tries to get funny and input a command that actually isn't in the game, give them an error message.
			OutputHandler.output("\nSorry, I'm not sure what you mean by '" + userInput[0] + "'");
		}
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
