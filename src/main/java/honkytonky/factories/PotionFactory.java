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

    /**
     * List that contains every Potion in the game
     */
    private List<Potion> potionList = new ArrayList<>();

    /**
     * read all Potions from CSV-File
     */
    public PotionFactory() {
        createPotionsFromFile();
    }

    /**
     * Finds a Potion by its name and return a COPY of it (so it stays in potionList after use!)
     *
     * @param name name of potion to find
     * @return copy of found potion
     */
    public Potion getPotionByName(String name) {
        for (Potion p : potionList) {
            if (p.getName().equals(name)) {
                return new Potion(p.getId(), p.getName(), p.getAmount(), p.getValue(), p.getType());
            }
        }
        throw new IllegalArgumentException("Potion " + name + " not found!");
    }

    /**
     * Reads all contents from a CSV-File and creates a new Item from the contents on the fly
     */
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