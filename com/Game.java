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

import commands.CommandHandler;
import entities.Item;
import entities.Room;
import loader.JSONLoader;
import output.Mode;
import output.OutputHandler;
import parser.Parser;
import player.Player;

public class Game {
	
	private Player player;
	private State gameState;
	private Parser parser = new Parser();
	private CommandHandler commandHandler;

	private Room currentRoom;
	private HashMap<String, Room> allRooms = new HashMap<String, Room>();
	
	// The games data file. Contains information about rooms, exits and items.
	private JSONLoader loader = new JSONLoader("res/roomData.json");
	
	public Game() {
		
		this.player = new Player();
		
		commandHandler = new CommandHandler(this);
		
		this.gameState = State.PLAY;
		
		this.loadRooms();
	}
	
	/** Runs the game and checks for State updates.
	 * */
	public void play() {
		
		this.welcomeMessage();
		
		while(gameState == State.PLAY) {
		
			// Else run the game.
			commandHandler.handleCommand(parser);
		}
	}
	
	/** Loads the in-game rooms. Uses the List<String> object returned from the JSONLoader class to know what rooms exist in the game world.
	 * */
	private void loadRooms() {
		
		List<String> roomNames = loader.getAllRooms();
		
		for(int i = 0; i < roomNames.size(); i++) {
			
			// Get the room  name and description from the JSON file.
			String roomName = roomNames.get(i);
			String roomDesc = loader.getRoomDescription(roomName);
			
			HashMap<String, String> exits = loader.getRoomExits(roomName);
			
			// For the current room in the JSON file, the below loads the items it has.
			List<String> itemKeys = loader.getItemKeys(roomName);
			HashMap<String, Item> items = new HashMap<String, Item>();
			
			for(String itemName : itemKeys) {
				
				String[] itemInfo = loader.getRoomItems(roomName, itemName);
				String description = itemInfo[0];
				int weight = Integer.parseInt(itemInfo[1].toString());
				
				items.put(itemName, new Item(itemName, description, weight));
			}
			
			Room room = new Room(roomName, roomDesc, exits, items);
			allRooms.put(roomName, room);
		}
		
		// Setting the initial room of the game.
		this.currentRoom = allRooms.get("Courtyard");
		
	}
	
	/** Prints the welcome message to the screen for the player to see.
	 * */
	private void welcomeMessage() {
		
		OutputHandler.output("Welcome traveller, to the World of Zuul!\nYou are in: " + this.currentRoom.getName() + "\nFor hints you can type  'help'.", Mode.CONSOLE);
	}
	
	/** Returns the games current State, i.e. PAUSE, PLAY, QUIT etc.
	 * */
	public State getGameState() {
		
		return this.gameState;
	}
	
	/** Updates the games State, used to transition from PLAY -> QUIT, PLAY -> PAUSE etc.
	 * */
	public void updateGameState(State state) {
		
		this.gameState = state;
	}
	
	/** Returns the player object.
	 * */
	public Player getPlayer() {
		
		return this.player;
	}
	
	/** Returns the current Room object. I.e. the current room the player is in.
	 * */
	public Room getCurrentRoom() {
		
		return this.currentRoom;
	}
	
	/** Updates the current Room object. Allows the player to move from one room to another by taking exits.
	 * */
	public void updatCurrentRoom(Room room) {
		
		this.currentRoom = room;
	}
	
	/** Returns the entire list of Rooms available in the game.
	 * */
	public HashMap<String, Room> getAllRooms(){
		
		return this.allRooms;
	}
}
