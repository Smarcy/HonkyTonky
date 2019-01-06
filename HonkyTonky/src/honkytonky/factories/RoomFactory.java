package honkytonky.factories;

import honkytonky.objects.Room;

enum RoomID
{
    ENTRANCE,
    HALL,
    BEDROOM,
    KITCHEN

}

public class RoomFactory
{

    public void createRooms()
    {
        new Room("Entrance", 0, 0, true, true, false, false);
        new Room("Hall", 0, 1, false, false, true, true);
        new Room("Bedroom", 0, 2, false, true, false, false);
        new Room("Kitchen", 1, 0, false, false, true, true);
    }

}
