/** Provides a command interface definition for all the AI commands within the game. This also allows it to be easy for new developers to implement their own AI commands, simply implement IAiCommand to get going.
 * 
 * @author Chris Wing (cjmw2)
 * */
package commands;

import com.Game;

public interface IAiCommand {

	public void execute(Game game);
}
