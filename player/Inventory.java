/** The inventory class holds information about what items and what total weight any player/ NPC may have/is/. Contains methods to add/remove items whilst at the same time updating the inventories current weight.
 * 
 * @author Chris Wing (cjmw2)
 * */

package player;

import java.util.HashMap;
import java.util.Map;

import entities.Item;
import output.Mode;
import output.OutputHandler;

public class Inventory{

	private final int MAX_WEIGHT = 10;
	
	private HashMap<String, Item> items = new HashMap<String, Item>();
	
	private int currentWeight = 0;
	
	public Inventory() {
		
		
	}

	/** Gets the players inventory max weight value.
	 * */
	public int getMAX_WEIGHT() {
		return MAX_WEIGHT;
	}
	
	/** Used to add a new item to the players inventory. Will first check that the weight of the new item + the current total weight of the inventory is not larger than MAX_WEIGT.
	 * */
	public void addItem(Item item) {
		
		int itemWeight = item.getWeight();
		
		int newTotal = itemWeight + this.currentWeight;
		
		if(newTotal < this.MAX_WEIGHT) {
			
			this.items.put(item.getName(), item);
			this.currentWeight += item.getWeight();
		}
		else {
			
			OutputHandler.output("Error, that item is too heavy for you. Please loose some weight!", Mode.CONSOLE);
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
	
	/** Iterates over all items in the players inventory, will format this as a string and return it.
	 * */
	public String printInventory() {
		
		String inventory = "Your inventory: \n";
		
		if(items.size() == 0) {
			
			return "Looks like you dont have anything in your inventory yet!";
		}
		else {
		
			for(Map.Entry<String, Item> e : this.items.entrySet()) {
				
				Item item = e.getValue();
				
				String name = item.getName();
				int weight = item.getWeight();
				
				inventory += name + "(" + weight + ")" + "\n";
			}
			
			inventory += "Your total weight is: " + this.currentWeight;
			
			return inventory.trim();
		}

	}

	/** Used to drop an item, that is, remove it from the players inventory and add it to the player current room.
	 * */
	public void removeItem(Item item) {
		
		this.currentWeight -= item.getWeight();
		this.items.remove(item.getName());
	}
}
