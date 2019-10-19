/** This class handles the text output of the game. Instead of using 'System.out.println()' everywhwere, this class allows text output to be handled in a better way. Simple by specifying a mode, we can determine how
 *  to handle the output and where to put it. Can be extended later to allow output to GUI interfaces.
 * 
 * @author Chris Wing (cjmw2)
 * */

package output;

import output.Mode;

public class OutputHandler {
	
	/**
	 * Allows output to be handled in different ways.*/
	public static void output(String string, Mode mode) {
		
		switch(mode) {
		
			case CONSOLE:
				System.out.println(string);
				break;
			case CONSOLE_S:
				System.out.print(string);
				break;
			case F:
				System.out.printf(string);
				break;
		}
	}
}
