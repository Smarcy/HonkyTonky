package honkytonky.factories;

import honkytonky.objects.Room;
import java.util.ArrayList;
import java.util.List;

public class RoomFactory
{

    private List<Room> rooms = new ArrayList<>();

    RoomFactory()
    {
        rooms.add(new Room(0, "Bedroom", 0, 0));
        rooms.add(new Room(1, "Living Room", 0, 1));
        rooms.add(new Room(2, "Kitchen", 1, 1));
        rooms.add(new Room(3, "Storage", 1, 2));
        rooms.add(new Room(4, "Hall", 0, 2));
    }

    public Room getRoomByID(int id)
    {
        return rooms.get(id);
    }

    Room getRoomByName(String name)
    {
        for(Room room : rooms)
        {
            if(room.getName().equals(name))
            {
                return room;
            }
        }

        return null;
    }
}
