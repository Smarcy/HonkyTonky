package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.enumtypes.ArmorType;
import honkytonky.objects.Armor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ArmorFactory {

    /**
     * List that contains every Armor in the game
     */
    private final List<Armor> armors = new ArrayList<>();

    private static final ArmorFactory instance = new ArmorFactory();

    private ArmorFactory() {}

    public static ArmorFactory getInstance() {
        return instance;
    }

    /**
     * read all Armors from CSV-File
     */
    public void createArmors() {
        createArmorsFromFile();
    }

    /**
     * Finds an Armor from the List of all Armors by its name
     *
     * @param name the name of the armor
     * @return the Armor
     */
    public Armor getArmorByName(String name) {
        for (Armor armor : armors) {
            if (armor.getName().equals(name)) {
                return armor;
            }
        }
        throw new IllegalArgumentException("Armor " + name + " not found!");
    }

    /**
     * Reads all contents from a CSV-File and creates a new Item from the contents on the fly
     */
    private void createArmorsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/armors");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                armors.add(new Armor(
                  Integer.parseInt(nextRecord[0]),      // id
                  nextRecord[1],                        // name
                  Integer.parseInt(nextRecord[2]),      // damage
                  Integer.parseInt(nextRecord[3]),      // durability
                  Integer.parseInt(nextRecord[4]),      // maxDurability
                  Integer.parseInt(nextRecord[5]),      // value
                  ArmorType.valueOf(nextRecord[6])      // type
                ));
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Datei armors!");
        }
    }
}
