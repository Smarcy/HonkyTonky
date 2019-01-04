package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

abstract class Actor
{

    private final int id;
    private int hp;
    private int maxHP;
    private final String name;

    private List<Actor> actors = new ArrayList<>();

    Actor(String name, int maxHP)
    {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = maxHP;

        this.id = actors.size();

        actors.add(this);
    }
}
