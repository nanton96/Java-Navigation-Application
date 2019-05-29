import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import ijp.controller.*;
/**
 * 
 * Main Class to launch the application.
 * 
 * @author Nick Antonopoulos
 * @version 1.0
 */
public class MainApplication extends Application {

	public void start(Stage stage) {
		
		try {
			//code to load up the fxml file created in scenebuilder
			FXMLLoader fxmlLoader = new FXMLLoader();
			String viewerFxml = "GUI.fxml";
			AnchorPane page = (AnchorPane) fxmlLoader.load(this.getClass().getResource(viewerFxml).openStream());
			Scene scene = new Scene(page);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Deaconess Explorer");
			MyController controller = (MyController) fxmlLoader.getController();      			
			//Initialise the controller
			controller.start();

			stage.show();
        
		} catch (IOException ex) {
		   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		   System.exit(1);
		}
	}
	
    public static void main(String args[]) {
     	launch(args);
     	System.exit(0);
    }
}
