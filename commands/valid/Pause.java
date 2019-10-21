/** Allows the player to pause the game. 
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import com.Game;
import com.State;

import commands.ICommand;
import output.Mode;
import output.OutputHandler;

public class Pause implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Pauses the game.";
	
	@Override
	public void execute(Game game, String[] userInput) {
		
		OutputHandler.output("Game paused. Press 'p' to continue.", Mode.CONSOLE);
		game.updateGameState(State.PAUSE);			
	}

	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
