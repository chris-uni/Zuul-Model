/** The definition of a Room within the game. Rooms contain exits, and can also contain (but not always) NPC's and Items.
 * 
 * @author Chris Wing (cjmw2)
 * */

package entities;

import java.util.HashMap;

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
		
		var wrapper = new Object() { String exits = ""; };
		
		this.exits.forEach((k, v) -> {
			
			wrapper.exits += v + " : " + k + "\n";
		});
	
		return wrapper.exits.trim();
	}
	
	/** Will iterate over the list of items in this room, grab the items name, desc and weight, format the items information into a string and then return the total string. Used within the 'Look' command.
	 * */
	public String getItemsAsString() {
		
		var wrapper = new Object() {String items = ""; };
		
		if(this.items.size() == 0) {
			
			return "no items!";
		}
		else {
			
			this.items.forEach((k, v) -> {
				
				wrapper.items += v.getName() + "(" + v.getWeight() + ")" + ", " + v.getDescription() + "\n";
			});
			
			return wrapper.items.trim();
		}

	}
	
	/** Formats a string containing the NPC names in the current room. If no NPCs, an empty string will return.
	 * */
	public String getNPCsAsString() {
		
		var wrapper = new Object() {String list = ""; };
		
		if(this.npcs.size() == 0) {
			
			// Just print blank, i.e. no one is here.
			return "";
		}
		else {
			
			this.npcs.forEach((k, v) -> {
				
				wrapper.list += k + "\n";
			});
		}
		
		return wrapper.list.trim();
	}
	
	/** Removes the specified item from the items HashMap. I.e. removes the item from the room.
	 * */
	public void removeItem(String itemName) {
		
		this.items.remove(itemName);
	}
	
	/** Used when a player is dropping items. The player will drop (remove it from inventory) and add it to their current room.
	 * */
	public void addItem(Item item) {
		
		this.items.put(item.getName(), item);
	}
	
	/** Returns an item object whose key corresponds to the key specified, if it exists. Otherwise returns null.
	 * */
	public Item getItem(String itemName) {
		
		return this.items.get(itemName);
	}
	
	/** Returns the room at exits 'direction'. If no exits are in direction 'direction', null will be returned.
	 **/
	public String getExit(String direction) {
		
		return this.exits.get(direction);
	}
	
	/** Used to check whether or not the room has a certain NPC in it or not. True -> yes.
	 * */
	public boolean hasNPC(String npcName) {
		
		return this.npcs.containsKey(npcName);
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
