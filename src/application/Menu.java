package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu {
	private Scene menu;
	private ListView<Grupa> grupyListView;
	private LinkedList<Grupa> listaGrup;
	private Grupa chosenGroup;

	public Menu(Stage primaryStage) {
		
		Text listaText = new Text("Lista grup:");
		
		grupyListView = new ListView<>();
		grupyListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Grupa>() {

			@Override
			public void changed(ObservableValue<? extends Grupa> observable, Grupa oldValue, Grupa newValue) {
				chosenGroup=newValue;
			}
		});
		
		listaGrup = new LinkedList<>();
		
		wczytajStan();
				
		Button dodaj = new Button("dodaj");
		dodaj.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Grupa nowaGrupa = new Grupa(0, DZIEN_TYGODNIA.poniedzia³ek, "00:00", "00:00");
				listaGrup.add(nowaGrupa);
				new FormularzGrupy(nowaGrupa, primaryStage, Menu.this);
			}
		});
		
		Button usun = new Button("usuñ");
		usun.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(null!=chosenGroup) {
					listaGrup.remove(chosenGroup);
					zaktualizujGrupy();
				}
			}
		});
		
		Button edytuj = new Button("edytuj");
		edytuj.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(null!=chosenGroup)
					new FormularzGrupy(chosenGroup, primaryStage, Menu.this);
			}
		} );
		
		Button wyswietl = new Button("wyœwietl");
		wyswietl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(null!=chosenGroup)
					primaryStage.setScene(new MenuGrupy(primaryStage, getScene(), chosenGroup).getScene());
			}
		});
		
		Button zapisz = new Button("zapisz pracê");
		zapisz.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				zapiszStan();
			}
		});
		
		GridPane naglowek = new GridPane();
		naglowek.setPadding(new Insets(0,10,0,10));
		naglowek.setVgap(10);
		naglowek.setHgap(10);
		naglowek.add(listaText, 0, 0);
		naglowek.add(zapisz, 15, 0);
		
		GridPane miniMenu = new GridPane();
		miniMenu.setMinSize(300, 30);
		miniMenu.setPadding(new Insets(0,40,0,40));
		miniMenu.setVgap(10);
		miniMenu.setHgap(10);
		miniMenu.add(wyswietl, 0, 0);
		miniMenu.add(edytuj, 1, 0);
		miniMenu.add(dodaj, 2, 0);
		miniMenu.add(usun, 3, 0);
		
		GridPane gridPane = new GridPane();    
		gridPane.setMinSize(300, 100); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane.setVgap(10); 
		gridPane.setHgap(10);       
		gridPane.setAlignment(Pos.CENTER); 
		
		gridPane.add(naglowek, 0, 0);
		gridPane.add(grupyListView, 0, 1);
		gridPane.add(miniMenu, 0, 2);
		gridPane.setPrefHeight(300);
		
		gridPane.setStyle("-fx-background-color: rgba(100, 149, 237,0.2);"); 
		
		menu = new Scene(gridPane);
	}

	public Scene getScene() {
		return menu;
	}
	public void zaktualizujGrupy() {
		grupyListView.getItems().clear();
		grupyListView.getItems().addAll(listaGrup);
	}
	
	private void zapiszStan() {
		File saveFile = new File("save");
		if(saveFile.exists()) 
			saveFile.delete();
		FileOutputStream fileStream = null;
		ObjectOutputStream objectStream = null;
		try {
			fileStream = new FileOutputStream("save");
			objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(listaGrup);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				objectStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void wczytajStan() {
		File file = new File("save");
		FileInputStream fileStream;
		ObjectInputStream objectStream = null;
		if(file.exists()) {
			try {
				fileStream = new FileInputStream("save");
				objectStream = new ObjectInputStream(fileStream);
				listaGrup = (LinkedList<Grupa>)objectStream.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				try {
					objectStream.close();
					zaktualizujGrupy();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
