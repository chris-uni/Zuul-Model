/** Allows the player to quit the game.
 * 
 * @author Chris Wing (cjmw2)
 * */

package commands.valid;

import com.Game;
import com.State;

import commands.ICommand;
import output.Mode;
import output.OutputHandler;

public class Quit implements ICommand{

	@Override
	public void execute(Game game, String[] userInput) {
		
		OutputHandler.output("Cya!", Mode.CONSOLE);
		game.updateGameState(State.QUIT);
	}
}
