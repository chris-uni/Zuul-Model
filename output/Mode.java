/** This class contains the definitions of the different types of output in the game.
 * 
 * @author Chris Wing (cjmw2)
 * */

package output;

public enum Mode {

	/**
	 * Console mode, all output will be to std::console. */
	CONSOLE,
	/**
	 * GUI mode, game creates a window with buttons to represent the commands. */
	GUI
}
