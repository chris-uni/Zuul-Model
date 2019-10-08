package parser;

import java.util.Scanner;

import output.Mode;
import output.OutputHandler;

public class Parser {

	private Scanner scanner;
	
	public Parser() {
		
		this.scanner = new Scanner(System.in);
	}
	
	/*
	 * Uses the scanner object to get the users input from the command line.
	 * */
	public String[] getUserInput() {
		
		OutputHandler.output("> ", Mode.CONSOLE_S);
		return this.scanner.nextLine().toLowerCase().split(" ");
	}
}
