/**
 * This is the main Game class for zuul-model.
 * 
 * Contains game logic, calls to other classes that handle room navigation, parsing, item interaction and more.
 * 
 * @author Chris Wing
 */
package com;

import java.util.HashMap;
import java.util.List;

import entities.Room;
import loader.JSONLoader;
import output.Mode;
import output.OutputHandler;
import parser.Parser;

public class Game {
	
	private State gameState;
	private Parser parser = new Parser();

	private Room currentRoom;
	private HashMap<String, Room> allRooms = new HashMap<String, Room>();
	
	private JSONLoader loader = new JSONLoader("res/roomData.json");
	
	public Game() {
		
		this.gameState = State.PLAY;
		
		this.loadRooms();
		
		// OutputHandler.output(loader.getRoomDescription("Courtyard"), Mode.CONSOLE);
	}
	
	public void play() {
		
		this.welcomeMessage();
		
		while(gameState == State.PLAY) {
		
			// Else run the game.
			this.processInput();
		}
	}
	
	/** Loads the in-game rooms. Uses the List<String> object returned from the JSONLoader class to know what rooms exist in the game world.
	 * */
	private void loadRooms() {
		
		List<String> roomNames = loader.getAllRooms();
		
		for(int i = 0; i < roomNames.size(); i++) {
			
			// OutputHandler.output(roomNames.get(i), Mode.CONSOLE);
			
			String roomName = roomNames.get(i);
			String roomDesc = loader.getRoomDescription(roomName);
			
			Room room = new Room(roomName, roomDesc);
			allRooms.put(roomName, room);
		}
		
		// Setting the initial room of the game.
		this.currentRoom = allRooms.get("Courtyard");
		
	}
	
	/** Prints the welcome message to the screen for the player to see.
	 * */
	private void welcomeMessage() {
		
		OutputHandler.output("Welcome traveller, to the World of Zuul!\nYou are in: " + this.currentRoom.getName(), Mode.CONSOLE);
	}
	
	/**
	 * Processes the users input. Will execute different commands based on what the user says.
	 * */
	private void processInput() {
		
		String[] userInput = parser.getUserInput();
		
		int inputLength = userInput.length;
			
		if(userInput[0].equals("look")) {
			
			OutputHandler.output(this.currentRoom.getDescription(), Mode.CONSOLE);
		}
		else if(userInput[0].equals("quit")) {
			
			OutputHandler.output("Cya!", Mode.CONSOLE);
			this.gameState = State.QUIT;
		}
		
		else if(userInput[0].equals("pause")) {
			
			OutputHandler.output("Game paused. Type resume to continue.", Mode.CONSOLE);
			this.gameState = State.PAUSE;
		}
		else {
			
			// Fallback, we don't understand the users command.
			OutputHandler.output("I'm sorry, I dont know what you mean by: '" + userInput[0] + "'", Mode.CONSOLE);
		}
	}
}
