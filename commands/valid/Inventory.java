/** The Inventory command lists the contents of the players inventory.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import com.Game;

import commands.ICommand;
import output.Mode;
import output.OutputHandler;
import player.Player;

public class Inventory implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Lists the items in your inventory.";
	
	@Override
	public void execute(Game game, String[] userInput) {
		
		Player player = game.getCurrentPlayer();
		OutputHandler.output(player.getInventory().printInventory(), Mode.CONSOLE);
	}

	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
