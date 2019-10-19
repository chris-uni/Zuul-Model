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

	@Override
	public void execute(Game game, String[] userInput) {
		
		Player player = game.getPlayer();
		OutputHandler.output(player.getInventory().printInventory(), Mode.CONSOLE);
	}
}
