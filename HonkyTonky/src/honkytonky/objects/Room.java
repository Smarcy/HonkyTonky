package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Room
{
    int id;
    String name;

    private static List<Room> rooms = new ArrayList<>();

    public enum exits
    {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    public Room(String name, exits exit)
    {
        this.name = name;
        this.id = rooms.size();

        rooms.add(this);

    }

    public String getName()
    {
        return name;
    }

    public List<Room> getRooms()
    {
        return rooms;
    }
}
