package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import fileCreatorsALGOL.ConcreteCreatorCsvTxt;
import fileCreatorsALGOL.ReaderCreatorALGOL;
import fileCreatorsALGOL.ReaderProductALGOL;
import ownUtil.Observable;
import ownUtil.Observer;

public class TeppichModel implements Observable
{
    private static TeppichModel teppichModel;
    private ArrayList<Teppich> teppiche = new ArrayList<Teppich>();
    private Vector<Observer> observers = new Vector<Observer>();
    
    private TeppichModel() {}
    
    public static TeppichModel getInstance()
    {
    	if(teppichModel == null)
    	{
    		teppichModel = new TeppichModel();
    	}
    	return teppichModel;
    }

   public ArrayList<Teppich> getTeppiche() 
    {
        return this.teppiche;
    }
   
   public void addTeppich(Teppich teppich)
   {
	   this.teppiche.add(teppich);
	   notifyObserver();
   }

    public void nehmeTeppichAuf(String artikelnummer, String kategorie, int breite, int laenge, String[] farben) throws Exception 
    {
    	//NEUUUUUUUUUUUUUUUUUUUUUU
        Teppich teppich = new Teppich(artikelnummer, kategorie, breite, laenge, farben);
        addTeppich(teppich); 
    }

    public void leseAusDatei(String typ) throws IOException, Exception
    {
    	ReaderCreatorALGOL creator;
    	
        if ("csv".equals(typ) || "txt".equals(typ)) 
        {
        	creator= new ConcreteCreatorCsvTxt();
        } else 
        {
            throw new UnsupportedOperationException("Noch nicht implementiert!");
        }
        
        ReaderProductALGOL product = creator.factoryMethod(typ);
        String[] zeile = product.leserAusDatei();
        product.schlieesenDatei();
        
       // NEUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
        Teppich teppich = new Teppich(zeile[0], zeile[1], Integer.parseInt(zeile[2]), Integer.parseInt(zeile[3]), zeile[4].split("_"));
        addTeppich(teppich);
    }

    public void schreibeInCsvDatei() throws IOException, Exception 
    {
        if (this.getTeppiche().isEmpty() || this.getTeppiche() == null) 
        {
            throw new Exception("Kein Teppich zum Speichern vorhanden!");
        }
        
        BufferedWriter aus = new BufferedWriter(new FileWriter("TeppicheAusgabe.csv", true));
        
        //NEUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
        for(Teppich teppich : this.teppiche)
        {
        	aus.write(teppich.gibTeppichZurueck(';'));        	
        }
        aus.close();
    }
    
    
    public String gibTeppicheZurueckAlsString() 
    {
    	String teppiche = "";
    	
    	for(Teppich teppich : this.getTeppiche())
    	{
    		teppiche += teppich.gibTeppichZurueck(' ');
    	}
    	return teppiche;
    }

	@Override
	public void addObserver(Observer obs) 
	{
		observers.addElement(obs);
		
	}

	@Override
	public void removeObserver(Observer obs) 
	{
		observers.removeElement(obs);
	}

	@Override
	public void notifyObserver() 
	{
		for(Observer observer : observers)
		{
			observer.update();
		}
		
	}
}