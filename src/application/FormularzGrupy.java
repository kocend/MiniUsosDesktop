package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormularzGrupy {
	private TextField numerField;
	private TextField dzienTygodniaField;
	private TextField godzinaRozpoczeciaField;
	private TextField godzinaZakonczeniaField;
	
	public FormularzGrupy(Grupa grupa, Stage primaryStage, Menu obj){
		
		final Stage dialog = new Stage();
		dialog.initOwner(primaryStage);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setResizable(false);
		
		try {
			dialog.getIcons().add(new Image(this.getClass().getResource("icon.png").toString()));
			}
			catch(Exception ex) {
				System.out.println("brak icon.png w plikach bin programu");
			}
		
		Text numerLabel = new Text("numer:");
		Text dzienTygodniaLabel = new Text("dzien tygodnia:");
		Text godzinaRozpoczeciaLabel = new Text("godzina rozpoczêcia:");
		Text godzinaZakonczeniaLabel = new Text("godzina zakoñczenia:");
		Text errorMessage = new Text(" ");
		
		numerField = new TextField();
		dzienTygodniaField = new TextField();
		godzinaRozpoczeciaField = new TextField();
		godzinaZakonczeniaField = new TextField();
		
		numerField.setText(grupa.getNumer().toString());
		dzienTygodniaField.setText(grupa.getDzienTygodnia().toString());
		godzinaRozpoczeciaField.setText(grupa.getGodzinaRozpoczecia());
		godzinaZakonczeniaField.setText(grupa.getGodzinaZakonczenia());
		
		
		Button zapisz = new Button("zapisz");
		zapisz.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(testNumer()
						&&testDzienTygodnia()
						&&testGodzina(godzinaRozpoczeciaField)
						&&testGodzina(godzinaZakonczeniaField)
						&&(godzinaRozpoczeciaField.getText().trim().compareTo(godzinaZakonczeniaField.getText().trim())<0)){
					grupa.setNumer(Integer.parseInt(numerField.getText()));
					grupa.setDzienTygodnia(DZIEN_TYGODNIA.valueOf(dzienTygodniaField.getText().trim()));
					grupa.setGodzinaRozpoczecia(godzinaRozpoczeciaField.getText().trim());
					grupa.setGodzinaZakonczenia(godzinaZakonczeniaField.getText().trim());
					
					errorMessage.setFill(Color.GREEN);
					errorMessage.setText("zapisano pomyœlnie!");
					obj.zaktualizujGrupy();
				}
				else {
					errorMessage.setFill(Color.RED);
					errorMessage.setText("niepoprawne dane!");
				}
					
			}
		});
		
		Button zakoncz = new Button("zakoncz");
		zakoncz.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				dialog.close();
			}
		});
		GridPane panelSterowania = new GridPane();
		panelSterowania.setPadding(new Insets(10, 10, 10, 10)); 
		panelSterowania.setVgap(10); 
		panelSterowania.setHgap(10);       
		
		panelSterowania.add(zapisz, 0, 0);
		panelSterowania.add(zakoncz, 1, 0);
		
		GridPane gridPane = new GridPane();    
		gridPane.setMinSize(300, 100); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane.setVgap(10); 
		gridPane.setHgap(10);       
		gridPane.setAlignment(Pos.CENTER); 
		
		gridPane.add(numerLabel, 0, 1);
		gridPane.add(numerField, 1, 1);
		gridPane.add(dzienTygodniaLabel, 0, 2);
		gridPane.add(dzienTygodniaField, 1, 2);
		gridPane.add(godzinaRozpoczeciaLabel, 0, 3);
		gridPane.add(godzinaRozpoczeciaField, 1, 3);
		gridPane.add(godzinaZakonczeniaLabel, 0, 4);
		gridPane.add(godzinaZakonczeniaField, 1, 4);
		gridPane.add(errorMessage, 1, 5);
		gridPane.add(panelSterowania, 1, 6);
		
		gridPane.setStyle("-fx-background-color: rgba(100, 149, 237,0.2);"); 
		
		dialog.setScene(new Scene(gridPane));
		dialog.show();
		
	}
	
	private boolean testDzienTygodnia() {
		try {
			DZIEN_TYGODNIA.valueOf(dzienTygodniaField.getText().trim());
		}
		catch(IllegalArgumentException ex) {
			return false;
		}
		return true;
	}
	
	private boolean testNumer() {
		try {
			Integer.parseInt(numerField.getText().trim());
		}
		catch(NumberFormatException ex) {
			return false;
		}
		return true;
	}
	
	private boolean testGodzina(TextField pole) {
		String godzina = pole.getText().trim().substring(0, 2);
		String minuta = pole.getText().trim().substring(3);
		Character znak = pole.getText().trim().charAt(2);
		
		ArrayList<String> correctHours = new ArrayList<>();
		for(int i=0; i<24; i++) {
			String godz = Integer.toString(i);
			if(i<10)
				correctHours.add("0"+godz);
			else
				correctHours.add(godz);
		}
		
		ArrayList<String> correctMinutes = new ArrayList<>();
		for(int i=0; i<60; i++) {
			String min = Integer.toString(i);
			if(i<10)
				correctMinutes.add("0"+min);
			else
				correctMinutes.add(min);
		}

		
		if(correctHours.contains(godzina)&&correctMinutes.contains(minuta)&&znak==':')
			return true;
		else
			return false;
	}
}
