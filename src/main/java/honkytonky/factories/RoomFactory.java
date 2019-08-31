package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.objects.Room;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RoomFactory")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomFactory {

    /**
     * List that contains every Room in the game
     */
    @XmlElementWrapper(name = "rooms")
    @XmlElement(name = "room")
    private List<Room> rooms = new ArrayList<>();

    /**
     * read all Rooms from CSV-File
     */
    public RoomFactory() {
        createRoomsFromFile();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Finds a Room from the List of all Rooms by its name
     *
     * @param name the name of the Room
     * @return the Room
     */
    public Room getRoomByName(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        throw new IllegalArgumentException("Room " + name + " not found!");
    }

    /**
     * Reads all contents from a CSV-File and creates a new Item from the contents on the fly
     */
    private void createRoomsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/rooms");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                rooms.add(new Room(
                  Integer.parseInt(nextRecord[0]),      // Room ID
                  nextRecord[1]));                      // Room Name
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei rooms!");
        }
    }

    public void wipeRooms() {
        rooms = new ArrayList<>();
    }
}
