package ijp.user;
import ijp.location.*;
import ijp.item.*;
import ijp.world.World;
import ijp.direction.*;
import ijp.inventory.*;
/**
 * 
 * Class that represents the user.
 * It contains information about:
 * The current location and direction of the user.
 * The items that the user holds which are stored in the inventory class.
 * 
 * @author Nick Antonopoulos
 * @version 1.0
 */
public class User {
	private Location currentLocation;
	private Direction currentDirection;
	private Inventory inventory;
	
	//Initialise with a starting location
	public User(Location location) {		
		this.currentLocation = location;
		//initialise with a default direction
		this.currentDirection = Direction.North;
		//empty inventory
		Inventory inventory = new Inventory();
		this.inventory = inventory;
	}
	
	//Initialise with both a starting location and direction.
	public User(Location location, Direction direction) {
		this.currentLocation  = location;
		this.currentDirection = direction;
		//empty inventory
		Inventory inventory = new Inventory();
		this.inventory = inventory;
	}
	
	/**
	 * The direction of the user changes to the right.
	 */
	public void turnRight() {
		this.currentDirection = this.currentDirection.rightDirection();
	}
	
	/**
	 * The direction of the user changes to the left.
	 */
	public void turnLeft() {
		this.currentDirection = this.currentDirection.leftDirection();
	}
	/**
	 * User goes forward through an exit.
	 * @param world object that stores the location where the user is going
	 */
	public void goForward(World world) {
		this.currentLocation = world.goThroughExit(this.currentLocation, this.currentDirection);
	}
	/**
	 * User picks up an item which is added to the inventory.
	 * @param item to be picked up
	 */
	public void pickUpItem(Item item) {
		this.inventory.addItem(item);
	}
	/**
	 * User picks up an item which is removed from the inventory.
	 * @param item to be put down
	 */
	public void putDownItem(Item item) {
		this.inventory.removeItem(item);
	}
	/**
	 * Accessor method for the current location
	 * @return current Location object 
	 */
	public Location getCurrentLocation() {
		return this.currentLocation;
	}
	/**
	 * Accessor method for the current direction
	 * @return current Direction object 
	 */
	public Direction getCurrentDirection() {
		return this.currentDirection;
	}
}
