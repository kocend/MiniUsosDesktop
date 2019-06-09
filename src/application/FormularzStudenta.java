package application;


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

public class FormularzStudenta {
	private TextField imieField;
	private TextField nazwiskoField;
	private TextField nrUSOSField;
	private TextField punktyKolokwiumI;
	private TextField punktyKolokwiumII;
	private Text sumaPunktow;
	private Text ocenaKoncowa;
	
	public FormularzStudenta(Student student, Stage primaryStage, MenuGrupy obj){
		
		
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
		
		Text imieLabel = new Text("imie:");
		Text nazwiskoLabel = new Text("nazwisko:");
		Text nrUSOSLabel = new Text("nr USOS:");
		Text punktyKolokwiumILabel = new Text("punkty z kolokwium I (0-50):");
		Text punktyKolokwiumIILabel = new Text("punkty z kolokwium II (0-50):");
		Text sumaPunktowLabel = new Text("suma punktów z obu kolokwiów:");
		Text ocenaKoncowaLabel = new Text("ocena koñcowa studenta:");
		Text errorMessage = new Text(" ");
		
		imieField = new TextField();
		nazwiskoField = new TextField();
		nrUSOSField = new TextField();
		punktyKolokwiumI = new TextField();
		punktyKolokwiumII = new TextField();
		sumaPunktow = new Text();
		ocenaKoncowa = new Text();
		
		
		imieField.setText(student.getImie());
		nazwiskoField.setText(student.getNazwisko());
		nrUSOSField.setText(student.getNumerUSOS().toString());
		punktyKolokwiumI.setText(student.getPunktyKolokwiumI().toString());
		punktyKolokwiumII.setText(student.getPunktyKolokwiumII().toString());
		sumaPunktow.setText(student.getSumaPunktow().toString());
		ocenaKoncowa.setText(student.getOcenaKoncowa().toString());
		
		
		Button zapisz = new Button("zapisz");
		
		zapisz.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(testNrUSOS()
					&&testPunktyKolokwium(punktyKolokwiumI)
					&&testPunktyKolokwium(punktyKolokwiumII)) {
					student.setImie(imieField.getText());
					student.setNazwisko(nazwiskoField.getText());
					student.setNumerUSOS(Integer.parseInt(nrUSOSField.getText()));
					student.setPunktyKolokwiumI(Integer.parseInt(punktyKolokwiumI.getText()));
					student.setPunktyKolokwiumII(Integer.parseInt(punktyKolokwiumII.getText()));

					sumaPunktow.setText(student.getSumaPunktow().toString());
					ocenaKoncowa.setText(student.getOcenaKoncowa().toString());
					errorMessage.setFill(Color.GREEN);
					errorMessage.setText("zapisano pomyœlnie!");
					obj.zaktualizujListeStudentow();
				}
				else {
					errorMessage.setFill(Color.RED);
					errorMessage.setText("niepoprawne dane!");
				}
			}
		});
		
		Button zakoncz = new Button("zakoñcz");
		
		zakoncz.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
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
		
		gridPane.add(imieLabel, 0, 0);
		gridPane.add(imieField, 1, 0);
		gridPane.add(nazwiskoLabel, 0, 1);
		gridPane.add(nazwiskoField, 1, 1);
		gridPane.add(nrUSOSLabel, 0, 2);
		gridPane.add(nrUSOSField, 1, 2);
		gridPane.add(punktyKolokwiumILabel, 0, 3);
		gridPane.add(punktyKolokwiumI, 1, 3);
		gridPane.add(punktyKolokwiumIILabel, 0, 4);
		gridPane.add(punktyKolokwiumII, 1, 4);
		gridPane.add(sumaPunktowLabel, 0, 5);
		gridPane.add(sumaPunktow, 1, 5);
		gridPane.add(ocenaKoncowaLabel, 0, 6);
		gridPane.add(ocenaKoncowa, 1, 6);
		gridPane.add(errorMessage, 1, 7);
		gridPane.add(panelSterowania, 1, 8);
		
		gridPane.setStyle("-fx-background-color: rgba(100, 149, 237,0.2);"); 
		
		dialog.setScene(new Scene(gridPane));
		dialog.show();
	}
	
	private boolean testNrUSOS() {
		try{
			Integer.parseInt(nrUSOSField.getText());
		}
		catch(NumberFormatException ex) {
			return false;
		}
		return true;
	}
	
	private boolean testPunktyKolokwium(TextField pole) {
		int punkty;
		try {
			punkty = Integer.parseInt(pole.getText());
		}
		catch(NumberFormatException ex) {
			return false;
		}
		if(punkty<0||punkty>50)
			return false;
		return true;
	}
}
