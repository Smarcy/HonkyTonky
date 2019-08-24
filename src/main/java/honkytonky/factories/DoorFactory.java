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

    private final List<Door> doors = new ArrayList<>();
    private final RoomFactory roomFactory;
    private Door currDoor;

    public DoorFactory(RoomFactory roomFactory) {
        this.roomFactory = roomFactory;
        createDoorsFromFile();
    }

    private Door getDoorByName(String name) {
        for (Door door : doors) {
            if (door.getName().equals(name)) {
                return door;
            }
        }
        throw new IllegalArgumentException("Door " + name + " not found!");

    }

    private void createDoorsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/doors");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
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
}
