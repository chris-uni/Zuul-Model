/** The player class. This contains the definition on what a player within the game is. Contains information on their inventory and current room.
 * 
 * @author Chris Wing (cjmw2)
 * */

package player;

import entities.Item;
import entities.Room;

public class Player {

	private Inventory inventory;
	
	private Room currentRoom;

	public Player() {
		
		this.inventory = new Inventory();
	}
	
	/** Used to update the players current room. I.e. set a new room as the current room when they use the 'go' command.
	 * */
	public void updateRoom(Room room) {
		
		this.currentRoom = room;
	}
	
	/** Used to get the players current room.
	 * */
	public Room getCurrentRoom() {
		
		return this.currentRoom;
	}
	
	/** Allows a player to add an item to their inventory.
	 * */
	public void addItem(Item item) {
		
		this.inventory.addItem(item, this.currentRoom);
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
