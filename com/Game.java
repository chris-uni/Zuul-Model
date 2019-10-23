/**
 * This is the main Game class for zuul-model.
 * 
 * Contains game logic, calls to other classes that handle room navigation, parsing, item interaction and more.
 * 
 * @author Chris Wing (cjmw2)
 */
package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ai.AIController;
import commands.CommandHandler;
import commands.ICommand;
import entities.Item;
import entities.NPC;
import entities.Room;
import loader.JSONLoader;
import output.Mode;
import output.OutputHandler;
import parser.Parser;
import player.Player;
import tools.CommandList;

public class Game {
	
	private State gameState;
	private Parser parser = new Parser();
	
	private CommandHandler commandHandler;
	private HashMap<String, ICommand> commands;

	//private Room currentRoom;
	private HashMap<String, Room> allRooms = new HashMap<String, Room>();
	
	// The games data file. Contains information about rooms, exits and items.
	private JSONLoader loader = new JSONLoader("res/roomData.json");
	
	// This holds the list of players currently playing the game.
	private List<Player> players = new ArrayList<Player>();
	
	// The reference of the player object whose go it is currently.
	private Player currentPlayer;
	
	private AIController ai;
	
	public Game() {
			
		this.commands = CommandList.getCommands("src/commands/valid");
		
		commandHandler = new CommandHandler(this);
		
		ai = new AIController(this);
		
		this.gameState = State.PLAY;
		
		// First things first, how many players in the game?
		this.setPlayers();
		
		// We know we must have at least one player in the game for it to start, therefore set the current player to be the player at index 0.
		this.currentPlayer = players.get(0);
		
		// Now we can load the rooms.
		this.loadRooms();
	}
	
	/** Runs the game and checks for State updates.
	 * */
	public void play() {
				
		this.welcomeMessage();

		// Main game loop.
		while(gameState == State.PLAY) {
			
			if(gameState == State.PAUSE) {
				
				// We will handle this later...
			}
			
			else {
				
				// For every player we have in the game, this lets them take in turns.
				for(int i = 0; i < players.size(); i++) {
					
					// AI of the NPCs. Every 10-20 seconds a random NPc will move room. Can be extended.
					this.ai.run(10000);
					OutputHandler.output("Current thread: " + Thread.currentThread().getId(), Mode.CONSOLE);
					
					// Sets who the current player is.
					this.currentPlayer = players.get(i);
					
					OutputHandler.output("Player " + (i + 1) + "'s turn: \n", Mode.CONSOLE);
					commandHandler.handleCommand(parser);
				}
			}
		}
		
		// Closes the scanner and releases the resources attached to it.
		parser.cleanup();
	}
	
	/** The very first thing to run, sets the number of players in the game.
	 * */
	private void setPlayers() {
		
		// New console scanner.
		Scanner scanner = new Scanner(System.in);
		
		OutputHandler.output("How many players do you want playing? ", Mode.CONSOLE);
		
		try {
			
			// Tries to convert the users input into a number.
			int number = Integer.parseInt(scanner.nextLine());
			
			// Max of 10 players.
			if(number <= 10) {
				
				// For X, create X number of players.
				for(int i = 0; i < number; i++) {
					
					// Add players to list.
					this.players.add(new Player());
				}	
			}
			else {
				
				OutputHandler.output("Maximum of 10 players allowed!", Mode.CONSOLE);
				this.setPlayers();
			}
		}
		catch(NumberFormatException e) {
			
			// If the number entered is not a valid number, output error message and try again!
			OutputHandler.output("Your must enter in a number!", Mode.CONSOLE);
			this.setPlayers();
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
			HashMap<String, Item> items = this.loadItems(roomName);
			HashMap<String, NPC> npcs = this.loadNPCs(roomName);
			
			Room room = new Room(roomName, roomDesc, exits, items, npcs);
			allRooms.put(roomName, room);
		}
		
		Random rand = new Random();
		int rnd = rand.nextInt(allRooms.size() - 1);
		
		// Setting the initial room for all the players in the room.
		for(int i = 0; i < players.size(); i++) {
		
			Room room  = (Room)allRooms.values().toArray()[rnd];
			
			players.get(i).updateRoom(room);
		}
	}
	
	/** A private method to load the items for any given room. Makes the loadRooms() method above a little cleaner.
	 * */
	private HashMap<String, Item> loadItems(String roomName){
		
		// For the current room in the JSON file, the below loads the items it has.
		List<String> itemKeys = loader.getItemKeys(roomName);
		
		var itemWrapper = new Object() { HashMap<String, Item> items = new HashMap<String, Item>(); };
		
		itemKeys.forEach((k) -> {
			
			String itemInfo[] = loader.getRoomItems(roomName, k);
			
			String desc = itemInfo[0];
			int weight = Integer.parseInt(itemInfo[1].toString());
			
			itemWrapper.items.put(k, new Item(k, desc, weight));
			
		});
		
		return itemWrapper.items;
	}
	
	/** A private method to load NPCs into the game. Makes the loadRooms() method a little cleaner.
	 * */
	private HashMap<String, NPC> loadNPCs(String roomName){
		
		List<String> npcKeys = loader.getNPCKeys(roomName);
		
		var npcWrapper = new Object() { HashMap<String, NPC> npcs = new HashMap<String, NPC>(); };
		
		npcKeys.forEach((k) -> {
			
			String npcInfo[] = loader.getRoomNPCs(roomName, k);
			String dialog = npcInfo[0];
			
			npcWrapper.npcs.put(k, new NPC(k, dialog));
		});
		
		return npcWrapper.npcs;
	}
	
	/** Prints the welcome message to the screen for the player to see.
	 * */
	private void welcomeMessage() {
		
		String plural = "traveller";
		
		if(players.size() > 1) {
			
			plural = "travellers";
		}
			
		OutputHandler.output("Welcome " + plural + ", to the World of Zuul! For hints you can type 'help'.\nYou are currently in: " + this.currentPlayer.getCurrentRoom().getName(), Mode.CONSOLE);
	}
	
	/** Returns who the current player is.
	 * */
	public Player getCurrentPlayer() {
		
		return this.currentPlayer;
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
	
	/** Returns the room object specified by the room name paramater.
	 * */
	public Room getRoom(String roomName) {
		
		return this.allRooms.get(roomName);
	}
	
	/** Returns a random room within the game. Used within NPC ai.
	 * */
	public Room getRandomRoom() {
		
		int count = this.allRooms.size() - 1;
		
		Random random = new Random();
		
		return (Room) this.allRooms.values().toArray()[random.nextInt(count)];
	}
	
	/** Returns an ICommand object back to the caller.
	 * */
	public ICommand getCommand(String commandName){
		
		return this.commands.get(commandName);
	}
	
	/** Returns the HashMap of commands available within the game.
	 * */
	public HashMap<String, ICommand> getCommands(){
		
		return (HashMap<String, ICommand>) this.commands.clone();
	}
}
