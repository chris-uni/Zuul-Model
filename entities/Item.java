/** The definition of an in-game item. Items have a name, description and weight.
 * 
 * @author Chris Wing (cjmw2)
 * */

package entities;

public class Item{

	private String name;
	private String description;
	private int weight;
	
	public Item(String name, String description, int weight) {
		
		this.name = name;
		this.description = description;
		this.weight = weight;
	}

	/** Returns the name of this item.
	 * */
	public String getName() {
		return name;
	}

	/** Returns the description of this item.
	 * */
	public String getDescription() {
		return description;
	}

	/** Returns the weight of this item.
	 * */
	public int getWeight() {
		return weight;
	}
}
