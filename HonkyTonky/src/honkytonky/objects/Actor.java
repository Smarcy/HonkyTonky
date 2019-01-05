package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

abstract class Actor
{

    private final int id;
    private int hp;
    private int maxHP;
    private int x, y;
    private final String name;

    private List<Actor> actors = new ArrayList<>();

    Actor(String name, int maxHP, int x, int y)
    {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.x = x;
        this.y = y;

        this.id = actors.size();

        actors.add(this);
    }

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

}
