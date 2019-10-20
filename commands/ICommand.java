/** The command interface that all in-game commands implement. Contains a single method, execute.
 * 
 * @author Chris Wing (cjmw2)
 * */
package commands;

import com.Game;

public interface ICommand {
	
	public void execute(Game game, String[] userInput);
	
	public String description();

}
