package honkytonky.factories;

import honkytonky.objects.Room;
import java.util.Arrays;

public class RoomFactory
{

    private Room[][] roomList = new Room[10][10];

    public RoomFactory()
    {

    }

    public Room[][] createRooms()
    {
        roomList[0][0] = new Room("Entrance", 0, 0, true, true, false, false);
        roomList[0][1] = new Room("Hall", 0, 1, false, false, true, true);
        roomList[0][2] = new Room("Bedroom", 0, 2, false, true, false, false);
        roomList[1][0] = new Room("Kitchen", 1, 0, false, false, true, true);

        return roomList;
    }

    public String toString(int x, int y)
    {
        return roomList[x][y].getName();
    }
}
