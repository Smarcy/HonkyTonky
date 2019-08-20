package honkytonky.factories;

import com.opencsv.CSVReader;
import honkytonky.objects.Room;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RoomFactory {

    private List<Room> rooms = new ArrayList<>();

    RoomFactory() {
        createRoomsFromFile();
    }

    Room getRoomByName(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    private void createRoomsFromFile() {
        try (
            Reader reader = Files.newBufferedReader(Paths.get("data/rooms"));
            CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null) {
                rooms.add(new Room(
                  Integer.parseInt(nextRecord[0]),      // Room ID
                  nextRecord[1],                        // Room Name
                  Boolean.getBoolean(nextRecord[2])));  // contains Monster
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei rooms!");
        }

    }
}
