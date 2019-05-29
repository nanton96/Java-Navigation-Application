package ijp.item;
/**
 * 
 * Class that represents an item.
 * It contains information about the item, namely its name and the picture that represents it.
 * 
 * @author Nick Antonopoulos
 * @version 1.0
 */
public class Item {

	private String Name;
	private String ItemImage;
	
	public Item(String Name, String ItemImage) {
		this.Name = Name;
		this.ItemImage = ItemImage;
	}
	/**
	 * Accessor method for the string representing the picture of the item.
	 * @return string the name of the picture of the item
	 */
	public String getImageName() {
		return this.ItemImage;
	}
	/**
	 * Accessor method for the string representing the name of the item.
	 * @return string the name of the item
	 */
	public String getName() {
		return this.Name;
	}
}
