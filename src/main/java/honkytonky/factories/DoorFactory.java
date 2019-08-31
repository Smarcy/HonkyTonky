package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.objects.Door;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DoorFactory {

    /**
     * List that contains every Door in the game
     */
    private List<Door> doors = new ArrayList<>();
    /**
     * Needed to attach a Door to a specific Room
     */
    private final RoomFactory roomFactory;

    /**
     * read all Doors from CSV-File
     *
     * @param roomFactory roomfactory instance
     */
    public DoorFactory(RoomFactory roomFactory) {
        this.roomFactory = roomFactory;
        createDoorsFromFile();
    }

    /**
     * Finds a Door from the List of all Doors by its name
     *
     * @param name the name of the Door
     * @return the Door
     */
    private Door getDoorByName(String name) {
        for (Door door : doors) {
            if (door.getName().equals(name)) {
                return door;
            }
        }
        throw new IllegalArgumentException("Door " + name + " not found!");
    }

    /**
     * Reads all contents from a CSV-File and creates a new Item from the contents on the fly
     */
    private void createDoorsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/doors");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Door currDoor;
                doors.add(currDoor = new Door(
                  Integer.parseInt(nextRecord[0]),          // id
                  nextRecord[1],                            // doorName
                  roomFactory.getRoomByName(nextRecord[2]), // sourceRoom
                  roomFactory.getRoomByName(nextRecord[3])  // targetroom
                ));
                currDoor.getSourceRoom().addDoor(getDoorByName(currDoor.getName()));
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei doors!");
        }
    }

    public void wipeDoors() {
        doors = new ArrayList<>();
    }
}
