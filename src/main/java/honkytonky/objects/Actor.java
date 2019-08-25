package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public abstract class Actor {

    private final int id;
    private final String name;
    private int hp;
    private int maxHP;
    private int level;  //default = 1
    private int damage;
    private static List<Actor> actors = new ArrayList<>();

    Actor(String name, int maxHP, int level, int damage) {

        //@formatter:off
        this.name   = name;
        this.maxHP  = maxHP;
        this.hp     = maxHP;
        this.level  = level;
        this.damage = damage;
        this.id     = actors.size();
        //@formatter:on

        actors.add(this);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
