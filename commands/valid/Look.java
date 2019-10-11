package commands.valid;

import com.Game;

import commands.ICommand;
import output.Mode;
import output.OutputHandler;

public class Look implements ICommand{

	@Override
	public void execute(Game game, String[] userInput) {
		
		OutputHandler.output(game.getCurrentRoom().getDescription() + " There are " + game.getCurrentRoom().getExits().size() + " exits in this room.", Mode.CONSOLE);
		OutputHandler.output(game.getCurrentRoom().getExitAsString(), Mode.CONSOLE);
	 }
}
