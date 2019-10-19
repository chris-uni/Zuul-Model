/** Go enables players to move between rooms. Will check the direction specified by the player, if valid it will move the plahyer into the new room.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import java.lang.reflect.InvocationTargetException;

import com.Game;

import commands.ICommand;
import output.Mode;
import output.OutputHandler;

public class Go implements ICommand{

	@Override
	public void execute(Game game, String[] userInput) {
		
		int inputLength = userInput.length;
		
		// First check to see if there is another input word, i.e. 'north'.
		if(inputLength != 1) {
			
			// The direction the player wants to move.
			String direction = userInput[1];
			
			// Now check to see if the direction specified actually has a room.
			if(game.getCurrentRoom().getExit(direction) != null) {
				
				// Okay, now we can move to the new room.
				String newRoom =  game.getCurrentRoom().getExit(direction);
				
				// Updates the current room the player is in.
				game.updatCurrentRoom(game.getAllRooms().get(newRoom));
				
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
				
				OutputHandler.output("Error, there is no exit that way! Staying here for now.", Mode.CONSOLE);
			}
		}
		else {
			OutputHandler.output("Error, expecting a direction to move. Staying here for now.", Mode.CONSOLE);
		}
		
	}
}
