package commands.valid;

import java.util.List;

import com.Game;

import commands.ICommand;
import output.Mode;
import output.OutputHandler;

public class Help implements ICommand{

	/** Implements the 'help' command into the game. This will take the list of command words from the main Game class, iterate over them and output them to the screen using the OutputHandler class.
	 * */
	public void execute(Game game, String[] userInput) {
		
		OutputHandler.output("You have the following commands avilable to you: ", Mode.CONSOLE);
		
		List<String> validCommands = game.getCommandWords();
		
		for(String command : validCommands) {
			
			OutputHandler.output(command, Mode.CONSOLE);
		}
	}
}
