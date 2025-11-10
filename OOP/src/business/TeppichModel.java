package business;

import java.io.BufferedWriter;  // Hinzugefügt: Fehlte für BufferedWriter
import java.io.FileWriter;      // Hinzugefügt: Fehlte für FileWriter
import java.io.IOException;
import factory.Creator;
import factory.ConcreteCreatorCsv;
import factory.ConcreteCreatorTxt;
import factory.Product;

public class TeppichModel {
    private Teppich teppich;  // Einzelnes Attribut für einen Teppich (kann null sein)

    // Getter für den Teppich
    public Teppich getTeppich() {
        return teppich;
    }

    // Erzeugt einen neuen Teppich aus Eingabedaten (delegiert vom Controller)
    public void nehmeTeppichAuf(String artikelnummer, String kategorie, int breite, int laenge, String[] farben) throws Exception {
        this.teppich = new Teppich(artikelnummer, kategorie, breite, laenge, farben);
    }

    // Liest einen Teppich aus einer Datei (csv oder txt) mit Factory-Methode
    public void leseAusDatei(String typ) throws IOException, Exception {
        Creator creator;
        if ("csv".equals(typ)) {
            creator = new ConcreteCreatorCsv();
        } else if ("txt".equals(typ)) {
            creator = new ConcreteCreatorTxt();
        } else {
            throw new UnsupportedOperationException("Noch nicht implementiert!");
        }
        Product product = creator.factoryMethod();
        String[] zeile = product.leserAusDatei();
        product.schlieesenDatei();
        this.teppich = new Teppich(zeile[0], zeile[1], Integer.parseInt(zeile[2]), Integer.parseInt(zeile[3]), zeile[4].split("_"));
    }

    // Schreibt den aktuellen Teppich in eine CSV-Datei
    public void schreibeInCsvDatei() throws IOException, Exception {
        if (this.teppich == null) {
            throw new Exception("Kein Teppich zum Speichern vorhanden!");
        }
        BufferedWriter aus = new BufferedWriter(new FileWriter("TeppicheAusgabe.csv", true));
        aus.write(teppich.gibTeppichZurueck(';'));
        aus.close();
    }
}
