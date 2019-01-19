package honkytonky.factories;

import honkytonky.objects.Room;
import java.util.ArrayList;
import java.util.List;

public class MapLayout {

    private RoomFactory roomFactory = new RoomFactory();
    private DoorFactory doorFactory = new DoorFactory();

    private List<Room> rooms = new ArrayList<>();

    public MapLayout()
    {
        Room bedroom = roomFactory.getRoomByName("Bedroom");
        bedroom.addDoor(doorFactory.getDoorByName("living room"));

        Room livingRoom = roomFactory.getRoomByName("Living Room");
        livingRoom.addDoor(doorFactory.getDoorByName("bedroom"));
        livingRoom.addDoor(doorFactory.getDoorByName("kitchen"));
        livingRoom.addDoor(doorFactory.getDoorByName("hall"));

        Room kitchen = roomFactory.getRoomByName("Kitchen");
        kitchen.addDoor(doorFactory.getDoorByName("living room"));
        kitchen.addDoor(doorFactory.getDoorByName("storage"));

        Room storage = roomFactory.getRoomByName("Storage");
        storage.addDoor(doorFactory.getDoorByName("kitchen"));

        Room hall = roomFactory.getRoomByName("Hall");
        hall.addDoor(doorFactory.getDoorByName("living room"));

        rooms.add(bedroom);
        rooms.add(livingRoom);
        rooms.add(kitchen);
        rooms.add(storage);
        rooms.add(hall);
    }

    public List<Room> getRooms()
    {
        return rooms;
    }

}
