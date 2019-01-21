package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int id;
    private final String name;
    private final int x, y;
    private final boolean hasLivingMonster;
    private final List<Door> doors = new ArrayList<>();
    private Monster presentMonster;

    public Room(int id, String name, int x, int y, boolean hasMonster) {

        //@formatter:off
        this.name   = name;
        this.id     = id;
        this.x      = x;
        this.y      = y;
        this.hasLivingMonster = hasMonster;
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

    public void addMonster(Monster monster)
    {
        this.presentMonster = monster;
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

    public boolean hasLivingMonster()
    {
        return this.hasLivingMonster;
    }

    public String getName()
    {
        return name;
    }

}
