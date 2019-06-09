package application;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuGrupy {
	private Scene menuGrupy;
	private ListView<Student> studenci;
	private Grupa grupa;
	private Student chosenStudent;

	public MenuGrupy(Stage primaryStage, Scene previousScene, Grupa grupa) {
		this.grupa=grupa;
		
		studenci = new ListView<>();
		zaktualizujListeStudentow();
		studenci.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {

			@Override
			public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
				chosenStudent=newValue;
			}
		});
		
		
		Text listaText = new Text("Lista studentów:");
		
		Button cofnij = new Button("cofnij");
		cofnij.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				primaryStage.setScene(previousScene);
			}
		});
		
		GridPane naglowek = new GridPane();
		naglowek.setPadding(new Insets(0,10,0,10));
		naglowek.setVgap(10);
		naglowek.setHgap(10);
		
		naglowek.add(cofnij, 0, 0);
		naglowek.add(listaText, 1, 0);
		
		Button dodaj = new Button("dodaj studenta");
		dodaj.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Student student = new Student("","",0);
				grupa.getListaStudentow().add(student);
				new FormularzStudenta(student, primaryStage, MenuGrupy.this);
			}
		});
		
		Button usun = new Button("usuñ studenta");
		usun.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(null!=chosenStudent) {
					grupa.getListaStudentow().remove(chosenStudent);
					zaktualizujListeStudentow();
				}
			}
		});
		
		Button wyswietl = new Button("wyœwietl studenta");
		wyswietl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(null!=chosenStudent)
					new FormularzStudenta(chosenStudent, primaryStage, MenuGrupy.this);
			}
		});
		
		
		GridPane miniMenu = new GridPane();
		miniMenu.setMinSize(300, 30);
		miniMenu.setPadding(new Insets(0,10,0,10));
		miniMenu.setVgap(10);
		miniMenu.setHgap(10);
		miniMenu.add(wyswietl, 0, 0);
		miniMenu.add(dodaj, 1, 0);
		miniMenu.add(usun, 2, 0);
		
		GridPane gridPane = new GridPane();    
		gridPane.setMinSize(300, 100); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane.setVgap(10); 
		gridPane.setHgap(10);       
		gridPane.setAlignment(Pos.CENTER); 
		
		gridPane.add(naglowek, 0, 0);
		gridPane.add(studenci, 0, 1);
		gridPane.add(miniMenu, 0, 2);
		gridPane.setPrefHeight(300);
		
		gridPane.setStyle("-fx-background-color: rgba(100, 149, 237,0.2);"); 
		
		menuGrupy = new Scene(gridPane);
	}

	public void zaktualizujListeStudentow() {
		studenci.getItems().clear();
		studenci.getItems().addAll(grupa.getListaStudentow());
	}
	
	public Scene getScene() {
		return menuGrupy;
	}
}
