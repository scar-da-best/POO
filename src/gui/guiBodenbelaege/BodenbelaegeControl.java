package gui.guiBodenbelaege;
import java.io.IOException;

import business.gardine.GardineModel;
import business.teppich.TeppichModel;
import javafx.stage.Stage;
import ownUtil.Observer;

public class BodenbelaegeControl implements Observer
{	
	private BodenbelaegeView bodenbelaegeView;
	private TeppichModel teppichModel;
	private GardineModel gardineModel;
	
	public BodenbelaegeControl(Stage primaryStage)
	{
 		this.teppichModel = TeppichModel.getInstance(); 		
 		this.teppichModel.addObserver(this);
		this.bodenbelaegeView = new BodenbelaegeView(this, primaryStage,teppichModel);
		this.gardineModel = GardineModel.getInstance();
	}
	
	@Override
	public void update() 
	{
		this.bodenbelaegeView.zeigeTeppicheAn();
		
	}
	
	public void leseSporthalleAusCsvDatei()
    {
    	try 
    	{
    		this.gardineModel.leseSporthallenAusCsvDatei();
		} 
    	catch (IOException e) 
    	{
			this.bodenbelaegeView.zeigeFehlermeldungsfensterAn("IOException beim Lesen von Gardinen!");
		}
    	catch (Exception e)
    	{
    		this.bodenbelaegeView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen von Gardinen!");
    		e.printStackTrace();
    	}
    	
    }
}
