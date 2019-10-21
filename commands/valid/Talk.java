/** Allows the player to talk to NPC's within the game.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import com.Game;

import commands.ICommand;
import entities.NPC;
import entities.Room;
import output.Mode;
import output.OutputHandler;
import player.Player;
import tools.Tools;

public class Talk implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Talk to an NPC in your current room. E.g. 'talk to chris'";
	
	@Override
	public void execute(Game game, String[] userInput) {
		
		/* This command is used to talk to the npcs in the game.
		 * 
		 * 1. Check to see if the player entered an npc name.
		 * 2. Check to see if that npc is in the room.
		 * 3. If they are, get that npc's dialog and print to screen.
		 * 4. If not, error message.
		 * */
		
		Player player = game.getCurrentPlayer();
		Room currentRoom = player.getCurrentRoom();
		
		if(userInput.length > 1) {
			
			String npcName = Tools.firstLetterToCapital(userInput[1]);
			NPC npc = currentRoom.getNPC(npcName);
			
			if(npc != null) {
				
				OutputHandler.output(npcName + ": " + npc.getDialog(), Mode.CONSOLE);
			}
			else {
				
				OutputHandler.output("Error, " + npcName + " is not in this room!", Mode.CONSOLE);
			}
		}
		else{
			
			OutputHandler.output("Error, you must enter an NPC's name!", Mode.CONSOLE);
		}
	}

	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
