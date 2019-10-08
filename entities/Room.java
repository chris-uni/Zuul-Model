package entities;

import java.util.HashMap;

public class Room {

	private String name;
	private String description;
	private HashMap<String, Room> exits;
	
	public Room(String name, String description) {
		
		this.name = name;
		this.description = description;
	}
	
	/** Adds all possible exists to a particular room.
	 * */
	public void addExits(HashMap<String, Room> exits) {
		
		this.exits = exits;
	}
	
	/** Returns all the possible exits of a particular room.
	 **/
	public HashMap<String, Room> getExits(){
		
		return this.exits;
	}
	
	/** Returns the room at exits 'direction'.
	 **/
	public Room getExit(String direction) {
		
		return this.exits.get(direction);
	}
	
	/**
	 * Returns the name of the room.
	 * */
	public String getName() { 
		
		return this.name; 
	}
	
	/**
	 * Returns the description of the room.
	 * */
	public String getDescription() { 
		
		return this.description; 
	}
}
