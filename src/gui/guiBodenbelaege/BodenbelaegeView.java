package gui.guiBodenbelaege;
   
import business.gardine.Gardine;
import business.gardine.GardineModel;
import business.teppich.Teppich;
import business.teppich.TeppichModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class BodenbelaegeView
{
	
	private BodenbelaegeControl bodenbelaegeControl;
	private TeppichModel teppichModel;	
	private GardineModel gardineModel;

	//---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new  Pane();
	private Label lblAnzeigeTeppiche     = new Label("Anzeige Teppiche");
	private TextArea txtAnzeigeTeppiche  = new TextArea();
	private Button btnAnzeigeTeppiche    = new Button("Anzeige");
	
	private Label lblAnzeigeGardine      = new Label("Anzeige Gardinen");
	private TextArea txtAnzeigeGardine   = new TextArea();
	private Button btnAnzeigeGardine     = new Button("import und Anzeige");
	//-------Ende Attribute der grafischen Oberflaeche-------

	public BodenbelaegeView( BodenbelaegeControl bodenbelaegeControl, Stage primaryStage, TeppichModel teppichModel)
	{
		Scene scene = new Scene(this.pane, 560, 340);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Anzeige von Bodenbelaegen");
		primaryStage.show();
		this.bodenbelaegeControl = bodenbelaegeControl;
		this.teppichModel = teppichModel;
		this.gardineModel = GardineModel.getInstance();
		this.initKomponenten();
		this.initListener();
	}

 	private void initKomponenten()
 	{
    		// Label
 		Font font = new Font("Arial", 20);
       	lblAnzeigeTeppiche.setLayoutX(310);
		lblAnzeigeTeppiche.setLayoutY(40);
		lblAnzeigeTeppiche.setFont(font);
		lblAnzeigeTeppiche.setStyle("-fx-font-weight: bold;"); 
		
		lblAnzeigeGardine.setLayoutX(20);
		lblAnzeigeGardine.setLayoutY(40);
		lblAnzeigeGardine.setFont(font);
		lblAnzeigeGardine.setStyle("-fx-font-weight: bold;"); 

		pane.getChildren().add(lblAnzeigeTeppiche); 
		pane.getChildren().add(lblAnzeigeGardine);
// Textbereich	
    	txtAnzeigeTeppiche.setEditable(false);
 		txtAnzeigeTeppiche.setLayoutX(310);
		txtAnzeigeTeppiche.setLayoutY(90);
 		txtAnzeigeTeppiche.setPrefWidth(220);
		txtAnzeigeTeppiche.setPrefHeight(185);
		
		txtAnzeigeGardine.setEditable(false);
		txtAnzeigeGardine.setLayoutX(20);
		txtAnzeigeGardine.setLayoutY(90);
		txtAnzeigeGardine.setPrefWidth(220);
		txtAnzeigeGardine.setPrefHeight(185);

	   	pane.getChildren().add(txtAnzeigeTeppiche);  
		pane.getChildren().add(txtAnzeigeGardine);

    	// Button
      	btnAnzeigeTeppiche.setLayoutX(310);
    	btnAnzeigeTeppiche.setLayoutY(290);
    	
    	btnAnzeigeGardine.setLayoutX(20);
    	btnAnzeigeGardine.setLayoutY(290);
    	
    	pane.getChildren().add(btnAnzeigeTeppiche); 
    	pane.getChildren().add(btnAnzeigeGardine); 

   }
   
   private void initListener() 
   {
	    btnAnzeigeTeppiche.setOnAction( e ->  zeigeTeppicheAn());
	    btnAnzeigeGardine.setOnAction(e -> zeigeGardinenAn());
    }
   
    public void zeigeTeppicheAn()
    {
    		if(!teppichModel.getTeppiche().isEmpty())
    		{
    			StringBuffer text = new StringBuffer();
    			for(Teppich teppich : teppichModel.getTeppiche())
    			{
    				text.append(teppich.gibTeppichZurueck(' '));
    			}
    			txtAnzeigeTeppiche.setText(text.toString());
    		}
    		else
    		{
    			zeigeInformationsfensterAn(
 				"Bisher wurde kein Teppich aufgenommen!");
    		}
    }	
    
    public void zeigeGardinenAn()
    {
    	bodenbelaegeControl.leseSporthalleAusCsvDatei();
    	if(gardineModel.getGardinen().size() > 0)
    	{
    		StringBuffer text = new StringBuffer();
    		for(Gardine garinde : gardineModel.getGardinen())
    		{
    			text.append(garinde.gibGardineZurueck(' ') +  '\n');
    		}
    		this.txtAnzeigeGardine.setText(text.toString());
    	}
    	else
    	{
    		zeigeInformationsfensterAn("Es gibt keine Garine in der csv datei!!");
    	}
    }
   
    private void zeigeInformationsfensterAn(String meldung)
    {
    	  	new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    public void zeigeFehlermeldungsfensterAn(String meldung) {
        new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung).zeigeMeldungsfensterAn();
    }
    
    
}
