package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor
{

    private final int id;
    private final String name;
    private int hp;
    private int maxHP;
    private int x, y;

    private List<Actor> actors = new ArrayList<>();

    public Actor(String name, int maxHP, int x, int y)
    {
        this.name   = name;
        this.maxHP  = maxHP;
        this.hp     = maxHP;
        this.x      = x;
        this.y      = y;
        this.id     = actors.size();

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

    public int getID()
    {
        return id;
    }

    public int getHp()
    {
        return hp;
    }

    public int getMaxHP()
    {
        return maxHP;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
