package entities;

import player.Inventory;

public class NPC {

	private String name;
	private String dialog;
	private Inventory inventory;
	
	public NPC(String name, String dialog) {
		
		this.name = name;
		this.dialog = dialog;
		inventory = new Inventory();
	}
	
	/** Gets the NPC's name.
	 * */
	public String getName() {
		
		return this.name;
	}
	
	/** Returns the Dialog associated with this NPC.*/
	public String getDialog() {
		
		return this.dialog;
	}
	
	/** Returns the Inventory of the NPC.
	 * */
	public Inventory getInventory() {
		
		return this.inventory;
	}
}
