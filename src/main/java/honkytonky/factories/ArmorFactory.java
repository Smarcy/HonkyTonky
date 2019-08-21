package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.enumtypes.WeaponType;
import honkytonky.objects.Armor;
import honkytonky.enumtypes.ArmorType;
import honkytonky.objects.Weapon;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmorFactory {

    private List<Armor> armorList = new ArrayList<>();

    public ArmorFactory() {
        createArmorsFromFile();
    }

    public List<Armor> getArmorList() {
        return armorList;
    }

    public Armor findArmorByName(String name) {
        for(Armor armor : armorList) {
            if(armor.getName().equals(name)) {
                return armor;
            }
        }
        return null;
    }

    private void createArmorsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/armors");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                armorList.add(new Armor(
                  Integer.parseInt(nextRecord[0]),      // id
                  nextRecord[1],                        // name
                  Integer.parseInt(nextRecord[2]),      // damage
                  Integer.parseInt(nextRecord[3]),      // durability
                  Integer.parseInt(nextRecord[4]),      // maxDurability
                  ArmorType.valueOf(nextRecord[5])      // type
                ));
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei rooms!");
        }
    }
}
