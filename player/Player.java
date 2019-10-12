package player;

import entities.Item;

public class Player {

	private Inventory inventory = new Inventory();

	public Player() {
		
		
	}
	
	/** Allows a player to add an item to their inventory.
	 * */
	public void addItem(Item item) {
		
		this.inventory.addItem(item);
	}
	
	/** Returns the players inventory.
	 * */
	public Inventory getInventory() {
		
		return this.inventory;
	}
}
