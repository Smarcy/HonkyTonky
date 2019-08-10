package honkytonky.factories;

import honkytonky.objects.Door;
import java.util.ArrayList;
import java.util.List;

class DoorFactory {

    private List<Door> doors = new ArrayList<>();
    private RoomFactory roomFactory = new RoomFactory();

    DoorFactory() {
        doors.add(new Door(1, "Bedroom", roomFactory.getRoomByName("Bedroom")));
        doors.add(new Door(2, "Living Room", roomFactory.getRoomByName("Living Room")));
        doors.add(new Door(3, "Kitchen", roomFactory.getRoomByName("Kitchen")));
        doors.add(new Door(4, "Storage", roomFactory.getRoomByName("Storage")));
        doors.add(new Door(5, "Hall", roomFactory.getRoomByName("Hall")));
        doors.add(new Door(6, "Yard", roomFactory.getRoomByName("Yard")));
        doors.add(new Door(7, "Town Square", roomFactory.getRoomByName("Town Square")));
        doors.add(new Door(8, "Marketplace", roomFactory.getRoomByName("Marketplace")));
    }

    Door getDoorByName(String name) {
        for (Door door : doors) {
            if (door.getName().equals(name)) {
                return door;
            }
        }

        return null;
    }
}
