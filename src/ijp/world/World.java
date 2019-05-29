package ijp.world;
import java.util.HashMap;

import ijp.direction.*;
import ijp.location.*;

/**
 * 
 * Class that represents the world.
 * It contains all the locations, stored in a hashmap.
 * It also creates the location objects.
 * Images for the images are taken from https://www.iconfinder.com/iconsets/Easter_Icon_Set
 * @author Nick Antonopoulos
 * @version 1.0
 */
public class World {
	
	private HashMap<String,Location> locations;
	
	public World() {
		
		locations = new HashMap<String,Location>();
		//pictures will follow the following name scheme: "locationName-D.jpg", where D represents the direction with one letter: N,S,E,W
		//arrays containing information about a specific location.
		//locations[i] will have exits corresponding to exits[i] and items[i]
		
		String[] locations = new String[] {"room","corridorA","corridorB","kitchen","hall"};
		String[] exits = new String[] {"South: corridorA","North: room, East: corridorB",
				"West: corridorA, North: hall, South: kitchen","North: corridorB","South: corridorB"};
		String[] items = new String[] {"Rabbit: rabbit.png, Cake: cake.png, Campane: campane.png","Egg: egg.png","","Basket: basket.png",""};
		//create all locations for this world
		for (int i = 0; i<locations.length;i++) {
			this.addLocation(locations[i],exits[i],items[i]);
		}
		
		
	}
	/**
	 *  Method that returns a location given its name
	 * @param name of the location
	 * @return location object with given name
	 */
	public Location getLocation(String name) {
		return this.locations.get(name);
	}
	/**
	 *  Method that returns a location object which corresponds to an exit in location at a given direction
	 * @param location with the exit
	 * @param direction of the exit
	 * @return location object where the exit leads
	 */
	public Location goThroughExit(Location location, Direction direction) {
		String exitName = location.getExit(direction);
		// remove spaces
		exitName = exitName.trim();
		return getLocation(exitName);
	}
	
	/**
	 * Adds a location to the collection using strings representing the name, exits and contents of the location
	 * @param LocName name of location
	 * @param Exits   exits in the form "direction: location"
	 * @param items   in the form  "name: picturename"
	 */
	private void addLocation(String LocName,String Exits,String items) {
		//pictures will follow the following name scheme: "locationName-D.jpg", where D represents the direction with one letter: N,S,E,W
		Location location = new Location(LocName,Exits,items);
		// Add location to collection.
		this.locations.put(LocName, location);
		
	}
}
