/** Allows the player to take a specified item from a room, providing that the item exists.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import java.util.HashMap;

import com.Game;

import commands.ICommand;
import entities.Item;
import entities.Room;
import output.Mode;
import output.OutputHandler;
import player.Player;
import tools.Tools;

public class Take implements ICommand{

	@Override
	public void execute(Game game, String[] userInput) {
	
		Room currentRoom = game.getCurrentRoom();
		Player player = game.getPlayer();
		
		/*
		 * 0. Did the user enter in an item name, or simply type 'take'?
		 * 1. Get the name of the item the player inputed.
		 * 2. Check the current room for items of that name.
		 * 3. If exists, add the item. (Weight checking is done in the inventory class, method addItem.)
		 * 4. Else, output error message.
		 * */
		
		// Only the take command was entered..
		if(userInput.length == 1) {
			
			OutputHandler.output("Error, you must enter an item name to take!", Mode.CONSOLE);
		}
		else{
			
			String itemName = Tools.firstLetterToCapital(userInput[1]);
			
			HashMap<String, Item> roomItems = currentRoom.getItems();
			
			// Here we check the existence of the item in the room. If null == item does not exist!
			if(roomItems.get(itemName) != null) {
				
				player.addItem(roomItems.get(itemName));
				currentRoom.getItems().remove(itemName);
				
				OutputHandler.output("Successfully taken the " + itemName + "!", Mode.CONSOLE);
			}
			else {
				
				OutputHandler.output("Error, '" + itemName + "' is not an item in this room!", Mode.CONSOLE);
			}
		}
	}
}
