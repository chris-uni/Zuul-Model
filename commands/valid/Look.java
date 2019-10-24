/** The look commands allows the player to 'look' around the room they are in, essentially giving a description of what they see.
 * 
 * @author Chris Wing (cjmw2)
 * */
package commands.valid;

import com.Game;

import commands.ICommand;
import entities.Room;
import output.OutputHandler;
import player.Player;

public class Look implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Enables you to look around your current room.";
	
	@Override
	public void execute(Game game, String[] userInput) {
		
		Player player = game.getCurrentPlayer();
		Room currentRoom = player.getCurrentRoom();
		
		OutputHandler.output(currentRoom.getName() + ": " + currentRoom.getDescription() + " There are " + currentRoom.getExits().size() + " exits in this room.");
		OutputHandler.output(currentRoom.getExitAsString());
		OutputHandler.output("This room contains: " + currentRoom.getItemsAsString());
		OutputHandler.output(currentRoom.getNPCsAsString());
	 }

	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
