package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Room
{

    private final int id;
    private final String name;
    private final int x, y;
    private final boolean north, east, south, west;

    private static Room[][] rooms = new Room[10][10];

    public Room(String name, int x, int y, boolean north, boolean east, boolean south, boolean west)
    {
        this.name   = name;
        this.id     = rooms.length;
        this.x      = x;
        this.y      = y;

        this.north  = north;
        this.east   = east;
        this.south  = south;
        this.west   = west;

        rooms[x][y] = this;
    }

    public Room[][] getRooms()
    {
        return rooms;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public int[] getCurrentRoom()
    {
        int[] coords = {this.x, this.y};
        return coords;
    }
}
