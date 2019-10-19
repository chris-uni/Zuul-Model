/** The look commands allows the player to 'look' around the room they are in, essentially giving a description of what they see.
 * 
 * @author Chris Wing (cjmw2)
 * */
package commands.valid;

import com.Game;

import commands.ICommand;
import entities.Room;
import output.Mode;
import output.OutputHandler;

public class Look implements ICommand{

	@Override
	public void execute(Game game, String[] userInput) {
		
		Room currentRoom = game.getCurrentRoom();
		
		OutputHandler.output(currentRoom.getDescription() + " There are " + currentRoom.getExits().size() + " exits in this room.", Mode.CONSOLE);
		OutputHandler.output(currentRoom.getExitAsString(), Mode.CONSOLE);
		OutputHandler.output("This room contains: " + currentRoom.getItemsAsString(), Mode.CONSOLE);
		OutputHandler.output(currentRoom.getNPCsAsString(), Mode.CONSOLE);
	 }
}
