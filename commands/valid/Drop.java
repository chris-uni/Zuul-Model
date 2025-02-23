/** The Drop command allows the player to drop an existing item in their inventory into the current room they are in.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import com.Game;

import commands.ICommand;
import entities.Item;
import entities.Room;
import output.OutputHandler;
import player.Player;
import tools.Tools;

public class Drop implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Drop a specified item into your current room.";
	
	@Override
	public void execute(Game game, String[] userInput) {
		
		/* 1. check that the player entered in the name of an item to drop. 
		 * 2. If they did, check its an item in their inventory (i.e. a valid item name).
		 * 3. If so, add the item to the current rooms item HashMap
		 * 4. Remove the weight of the item from the players inventory (along with remove the item from the inventory).
		 * 5. Update. -> Could also run the Look command to update what the Room looks like?
		 * */
		
		Player player = game.getCurrentPlayer();
		Room currentRoom = player.getCurrentRoom();
		
		// They entered in a name!
		if(userInput.length > 1) {
			
			String itemName = Tools.firstLetterToCapital(userInput[1]);
			
			// The player has the specified item in its inventory and it is in-fact there!
			if(player.getInventory().hasItem(itemName)) {
				
				// The object of the item the player wants to drop.
				Item item = player.getInventory().getItem(itemName);
				
				player.removeItem(item);
				currentRoom.addItem(item);
				
				OutputHandler.output("You dropped the " + item.getName() + ".");
			}
			else {
				
				OutputHandler.output("Looks like you don't have '" + itemName + "' in your inventory!");
			}
		}
		else {
			
			OutputHandler.output("You need to enter the name of the item you wish to drop!");
		}
	}
	
	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
