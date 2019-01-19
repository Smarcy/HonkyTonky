package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private static Room[][] rooms = new Room[10][10];
    private final int id;
    private final String name;
    private final int x, y;
    private List<Door> doors = new ArrayList<>();

    public Room(int id, String name, int x, int y) {

        //@formatter:off
        this.name   = name;
        this.id     = id;
        this.x      = x;
        this.y      = y;

        rooms[x][y] = this;
        //@formatter:on

    }

    @Override
    public String toString() {
        return this.name;
    }

    public void addDoor(Door door)
    {
        doors.add(door);
    }

    public String getName()
    {
        return name;
    }
}
