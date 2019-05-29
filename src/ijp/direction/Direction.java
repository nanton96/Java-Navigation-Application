package ijp.direction;
/**
 * 
 * Class that represents possible directions.
 * Consists of four immutable objects, one for each direction.
 * 
 * @author Nick Antonopoulos
 * @version 1.0
 */
public class Direction {
    //Instances of the class created by the private constructor.
	//Idea adapted from IJP Lecture 2.
	public static final Direction North = new Direction("North");
	public static final Direction West  = new Direction("West");
	public static final Direction South = new Direction("South");
	public static final Direction East  = new Direction("East");
	
	private String Name;
	
	private Direction(String Name) {
		this.Name = Name;
	}
	/**
	 * Method that returns the right direction
	 * @return Direction that is right to the current one
	 */
	public Direction rightDirection(){
		if (this.equals(North)){
			return West;
		} else if(this.equals(West)){
			return South;
		} else if (this.equals(South)){
			return East;
		} else if (this.equals(East)) {
			return North;
		} else {
			throw new IllegalArgumentException("argument not a valid Direction");
		}
	}
	/**
	 * Method that returns the left direction
	 * @return Direction that is left to the current one
	 */
	public Direction leftDirection(){
		if (this.equals(North)){
			return East;
		} else if(this.equals(East)){
			return South;
		} else if (this.equals(South)){
			return West;
		} else if (this.equals(West)) {
			return North;
		} else {
			throw new IllegalArgumentException("argument not a valid Direction");
		}
	}
	/**
	 * Method to access the string representation of a direction.
	 * @param direction
	 * @return String Name of direction
	 */
	public String getName(Direction direction) {
		return this.Name;
	}
	/**
	 * Returns the object corresponding to a specific directions given a string representation.
	 * @param Name name of direction to be returned
	 * @return Direction object with given name
	 */
	public static Direction getDirection(String Name) {
		//Trim and set to lowercase
		Name = Name.trim();
		Name = Name.toLowerCase();
		if (Name.equals("north")){
			return North;
		} else if(Name.equals("south")){
			return South;
		} else if (Name.equals("east")){
			return East;
		} else if (Name.equals("west")) {
			return West;
		} else {
			//throws an error when direction is not found
			throw new IllegalArgumentException("argument not a valid Direction name");
		}
	}
	
}
