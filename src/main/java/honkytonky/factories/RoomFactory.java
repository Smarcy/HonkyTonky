package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.objects.Room;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        try (InputStream inputStream = getClass().getResourceAsStream("/rooms");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                rooms.add(new Room(
                  Integer.parseInt(nextRecord[0]),      // Room ID
                  nextRecord[1]));                        // Room Name
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei rooms!");
        }
    }
}
