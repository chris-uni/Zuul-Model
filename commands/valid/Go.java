/** Go enables players to move between rooms. Will check the direction specified by the player, if valid it will move the plahyer into the new room.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import java.lang.reflect.InvocationTargetException;

import com.Game;

import commands.ICommand;
import entities.Room;
import output.OutputHandler;
import player.Player;

public class Go implements ICommand{

	// The description of this command to be displayed on 'help' command.
	private String description = "Enables you to move into new rooms. E.g. 'go north'";
	
	@Override
	public void execute(Game game, String[] userInput) {
		
		int inputLength = userInput.length;
		
		Player currentPlayer = game.getCurrentPlayer();
		Room currentRoom = currentPlayer.getCurrentRoom();
		
		// First check to see if there is another input word, i.e. 'north'.
		if(inputLength != 1) {
			
			// The direction the player wants to move.
			String direction = userInput[1];
			
			// Now check to see if the direction specified actually has a room.
			if(currentRoom.getExit(direction) != null) {
				
				// Okay, now we can move to the new room.
				String newRoom =  currentRoom.getExit(direction);
				
				// Updates the current room of this specific player.
				currentPlayer.updateRoom(game.getRoom(newRoom));
				
				// Attempts to make a new instance of the 'Look' object which when executed, describes the room to the player.
				ICommand look;
				
				try {
					
					// Now we have changed rooms, we can have a look around.
					look = (ICommand) Class.forName("commands.valid.Look").getConstructor().newInstance();
					look.execute(game, userInput);
				} 
				catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					
					e.printStackTrace();
				}
			}
			
			else {
				
				OutputHandler.output("Error, there is no exit that way! Staying here for now.");
			}
		}
		else {
			OutputHandler.output("Error, expecting a direction to move. Staying here for now.");
		}
		
	}


	/** Allows the 'help' listing to get the commands description, lets the player know what each command does.
	 * */
	@Override
	public String description() {
		
		return this.description;
	}
}
