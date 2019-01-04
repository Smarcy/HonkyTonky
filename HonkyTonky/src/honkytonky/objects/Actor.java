package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

abstract class Actor
{
    private int id;
    private int hp;
    private int maxHP;
    private String name;

    public List<Actor> actors = new ArrayList<Actor>();

    Actor(String name, int maxHP)
    {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = maxHP;

        this.id = actors.size();

        actors.add(this);
    }
}
