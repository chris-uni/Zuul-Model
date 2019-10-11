package commands;

import com.Game;

public interface ICommand {

	void execute(Game game, String[] userInput);

}
