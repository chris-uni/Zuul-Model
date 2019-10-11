
package commands.valid;

import com.Game;
import com.State;

import commands.ICommand;
import output.Mode;
import output.OutputHandler;

public class Pause implements ICommand{

	@Override
	public void execute(Game game, String[] userInput) {
		
		OutputHandler.output("Game paused. Type resume to continue.", Mode.CONSOLE);
		game.updateGameState(State.PAUSE);			
	}
}
