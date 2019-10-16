package entities;

import java.util.HashMap;
import java.util.Map;

public class Room {

	private String name;
	private String description;
	private HashMap<String, String> exits;
	private HashMap<String, Item> items;
	private HashMap<String, NPC> npcs;
	
	public Room(String name, String description, HashMap<String, String> exits, HashMap<String, Item> items, HashMap<String, NPC> npcs) {
		
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.items = items;
		this.npcs = npcs;
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
	
	/** Will iterate over the list of items in this room, grab the items name, desc and weight, format the items information into a string and then return the total string. Used within the 'Look' command.
	 * */
	public String getItemsAsString() {
		
		String items = "";
		
		if(this.items.size() == 0) {
			
			return "no items!";
		}
		else {
			
			for(Map.Entry<String, Item> e : this.items.entrySet()) {
				
				Item item = e.getValue();
				
				String name = item.getName();
				String desc = item.getDescription();
				int weight = item.getWeight();
				
				items += name + "(" + weight + ")" + ", " + desc + "\n";
			}
			
			return items.trim();
		}

	}
	
	/** Formats a string containing the NPC names in the current room. If no NPCs, an empty string will return.
	 * */
	public String getNPCsAsString() {
		
		String npcs = "NPCs: ";
		
		if(this.npcs.size() == 0) {
			
			// Just print blank, i.e. no one is here.
			return "";
		}
		else {
			
			for(Map.Entry<String, NPC> e : this.npcs.entrySet()) {
				
				String name = e.getKey();
				
				npcs += "\n" + name;
			}
		}
		
		return npcs;
	}
	
	/** Returns the list of items in this room.
	 * */
	public HashMap<String, Item> getItems(){
		
		return this.items;
	}
	
	/** Used when a player is dropping items. The player will drop (remove it from inventory) and add it to their current room.
	 * */
	public void addItem(Item item) {
		
		this.items.put(item.getName(), item);
	}
	
	/** Returns the room at exits 'direction'.
	 **/
	public String getExit(String direction) {
		
		return this.exits.get(direction);
	}
	
	/** Returns either the NPC with the given key, OR null. (null if not found, i.e. NPC is not in the room).
	 * */
	public NPC getNPC(String npcName) {
		
		return this.npcs.get(npcName);
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
