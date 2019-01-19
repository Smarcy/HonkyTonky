package honkytonky.factories;

import honkytonky.objects.Room;

public class RoomFactory
{

    private Room[][] roomList = new Room[10][10];

    public RoomFactory()
    {

    }

    public Room[][] createRooms()
    {
        roomList[0][0] = new Room("Bedroom",     0, 0, true, false, false, false);
        roomList[0][1] = new Room("Living Room", 0, 1, true, true, true, false);
        roomList[0][2] = new Room("Hall",        0, 2, true, false, true, false);
        roomList[0][3] = new Room("Entrance",    0, 3, false, false, true, false);
        roomList[1][1] = new Room("Kitchen",     1, 1, true, false, false, true);
        roomList[1][2] = new Room("Storage",     1, 2, false, false, true, false);

        return roomList;
    }
}
