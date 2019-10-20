/** Help command allows the player to get a list of commands available to them in the game.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import java.util.HashMap;
import java.util.Map;

import com.Game;

import commands.ICommand;
import output.Mode;
import output.OutputHandler;

public class Help implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Displays a list of valid in-game commands.";
	
	/** Implements the 'help' command into the game. This will take the list of command words from the main Game class, iterate over them and output them to the screen using the OutputHandler class.
	 * */
	public void execute(Game game, String[] userInput) {
		
		OutputHandler.output("You have the following commands avilable to you: ", Mode.CONSOLE);
		
		HashMap<String, ICommand> commands = game.getCommands();
		
		for(Map.Entry<String, ICommand> e : commands.entrySet()) {
			
			OutputHandler.output(e.getKey() + " - " + e.getValue().description(), Mode.CONSOLE);
		}
	}

	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
