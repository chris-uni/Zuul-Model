/** Used to iterate through the 'commands.valid' folder and work out what list of commands are available to the player. Will also be used to iterate through the 'plugin.commands' folder to see what external plugins are
 * available.
 * 
 * @author Chris Wing (cjmw2)
 * */

package tools;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import commands.ICommand;

public class CommandList {

	/** This will scan the 'commands.valid' package, get the names of all the commands within the game and then collate them into a List<String>. Used for the 'Help' command to display all command without
	 * the need for a hard-coded array.
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * */
	public static HashMap<String, ICommand> getCommands(String filename){
		
		File dir = new File(filename);
		File[] listings = dir.listFiles();
		
		HashMap<String, ICommand> commands = new HashMap<String, ICommand>();
		
		if(listings != null) {
			
			for(File command : listings) {
				
				// Removes the '.java' from the end of the class name.
				String commandName = command.getName().split("[.]")[0];
				
				try {
					
					// Now we can try to create a new instance of the Command class 'comamndName'.
					ICommand commandObj = (ICommand)Class.forName("commands.valid." + commandName).getDeclaredConstructor().newInstance();
					
					// If successful, we add it to the list of available commands.
					commands.put(commandName, commandObj);
					
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
					
					e.printStackTrace();
				}
			}
			
			// Return the list of commands to the main Game class.
			return commands;
		}
		else {
			
			return null;
		}
	}
	
	/** This will scan the 'commands.ai' package, get the names of all the commands within the game and then collate them into a List<String>. 
	 * Used to enable AI's to have extendible commands at their disposal.
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * */
	public static List<String> getAiCommands(String filename){
		
		File dir = new File(filename);
		File[] listings = dir.listFiles();
		
		List<String> aiCommands = new ArrayList<String>();
		
		if(listings != null) {
			
			for(File command : listings) {
				
				// Removes the '.java' from the end of the class name.
				String commandName = command.getName().split("[.]")[0];
					
				aiCommands.add(commandName);
			}
			
			// Return the list of commands to the main Game class.
			return aiCommands;
		}
		else {
			
			return null;
		}
	}
}
