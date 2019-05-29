package ijp.controller;

import ijp.user.*;
import ijp.world.*;
import ijp.item.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;


import java.util.ArrayList;

import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
/**
 * 
 * Controller Class that handles input from user and manages javaFX objects.
 * Contains information about the topology in the world object and information
 * about the user in the user object.
 * 
 * @author Nick Antonopoulos
 * @version 1.0
 */

public class MyController {
	
	private User user;
	private World world;
	@FXML
	private ImageView itemView1;
	@FXML
	private ImageView itemView2;
	@FXML
	private ImageView itemView3;
	@FXML
	private ImageView itemView4;
	@FXML
	private ImageView itemView5;
	@FXML
	private Button forwardButton;
	@FXML
	private ImageView imageView;
	@FXML 
	private MenuBar itemMenu;
	@FXML 
	private Menu pickUpMenu;
	@FXML 
	private Menu putDownMenu;
	
	//private ArrayList<Item> itemsInLoc;
	private ImageView[] itemViews;
	
	/**
	 * Start the controller.
	 */
	public void start() {	
		//Construct world and user objects
		this.world = new World();
		this.user = new User(this.world.getLocation("room"));
		//Put itemViews into an array to create a correspondence with an arrayList containing items
		this.itemViews = new ImageView[] {itemView1,itemView2,itemView3,itemView4,itemView5};
		//Clears the putDown menu
		this.putDownMenu.getItems().clear();
		//Puts items in current location in the pick up menu
		updatePickUpMenu();
		displayCurrentScene();
	}
	/**
	 * Handles the action from a menu item in the pickup menu.
	 * When the menuItem is pressed the corresponding item is picked up
	 *
	 * @param menuItem object corresponding to the menu item being pressed
	 * @param Item     item that is picked up from the location
	 */
	public void pickUpCommand(MenuItem menuItem, Item item){
		// Remove Item From Location
		user.getCurrentLocation().removeItem(item);
		// Add Item to User
		user.pickUpItem(item);
		// Add Item to PutDown Menu
		addMenuItemWithAction(this.putDownMenu, item,"PutDown");
		// Remove Item from PickUp Menu
		this.pickUpMenu.getItems().remove(menuItem);
		displayCurrentScene();
	}
	/**
	 * Handles the action from a menu item in the putDown menu.
	 * When the menuItem is pressed the corresponding item is put down
	 *
	 * @param menuItem object corresponding to the menu item being pressed
	 * @param Item     item that is put down from the location
	 */
	public void putDownCommand(MenuItem menuItem, Item item){
		// Remove Item from User
		user.putDownItem(item);
		// Add Item to Location
		user.getCurrentLocation().addItem(item);
		// Add Item to pickUp Menu
		addMenuItemWithAction(this.pickUpMenu,item,"PickUp");	
		// Remove Item from PutDown Menu
		this.putDownMenu.getItems().remove(menuItem);
		displayCurrentScene();
	}
	/**
	 * Handles the left button. 
	 * The user turns left and the scene is updated.
	 */
    public void leftCommand(ActionEvent event) {
    	user.turnLeft();
    	displayCurrentScene();

    }
    
    /**
	 * Handles the right button. 
	 * The user turns right and the scene is updated.
	 */
    public void rightCommand(ActionEvent event) {
    	user.turnRight();
    	displayCurrentScene();
    }
    /**
	 * Handles the forward button. 
	 * The user goes forward and the scene is updated.
	 */
    public void forwardCommand(ActionEvent event) {
    	user.goForward(this.world);
    	//We call updatePickUpMenu because new location has different set of items.
    	updatePickUpMenu();
    	displayCurrentScene();
    }
    
    /**
     * Updates pickUpMenu so that it has options for the items in the current location
	 */
	public void updatePickUpMenu() {
		//Remove all items from menu
		this.pickUpMenu.getItems().clear();
		//Iterate over the location inventory and add items to menu
		for (Item item : this.user.getCurrentLocation().getInventory()) {
			addMenuItemWithAction(this.pickUpMenu, item,"PickUp");
		}
		
	}
	/**
     * Handles display of items in the current location.
	 */
	public void displayItemsInCurrentLocation() {
		//An array list is used as a representation for the items and an array for the itemViews
		//As such, an item with index i will appear in itemView with index i.
		ArrayList<Item> itemsInLoc = new ArrayList<Item>();
		//Iterate over inventory to put items in the ArrayList.
		for (Item item : this.user.getCurrentLocation().getInventory()) {
			itemsInLoc.add(item);
		}
		//Empty item views
		clearItemDisplays();
		//Display all items in the array list.
		for (int i = 0; i < itemsInLoc.size(); i++) {
			Image image = new Image("/resources/" + itemsInLoc.get(i).getImageName());
			this.itemViews[i].setImage(image);
		}
	}
	
	/**
     * Clears the itemView objects so that item display can be updated.
	 */
	public void clearItemDisplays() {
		//Iterate over the imageViews array and set the images to null.
		for (ImageView itemView: this.itemViews) {
			itemView.setImage(null);
		}
	}
    
	/**
     * Handles the display of the current scene including the items.
	 */
    public void displayCurrentScene() {
    	//Display current Scene
    	String imageName = this.user.getCurrentLocation().getScene(this.user.getCurrentDirection());
    	Image image = new Image("/resources/"+ imageName + ".jpg");
        imageView.setImage(image);
        //disable forward button if current direction is not an exit
        forwardButton.setDisable(!user.getCurrentLocation().isExit(user.getCurrentDirection())); 
        displayItemsInCurrentLocation();
    }
    
    
   /**
    * Handles the addition of a menu item
    * It specifies the action of the new menu item as well as its name.
    *  
    * @param menu menu to which the new menu item will be added
    * @param item item that can be picked up or put down using the menu item
    * @param action string that specifies which method will be called when menu item is pressed.
    */
    
    public void addMenuItemWithAction(Menu menu, Item item, String action) {
    	
    	// Create a new menuItem object
    	MenuItem menuItem = new MenuItem(item.getName());
		// This adds an action corresponding to a method in the controller which is selected
    	// according to the action string.
    	menuItem.setOnAction(new EventHandler<ActionEvent>() {
    		
            public void handle(ActionEvent t) {
            	switch(action) {
            		case "PickUp":
                		pickUpCommand(menuItem, item);
                		break;
            		case "PutDown":
            			putDownCommand(menuItem, item);
            			break;
            	}
                
            }
        });
    	//Add menu item to menu
		menu.getItems().add(menuItem);
    }

}
