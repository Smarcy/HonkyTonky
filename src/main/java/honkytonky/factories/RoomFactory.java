package honkytonky.factories;

import com.opencsv.CSVReader;
import honkytonky.objects.Room;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RoomFactory {

    private List<Room> rooms = new ArrayList<>();

    RoomFactory() throws IOException {
        rooms.add(new Room(1, "Bedroom", false));
        rooms.add(new Room(2, "Living Room", false));
        rooms.add(new Room(3, "Kitchen", false));
        rooms.add(new Room(4, "Storage", true));
        rooms.add(new Room(5, "Hall", false));
        rooms.add(new Room(6, "Yard", true));
        rooms.add(new Room(7, "Town Square", false));
        rooms.add(new Room(8, "Marketplace", false));
        // createRoomsFromFile();
    }

    Room getRoomByName(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    private void createRoomsFromFile() throws IOException {
        try (
          Reader reader = Files.newBufferedReader(
            Paths.get(URI.create(getClass().getResource("/rooms").toString())));
          CSVReader csvReader = new CSVReader(reader)
        ) {
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                rooms.add(new Room(
                  Integer.parseInt(nextRecord[0]),      // Room ID
                  nextRecord[1],                        // Room Name
                  Boolean.parseBoolean(nextRecord[2])));  // contains Monster
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei rooms!");
        }
    }
}
