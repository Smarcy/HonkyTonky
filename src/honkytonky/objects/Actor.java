package honkytonky.objects;

import honkytonky.factories.RoomFactory;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor {

    private final int id;
    private final String name;
    private final RoomFactory roomFactory = new RoomFactory();
    private final Room[][] roomList = roomFactory.createRooms();
    private int hp;
    private int maxHP;
    private int x, y;
    private int level = 1;
    private Room currentRoom;
    private List<Actor> actors = new ArrayList<>();

    Actor(String name, int maxHP, int x, int y, int level) {

        //@formatter:off
        this.name   = name;
        this.maxHP  = maxHP;
        this.hp     = maxHP;
        this.x      = x;
        this.y      = y;
        this.level  = level;
        this.id     = actors.size();
        //@formatter:on

        actors.add(this);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getID() {
        return id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public Room getCurrentRoom() {
        return roomList[this.x][this.y];
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
