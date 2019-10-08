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
