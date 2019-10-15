package tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandList {

	/** This will scan the 'commands.valid' package, get the names of all the commands within the game and then collate them into a List<String>. Used for the 'Help' command to display all command without
	 * the need for a hard-coded array.
	 * */
	public static List<String> getCommands(String filename){
		
		File dir = new File(filename);
		File[] listings = dir.listFiles();
		
		List<String> commandWords = new ArrayList<String>();
		if(listings != null) {
			
			for(File command : listings) {
				
				// Removes the '.java' from the end of the class name.
				String commandName = command.getName().split("[.]")[0];
				
				// Makes the first letter lower-case.
				commandWords.add(Tools.firstLetterToLower(commandName));
			}
			
			return commandWords;
		}
		else {
			
			return Arrays.asList("Looks like there are no commands!");
		}
	}
}
