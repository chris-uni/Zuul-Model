/** Handles how the new AI thread should act. Based onn the list of ai commands from the game class, gets and creates a new command object; then proceeds to execute ai command.
 * 
 * @author Chris Wing (cjmw2)
 * */

package ai;

import java.lang.reflect.InvocationTargetException;

import com.Game;

import commands.IAiCommand;
import output.OutputHandler;

public class AICommand implements Runnable{

	private Game game;
	
	public AICommand(Game game) {
		
		this.game = game;
	}
	
	/** Run method, allows AI commands to execute here.
	 * 
	 * 1. Select random ai command from game class.
	 * 2. Attempt to create an instance of that command.
	 * 3. Once created, execute.
	 * 4. if errors, output messages.
	 * */
	public void run() {
		
		String commandName = game.getRandomCommand();
		
		IAiCommand aiCommand;
		
		try {
			
			aiCommand = (IAiCommand)Class.forName("commands.ai." + commandName).getDeclaredConstructor().newInstance();
		
			aiCommand.execute(game);
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			
			e.printStackTrace();
			OutputHandler.output("Error, AI command: " + commandName + " cannot be found!");
		}
	
		
	}
}
