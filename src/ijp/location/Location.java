package ijp.location;

import java.util.HashMap;
//import java.util.Set;
import ijp.item.*;
import ijp.direction.*;
import ijp.inventory.*;

/**
 * 
 * Class that represents a location. It contains information about the location:
 * The pictures corresponding to the scene at each direction. The directions
 * where there are exits to different locations as well as where each exit
 * leads. The items that the location contains stored in the inventory class.
 * 
 * @author Nick Antonopoulos
 * @version 1.0
 */
public class Location {

	private String Name;
	private HashMap<Direction, String> Scenes;
	private HashMap<Direction, String> Exits;
	private Inventory inventory;

	public Location(String Name, HashMap<Direction, String> Scenes, HashMap<Direction, String> Exits,
			Inventory inventory) {
		this.Name = Name;
		this.Scenes = Scenes;
		this.Exits = Exits;
		this.inventory = inventory;
	}
	
	/**
	 * Alternative constructor using string that contain information about the location and its contents
	 * images are assumed to follow the naming scheme: "locationName-D.jpg", where D represents the direction with one letter: N,S,E,W
	 * @param locName name of location
	 * @param Exits   string containing directions and destinations of the exits of the location: "dirExit1: nameDest1, dirExit2: ..."
	 * @param items   string containing items in the location: "nameItem1: pictureNameOfItem1, nameItem2: ..."
	 */
	
	public Location(String locName, String Exits, String items) {
		
		// Handle The Exits Creation
		HashMap<Direction, String> exitMap = new HashMap<Direction, String>();
		// Split the string containing all the exits for a location
		String[] exits = Exits.split(",");
		// iterate over the split list
		for (String exit : exits) {
			// Split the String into two, a direction and the name of the location where the
			// exit leads
			String[] DirDestPair = exit.split(":");
			Direction direction = Direction.getDirection(DirDestPair[0]);
			String destination = DirDestPair[1];
			// put direction destination pair to exitmap
			exitMap.put(direction, destination);
		}

		// Handle The Scene Creation
		// using the naming scheme: "locationName-D.jpg", where D represents the direction with one letter: N,S,E,W
		// for pictures
		HashMap<Direction, String> Scenes = new HashMap<Direction, String>();
		Scenes.put(Direction.North, locName + "-N");
		Scenes.put(Direction.South, locName + "-S");
		Scenes.put(Direction.East, locName + "-E");
		Scenes.put(Direction.West, locName + "-W");

		// Handle inventory
		Inventory inventory = new Inventory();
		
		if (!items.isEmpty()) {
			// Split the string containing the items to get information about one item in each entry of the array itemsInfo
			String[] itemsInfo = items.split(",");
			for (String itemInfo : itemsInfo) {
				//split each entry into the name of the item and the name of the picture representing the item.
				String[] NamePicturePair = itemInfo.split(":");
				Item item = new Item(NamePicturePair[0].trim(), NamePicturePair[1].trim());
				inventory.addItem(item);
			}
		}
		
		this.Name = locName;
		this.Scenes = Scenes;
		this.Exits = exitMap;
		this.inventory = inventory;
		
	}

	/**
	 * Accessor method for the string representing the name of the location.
	 * 
	 * @return string the name of the location
	 */
	public String getName(Location location) {
		return this.Name;
	}

	/**
	 * Adds an item to the inventory of the location.
	 * 
	 * @param item to be added
	 */
	public void addItem(Item item) {
		this.inventory.addItem(item);
	}

	/**
	 * Removes an item to the inventory of the location.
	 * 
	 * @param item to be removed
	 */
	public void removeItem(Item item) {
		this.inventory.removeItem(item);
	}

	/**
	 * Methods that returns True when the given direction points to an exit.
	 * 
	 * @param direction to check whether it points to an exit
	 * @return boolean variable representing if there is an exit at the given
	 *         location
	 */
	public Boolean isExit(Direction direction) {
		return this.Exits.containsKey(direction);
	}

	/**
	 * Returns the name of the location where the exit at the given direction leads
	 * 
	 * @param direction of exit
	 * @return the name of the exit location.
	 */
	public String getExit(Direction direction) {
		if (isExit(direction)) {
			return this.Exits.get(direction);
		} else {
			throw new IllegalArgumentException("No exit in direction: " + direction.getName(direction));
		}
	}

	/**
	 * Accessor method for the string representing the picture of the location at a
	 * specific direction.
	 * 
	 * @return string the name of the picture
	 */
	public String getScene(Direction direction) {
		if (this.Scenes.containsKey(direction)) {
			return this.Scenes.get(direction);
		} else {
			throw new IllegalArgumentException("no scene for given Direction");
		}
	}

	/**
	 * Accessor method for the container of objects of this class.
	 * 
	 * @return inventory of this location
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
}
