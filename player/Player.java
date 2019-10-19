/** The player class. This contains the definition on what a player within the game is. Contains information on their inventory and current room.
 * 
 * @author Chris Wing (cjmw2)
 * */

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
	
	public void removeItem(Item item) {
		
		this.inventory.removeItem(item);
	}
	
	/** Returns the players inventory.
	 * */
	public Inventory getInventory() {
		
		return this.inventory;
	}
}
