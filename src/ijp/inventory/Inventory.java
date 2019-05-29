package ijp.inventory;
import java.util.HashSet;
import java.util.Iterator;


import ijp.item.*;
/**
 * 
 * Class that represents an inventory, a collection of items used by both the location and user class.
 * It implements the iterable interface.
 * 
 * @author Nick Antonopoulos
 * @version 1.0
 */
public class Inventory implements Iterable<Item>{
	private HashSet<Item> contents;
	//Store the items in the hashSet collection.
	public Inventory() {
		this.contents = new HashSet<Item>();
	}
	/**
	 * Adds an item to the inventory
	 * 
	 * @param item to be added to the collection
	 */
	public void addItem(Item item) {
		this.contents.add(item);
	}
	

	/**
	 * Removes an item from the inventory
	 * 
	 * @param item to be removed from the collection
	 */
	public void removeItem(Item item) {
		this.contents.remove(item);
	}
	/**
	 * Iterator needed to implement the iterable interface.
	 */
	@Override
	public Iterator<Item> iterator() {
		// returns the iterator of the container.
		return this.contents.iterator();
	}
	
	
}
