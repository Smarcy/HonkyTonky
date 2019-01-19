package honkytonky.factories;

import honkytonky.objects.Door;
import java.util.ArrayList;
import java.util.List;

public class DoorFactory {

    private List<Door> doors = new ArrayList<>();
    private RoomFactory roomFactory = new RoomFactory();

    DoorFactory()
    {
        doors.add(new Door(0, "bedroom", roomFactory.getRoomByName("Bedroom")));
        doors.add(new Door(1, "living room",  roomFactory.getRoomByName("Living Room")));
        doors.add(new Door(2, "kitchen", roomFactory.getRoomByName("Kitchen")));
        doors.add(new Door(3, "storage", roomFactory.getRoomByName("Storage")));
        doors.add(new Door(4, "hall", roomFactory.getRoomByName("Hall")));

    }

    Door getDoorById(int id)
    {
        return doors.get(id);
    }

    Door getDoorByName(String name)
    {
        for(Door door : doors)
        {
            if(door.getName().equals(name))
            {
                return door;
            }
        }

        return null;
    }
}
