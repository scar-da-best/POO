package gui;

import java.io.IOException;

import business.Teppich;
import business.TeppichModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.Observer;

public class TeppichController implements Observer
{
    private TeppichView view;
    private TeppichModel model;

    
    public TeppichController(Stage primaryStage) 
    {
        this.view = new TeppichView();  
        this.model = TeppichModel.getInstance();
        model.addObserver(this);
        initListener();
        initStage(primaryStage);
    }
    private void initStage(Stage primaryStage) 

    {
        Scene scene = new Scene(view.getPane(), 700, 340);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Verwaltung von Teppichen");
        primaryStage.show();
    }

    private void initListener() {
        view.getBtnEingabe().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                nehmeTeppichAuf();
            }
        });
        view.getBtnAnzeige().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                zeigeTeppicheAn();
            }
        });
        view.getMnItmCsvImpkategorie().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                leseAusDatei("csv");
            }
        });
        view.getMnItmTxtImpkategorie().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                leseAusDatei("txt");
            }
        });
        view.getMnItmCsvExpkategorie().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                schreibeInCsvDatei();
            }
        });
    }

    private void nehmeTeppichAuf() 
    {
        try 
        {
            model.nehmeTeppichAuf(
                view.getTxtArtikelnummer().getText(),
                view.getTxtKategorie().getText(),
                Integer.parseInt(view.getTxtBreite().getText()),
                Integer.parseInt(view.getTxtLaenge().getText()),
                view.getTxtFarben().getText().split(";"));
            zeigeInformationsfensterAn("Der Teppich wurde aufgenommen!");
        } 
        catch (Exception exc) 
        {
            zeigeFehlermeldungsfensterAn(exc.getMessage());
        }
    }

    // wir haben zwei möglichkeiten, entweder, der controler greift auf Teppich zu und alles läuft normal (und methoden von teppich Klasse werden sofort aufgerufen)
 
    // oder in der Model klasse erzeugen wir eine neue Methode, die das macht, und als string zurück gibt
    private void zeigeTeppicheAn() 
    {
        if (model.getTeppiche().size() > 0) 
        {
        	this.view.getTxtAnzeige().setText(this.model.gibTeppicheZurueckAlsString());
        	//direkt zu grifen: 
        	/*
        	 String teppicheAlsString = "";
        	 for(Teppich teppich : model.getTeppiche()
        	 {
        	 	teppicheAlsString += teppich.gibTeppichZurueck(' ');
        	 }
        	 this.view.getTxtAnzeige().setText(teppicheAlsString);
        	 * */
        } 
     
        else 
        {
            zeigeInformationsfensterAn("Bisher wurde kein Teppich aufgenommen!");
        }
    }

    private void leseAusDatei(String typ) 
    {
        try 
        {
            model.leseAusDatei(typ);
            zeigeInformationsfensterAn("Der Teppich wurde gelesen!");
        } catch (UnsupportedOperationException e) {
            zeigeInformationsfensterAn(e.getMessage());
        } catch (IOException exc) {
            zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
        } catch (Exception exc) {
            zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
            exc.printStackTrace();
        }
    }

    private void schreibeInCsvDatei() {
        try {
            model.schreibeInCsvDatei();
            zeigeInformationsfensterAn("Der Teppich wurde gespeichert!");
        } catch (IOException exc) {
            zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
        } catch (Exception exc) {
            zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
        }
    }

    private void zeigeInformationsfensterAn(String meldung) {
        new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
    }

    private void zeigeFehlermeldungsfensterAn(String meldung) {
        new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung).zeigeMeldungsfensterAn();
    }

	@Override
	public void update() 
	{
		zeigeTeppicheAn();
	}
}