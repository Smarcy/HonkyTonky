package honkytonky.factories;

import com.opencsv.CSVReader;
import honkytonky.enumtypes.MonsterType;
import honkytonky.objects.Monster;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {

    private List<Monster> monsterList = new ArrayList<>();

    public MonsterFactory() {
            createMonstersFromFile();
    }

    Monster getMonsterByName(String name) {
        for (Monster monster : monsterList) {
            if (monster.toString().equals(name)) {
                return monster;
            }
        }

        return null;
    }

    private void createMonstersFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/monsters");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                monsterList.add(new Monster(
                  nextRecord[0],                        // name
                  Integer.parseInt(nextRecord[1]),      // maxHP
                  Integer.parseInt(nextRecord[2]),      // damage
                  Integer.parseInt(nextRecord[3]),      // level
                  Integer.parseInt(nextRecord[4]),      // grantedExperience
                  MonsterType.valueOf(nextRecord[5])    // MonsterType
                ));
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei monsters!");
        }
    }
}
