package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Room
{
    private int id;
    private String name;
    private final boolean north, east, south, west;

    private static List<Room> rooms = new ArrayList<>();

    public Room(String name, int north, int east, int south, int west)
    {
        this.name = name;
        this.id = rooms.size();

        this.north  = north     == 1;
        this.east   = east      == 1;
        this.south  = south     == 1;
        this.west   = west      == 1;

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
