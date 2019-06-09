package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("CRUD");
		primaryStage.setResizable(false);
		try {
		primaryStage.getIcons().add(new Image(this.getClass().getResource("icon.png").toString()));
		}
		catch(Exception ex) {
			System.out.println("brak icon.png w plikach bin programu");
		}
		
		
		
		//Menu help = new Menu(primaryStage);
		///////////////////////////////////////
		//javafx.scene.control.Menu help = new javafx.scene.control.Menu("help");
		//MenuItem about = new MenuItem("about");
		//MenuBar menuBar = new MenuBar();
		//menuBar.getMenus().add(help);
		//VBox vbox = new VBox(menuBar);
		////////////////////////////////////////
		//Scene scena = new Scene(vbox);
		//primaryStage.setScene(scena);
		//menuBar.iss
		
		primaryStage.setScene(new Menu(primaryStage).getScene());
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
