package entities;

import java.util.HashMap;
import java.util.Map;

public class Room {

	private String name;
	private String description;
	private HashMap<String, String> exits;
	
	public Room(String name, String description, HashMap<String, String> exits) {
		
		this.name = name;
		this.description = description;
		this.exits = exits;
	}
	
	/** Adds all possible exists to a particular room.
	 * */
	public void addExits(HashMap<String, String> exits) {
		
		this.exits = exits;
	}
	
	/** Returns all the possible exits of a particular room.
	 **/
	public HashMap<String, String> getExits(){
		
		return this.exits;
	}
	
	/** Will iterate over the rooms list of exits, pulling only the exit Values (i.e. the new rooms the exits lead too). This is formed into a string and returned. Used when inspecting a room.
	 * */
	public String getExitAsString() {
		
		String exits = "";
		
		for(Map.Entry<String, String> e : this.exits.entrySet()) {
			
			String exit = e.getValue();
			String direction = e.getKey();
			
			exits += direction + " : " + exit + "\n";
		}
		
		return exits.trim();
	}
	
	/** Returns the room at exits 'direction'.
	 **/
	public String getExit(String direction) {
		
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
