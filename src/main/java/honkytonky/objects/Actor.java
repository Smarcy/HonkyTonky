package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
abstract class Actor {

    private final int id;
    private final String name;
    private int hp;
    private int maxHP;
    private int level;  //default = 1
    private static List<Actor> actors = new ArrayList<>();

    Actor(String name, int maxHP, int level) {

        //@formatter:off
        this.name   = name;
        this.maxHP  = maxHP;
        this.hp     = maxHP;
        this.level  = level;
        this.id     = actors.size();
        //@formatter:on

        actors.add(this);
    }
}
