/**
 * This class handles the users input via the command line.
 * 
 * It has been separate out into a separate class with separate methods for each command to make it more modular. Also it makes it easier to invoke one action from within another actions method (i.e. looking around
 * the room when you move into a new one).
 * 
 * @author Chris Wing
 */
package parser;

import com.Game;
import com.State;

import output.Mode;
import output.OutputHandler;

public class CommandHandler {

	private Game game;
	
	public CommandHandler(Game game) {
		
		this.game = game;
	}
	
	/** Main handler, this will look at the first command word in the users input and decide what to do. If input not recognised, error message printed to console.
	 * */
	public void handle(Parser parser) {
		
		// The input from the user via the command line.
		String[] userInput = parser.getUserInput();
			
		if(userInput[0].equals("look")) {
			
			this.handleLook();
		}
		else if(userInput[0].equals("go")) {
			
			// User wants to move room.
			this.handleGo(userInput);

		}
		else if(userInput[0].equals("quit")) {
			
			// Quit game.
			this.handleQuit();
		}
		
		else if(userInput[0].equals("pause")) {
			
			// Pause game.
			this.handlePause();
		}
		else {
			
			// Fallback, we don't understand the users command.
			OutputHandler.output("I'm sorry, I dont know what you mean by: '" + userInput[0] + "'", Mode.CONSOLE);
		}
	}
	
	/** Handles the quit command, will simple display a message to the console and change the game state to QUIT.
	 * */
	private void handleQuit() {
		
		OutputHandler.output("Cya!", Mode.CONSOLE);
		game.updateGameState(State.QUIT);
	}
	
	/** Handles the pause command, will simply display a message to the console and change the game state to PAUSE. More work needed here to have a 'press any key to resume' functionality.
	 * */
	private void handlePause() {
		
		OutputHandler.output("Game paused. Type resume to continue.", Mode.CONSOLE);
		game.updateGameState(State.PAUSE);	
	}
	
	/** Handles the look command, displays the current rooms description, exits and items to the console.
	 * */
	private void handleLook() {
		
		// Player looks around room. Display description, exits and items.
		
		OutputHandler.output(game.getCurrentRoom().getDescription() + " There are " + game.getCurrentRoom().getExits().size() + " exits in this room.", Mode.CONSOLE);
		OutputHandler.output(game.getCurrentRoom().getExitAsString(), Mode.CONSOLE);	
	}
	
	/** Handles the go command, will first check to see the length of the inputs. If 1, we know no direction was specified. Else, if the direction specified does not map to an avilable exit, throw error.
	 * Else, change room. Once changed we call the look() method to get an idea of what's in the new room.
	 * */
	private void handleGo(String[] userInput) {
		
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
				
				// Now we have changed rooms, we can have a look around.
				this.handleLook();
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
