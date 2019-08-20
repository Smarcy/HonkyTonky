package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.enumtypes.MonsterType;
import honkytonky.objects.Monster;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {

    private List<Monster> monsterList = new ArrayList<>();

    public MonsterFactory() {
 //       monsterList.add(new Monster("Peanut Butter Zombie", 15, 1, 1, 70, ZOMBIE));
//        monsterList.add(new Monster("Butterfly", 20, 2, 2, 100, BUTTERFLY));
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
        try (
          Reader reader = Files.newBufferedReader(
            Paths.get("src/main/resources/monsters"));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null) {
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
