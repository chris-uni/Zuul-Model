/** Allows the player to quit the game.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import com.Game;
import com.State;

import commands.ICommand;
import output.OutputHandler;

public class Quit implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Quit the game.";
	
	@Override
	public void execute(Game game, String[] userInput) {
		
		OutputHandler.output("Cya!");
		game.updateGameState(State.QUIT);
	}

	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
