package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.enumtypes.MonsterType;
import honkytonky.objects.Monster;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {

    /**
     * List that contains every Monster in the game
     */
    private List<Monster> monsterList = new ArrayList<>();
    /**
     * Needed to attach a Monster to a specific Room
     */
    private final RoomFactory roomFactory;

    /**
     * read all Monsters from CSV-File
     *
     */
    public MonsterFactory() {
        this.roomFactory = CreateWorld.getRoomFactory();
        createMonstersFromFile();
    }

    /**
     * Finds a Monster from the List of all Monsters by its name
     *
     * @param name the name of the Monster
     * @return the Monster
     */
    Monster getMonsterByName(String name) {
        for (Monster monster : monsterList) {
            if (monster.toString().equals(name)) {
                return monster;
            }
        }
        throw new IllegalArgumentException("Monster " + name + " not found!");
    }

    /**
     * Reads all contents from a CSV-File and creates a new Item from the contents on the fly
     */
    private void createMonstersFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/monsters");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Monster currMonster;
                monsterList.add(currMonster = new Monster(
                  nextRecord[0],                        // name
                  Integer.parseInt(nextRecord[1]),      // maxHP
                  Integer.parseInt(nextRecord[2]),      // damage
                  Integer.parseInt(nextRecord[3]),      // level
                  Integer.parseInt(nextRecord[4]),      // grantedExperience
                  Integer.parseInt(nextRecord[5]),      // goldDropped
                  MonsterType.valueOf(nextRecord[6])    // MonsterType
                ));
                roomFactory.getRoomByName(nextRecord[7]).addMonster(currMonster);
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei monsters!");
        }
    }

    public void wipeMonsters() {
        monsterList = new ArrayList<>();
    }
}
