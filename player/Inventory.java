package player;

import java.util.HashMap;
import java.util.Map;

import entities.Item;
import output.Mode;
import output.OutputHandler;

public class Inventory {

	private final int MAX_WEIGHT = 10;
	
	private HashMap<String, Item> items = new HashMap<String, Item>();
	
	public Inventory() {
		
		
	}

	/** Gets the players inventory max weight value.
	 * */
	public int getMAX_WEIGHT() {
		return MAX_WEIGHT;
	}

	/** Gets the list of items within the players inventory.
	 * */
	public HashMap<String, Item> getItems() {
		return items;
	}
	
	/** Used to add a new item to the players inventory. Will first check that the weight of the new item + the current total weight of the inventory is not larger than MAX_WEIGT.
	 * */
	public void addItem(Item item) {
		
		int itemWeight = item.getWeight();
		
		int newTotal = itemWeight + this.getCurrentTotalWeight();
		
		if(newTotal < this.MAX_WEIGHT) {
			
			this.items.put(item.getName(), item);
		}
		else {
			
			OutputHandler.output("Error, that item is too heavy for you. Please loose some weight!", Mode.CONSOLE);
		}
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
			
			return inventory.trim();
		}

	}
	
	/** Calculates the current total weight of the players inventory.
	 * */
	private int getCurrentTotalWeight() {
		
		int weight = 0;
		
		for(Map.Entry<String, Item> e : this.items.entrySet()) {
			
			weight += e.getValue().getWeight();
		}
		
		return weight;
	}
}
