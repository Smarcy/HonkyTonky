package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor {

    private final int id;
    private final String name;
    private int hp;
    private int maxHP;
    private int level;  //default = 1
    private List<Actor> actors = new ArrayList<>();

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
