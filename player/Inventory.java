/** The inventory class holds information about what items and what total weight any player/ NPC may have/is/. Contains methods to add/remove items whilst at the same time updating the inventories current weight.
 * 
 * @author Chris Wing (cjmw2)
 * */

package player;

import java.util.HashMap;

import entities.Item;
import entities.Room;
import output.OutputHandler;

public class Inventory{

	private final int MAX_WEIGHT = 10;
	
	private HashMap<String, Item> items = new HashMap<String, Item>();
	
	private int currentWeight;
	
	public Inventory() {
		
		this.currentWeight = 0;
	}

	/** Gets the players inventory max weight value.
	 * */
	public int getMAX_WEIGHT() {
		return MAX_WEIGHT;
	}
	
	/** Used to add a new item to the players inventory. Will first check that the weight of the new item + the current total weight of the inventory is not larger than MAX_WEIGT.
	 * */
	public void addItem(Item item, Room room) {
		
		int itemWeight = item.getWeight();
		
		int newTotal = itemWeight + this.currentWeight;
		
		OutputHandler.output("Item weight: " + itemWeight + ", New total: " + Integer.toString(newTotal));
		if(newTotal < this.MAX_WEIGHT) {
			
			this.items.put(item.getName(), item);
			this.currentWeight += item.getWeight();
			
			// If we are successful in taking the object, remove it from the current room.
			room.removeItem(item.getName());
			
			OutputHandler.output("Successfully taken the " + item.getName() + "!");
		}
		else {
			
			OutputHandler.output("Error, that item is too heavy for you. Please loose some weight!");
		}
	}
	
	/** Iterates over all items in the players inventory, will format this as a string and return it.
	 * */
	public String printInventory() {
		
		var wrapper = new Object() {String inventory = ""; };
		
		if(items.size() == 0) {
			
			return "Looks like you dont have anything in your inventory yet!";
		}
		else {
		
			this.items.forEach((k, v) -> {
				
				wrapper.inventory += v.getName() + "(" + v.getWeight() + ")" + "\n";
			});

			wrapper.inventory += "Your total weight is: " + this.currentWeight;
			
			return wrapper.inventory.trim();
		}

	}

	/** Checks to see if there is 'itemName' within the HashMap (inventory). If true, return true. Else return false.
	 * */
	public boolean hasItem(String itemName) {
		
		return this.items.containsKey(itemName);
	}
	
	/** Returns a specific item from the items HashMap.
	 * */
	public Item getItem(String itemName) {
		
		return this.items.get(itemName);
	}
	
	/** Used to drop an item, that is, remove it from the players inventory and add it to the player current room.
	 * */
	public void removeItem(Item item) {
		
		this.currentWeight -= item.getWeight();
		this.items.remove(item.getName());
	}
}
