package commands.valid;

import com.Game;

import commands.ICommand;
import entities.Item;
import entities.NPC;
import entities.Room;
import output.Mode;
import output.OutputHandler;
import player.Player;
import tools.Tools;

public class Give implements ICommand{

	@Override
	public void execute(Game game, String[] userInput) {
		
		/* This command is used to give npc's items that you have picked up.
		 * 
		 * Command should be of format: 'give {itemName} to {npcName}'
		 * 
		 * 1. Check if the user actually entered in an item name.
		 * 2. Check to see if they have it in their inventory.
		 * 3. Check to see if the npc name is in the current room as them.
		 * If yes to both, give the item specified to the npc.
		 * Else, error, dont have item | Error, not in the same room as npc!
		 * */
		
		Player player = game.getPlayer();
		Room currentRoom = game.getCurrentRoom();
		
		if(userInput.length > 1) {
			
			String checkTo = userInput[2];
			
			// Used to check if we are still adhering to the correct command format.
			if(checkTo.equals("to")) {
				
				// Name of the item, now we know the name exists in the command string.
				String itemName = Tools.firstLetterToCapital(userInput[1]);
				
				// Now we check the item to see if in players inventory.
				if(player.getInventory().hasItem(itemName)) {
					
					// The item the player wishes to give to the NPC.
					Item item = player.getInventory().getItem(itemName);
					
					// Now we need to check to see if the specified NPC is in the players current room.
					
					String npcName = Tools.firstLetterToCapital(userInput[3]);
					
					if(currentRoom.hasNPC(npcName)) {
						
						// Now we can give the item to the NPC!
						
						NPC npc = currentRoom.getNPC(npcName);
						
						// Add the item to the NPC's inventory and remove if from yours.
						npc.getInventory().addItem(item);
						player.getInventory().removeItem(item);
						
						OutputHandler.output("You gave " + npcName + " your " + itemName, Mode.CONSOLE);
					}
					else {
						
						OutputHandler.output("Error, " + npcName + " is not in your current room!", Mode.CONSOLE);
					}
				}
				else {
					
					OutputHandler.output("Error, you dont have " + itemName + " in your inventory!", Mode.CONSOLE);
				}
			}
			else {
				
				OutputHandler.output("Sorry, I dont know what you mean by: " + checkTo, Mode.CONSOLE);
			}
		}
		else {
			OutputHandler.output("Error, you must enter an item name to give!", Mode.CONSOLE);
		}
		
	}
}