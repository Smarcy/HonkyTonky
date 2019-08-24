package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.enumtypes.PotionType;
import honkytonky.objects.Potion;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PotionFactory {

    private List<Potion> potionList = new ArrayList<>();

    public PotionFactory() {
        createPotionsFromFile();
    }

    public Potion getPotionByName(String name) {
        for(Potion p : potionList) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Potion " + name + " not found!");
    }

    private void createPotionsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/potions");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                potionList.add(new Potion(
                  Integer.parseInt(nextRecord[0]),      // id
                  nextRecord[1],                        // name
                  Integer.parseInt(nextRecord[2]),      // amount
                  Integer.parseInt(nextRecord[3]),      // value
                  PotionType.valueOf(nextRecord[4])     // potionType
                ));
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei potions!");
        }
    }
}