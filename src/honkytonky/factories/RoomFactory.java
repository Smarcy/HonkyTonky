package honkytonky.factories;

import honkytonky.objects.Room;
import java.util.ArrayList;
import java.util.List;

public class RoomFactory
{

    private List<Room> rooms = new ArrayList<>();

    RoomFactory()
    {
        rooms.add(new Room(1, "Bedroom",        false));
        rooms.add(new Room(2, "Living Room",    false));
        rooms.add(new Room(3, "Kitchen",        false));
        rooms.add(new Room(4, "Storage",        true));
        rooms.add(new Room(5, "Hall",           false));
        rooms.add(new Room(6, "Yard",           true));
        rooms.add(new Room(7, "Town Square",    false));
        rooms.add(new Room(8, "Marketplace",    false));
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
