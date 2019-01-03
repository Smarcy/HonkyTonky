package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

abstract class Actor
{
    private int id;
    private int hp;
    private int mana;
    private String name;

    private List<Actor> actors = new ArrayList<Actor>();

    Actor(String name, int hp, int mana)
    {
        this.name   = name;
        this.hp     = hp;
        this.mana   = mana;

        actors.add(this);

        this.id     = actors.size();
    }

    public String getName()
    {
        return name;
    }
}
