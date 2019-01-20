package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Room {

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

    public List<Door> getDoors()
    {
        return doors;
    }

    public void listDoorOptions()
    {
        System.out.println("Where would you like to go?\n");
        System.out.println("There are doors to the following rooms:\n");

        int i = 1;

        for(Door door : doors)
        {
            System.out.println(i + ") " + door.getTargetRoom());
            i++;
        }
    }

    public String getName()
    {
        return name;
    }

}
