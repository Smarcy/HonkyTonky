package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.enumtypes.WeaponType;
import honkytonky.objects.Weapon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WeaponFactory {

    private final List<Weapon> weaponList = new ArrayList<>();

    public WeaponFactory() {
        createWeaponsFromFile();
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public Weapon getWeaponByName(String name) {
        for(Weapon w : weaponList) {
            if(w.getName().equals(name)) {
                return w;
            }
        }
        throw new IllegalArgumentException("Weapon " + name + " not found!");

    }

    private void createWeaponsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/weapons");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                weaponList.add(new Weapon(
                  Integer.parseInt(nextRecord[0]),      // id
                  nextRecord[1],                        // name
                  WeaponType.valueOf(nextRecord[2]),    // type
                  Integer.parseInt(nextRecord[3]),      // damage
                  Integer.parseInt(nextRecord[4]),      // durability
                  Integer.parseInt(nextRecord[5]),      // maxDurability
                  Integer.parseInt(nextRecord[6]),      // value
                  Boolean.parseBoolean(nextRecord[7])   // two-handed
                  ));
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Datei weapons!");
        }
    }
}
