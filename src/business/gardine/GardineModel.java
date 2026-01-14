package business.gardine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import business.teppich.Teppich;
import business.teppich.TeppichModel;
import fileCreatorsALGOL.ConcreteCreatorCsvTxt;
import fileCreatorsALGOL.ReaderCreatorALGOL;
import fileCreatorsALGOL.ReaderProductALGOL;
import ownUtil.Observer;

public class GardineModel 
{
	private ArrayList<Gardine> gardinen = new ArrayList<>();
	private static  GardineModel gardineModel = null;
	
	private GardineModel() {};
	
	public static GardineModel getInstance()
	{
		if(gardineModel == null)
		{
			GardineModel.gardineModel = new GardineModel();
		}
		
		return GardineModel.gardineModel;
	}
	
	public void leseSporthallenAusCsvDatei() throws IOException

	{
		
		BufferedReader ein = new BufferedReader(new FileReader("Gardinen.csv"));
		ArrayList<Gardine> ergebnis = new ArrayList <>();
		String zeileStr = ein.readLine();
		
		while (zeileStr != null)
		{
			String[] zeile = zeileStr.split(";");
			ergebnis.add(new Gardine(zeile[0], zeile[1], zeile[2]));
			zeileStr = ein.readLine();
			
		}
		
		ein.close();
		this.gardinen = ergebnis;
	}
	

	    public  ArrayList<Gardine> getGardinen() 
	    {
	        return gardinen;
	    }
	    
	    public void addGardine(Gardine gardine)
	    {
	    	gardinen.add(gardine);
	    }
	    

	    

}
