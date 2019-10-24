/** Allows the player to take a specified item from a room, providing that the item exists.
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

public class Take implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Take a specified item thats in your current room. E.g. 'take sword'";
	
	@Override
	public void execute(Game game, String[] userInput) {
		
		Player player = game.getCurrentPlayer();
		Room currentRoom = player.getCurrentRoom();
		
		/*
		 * 0. Did the user enter in an item name, or simply type 'take'?
		 * 1. Get the name of the item the player inputed.
		 * 2. Check the current room for items of that name.
		 * 3. If exists, add the item. (Weight checking is done in the inventory class, method addItem.)
		 * 4. Else, output error message.
		 * */
		
		// Only the take command was entered..
		if(userInput.length == 1) {
			
			OutputHandler.output("Error, you must enter an item name to take!");
		}
		else{
			
			String itemName = Tools.firstLetterToCapital(userInput[1]);
		
			Item roomItem = currentRoom.getItem(itemName);
			
			// Here we check the existence of the item in the room. If null == item does not exist!
			if(roomItem != null) {
				
				player.addItem(roomItem);
			}
			else {
				
				OutputHandler.output("Error, '" + itemName + "' is not an item in this room!");
			}
		}
	}

	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
