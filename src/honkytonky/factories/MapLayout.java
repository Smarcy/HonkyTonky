package honkytonky.factories;

import honkytonky.objects.Room;
import java.util.ArrayList;
import java.util.List;

public class MapLayout {

    private RoomFactory roomFactory = new RoomFactory();
    private DoorFactory doorFactory = new DoorFactory();
    private MonsterFactory monsterFactory = new MonsterFactory();

    private List<Room> rooms = new ArrayList<>();

    public MapLayout()
    {
        Room bedroom = roomFactory.getRoomByName("Bedroom");
        bedroom.addDoor(doorFactory.getDoorByName("Living Room"));

        Room livingRoom = roomFactory.getRoomByName("Living Room");
        livingRoom.addDoor(doorFactory.getDoorByName("Bedroom"));
        livingRoom.addDoor(doorFactory.getDoorByName("Kitchen"));
        livingRoom.addDoor(doorFactory.getDoorByName("Hall"));

        Room kitchen = roomFactory.getRoomByName("Kitchen");
        kitchen.addDoor(doorFactory.getDoorByName("Living Room"));
        kitchen.addDoor(doorFactory.getDoorByName("Storage"));

        Room storage = roomFactory.getRoomByName("Storage");
        storage.addDoor(doorFactory.getDoorByName("Kitchen"));

        storage.addMonster(monsterFactory.getMonsterbyName("Peanut Butter Zombie"));

        Room hall = roomFactory.getRoomByName("Hall");
        hall.addDoor(doorFactory.getDoorByName("Living Room"));

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

    public Room getRoomByName(String name)
    {
        return roomFactory.getRoomByName(name);
    }
}
