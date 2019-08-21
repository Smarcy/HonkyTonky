package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.objects.Door;
import honkytonky.objects.Room;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class DoorFactory {

    private List<Door> doors = new ArrayList<>();
    private RoomFactory roomFactory = new RoomFactory();

    DoorFactory() {
        createDoorsFromFile();
    }

    Door getDoorByName(String name) {
        for (Door door : doors) {
            if (door.getName().equals(name)) {
                return door;
            }
        }

        return null;
    }

    private void createDoorsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/doors");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                doors.add(new Door(
                  Integer.parseInt(nextRecord[0]),          // id
                  nextRecord[1],                            // doorName
                  roomFactory.getRoomByName(nextRecord[2])  // targetroom
                ));
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei doors!");
        }
    }
}
