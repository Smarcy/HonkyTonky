package honkytonky.factories;

import honkytonky.objects.Room;

public class MapLayout {

    private RoomFactory roomFactory = new RoomFactory();
    private DoorFactory doorFactory = new DoorFactory();

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
    }

}
