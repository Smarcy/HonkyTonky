package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Actor {

    /**
     * List that contains all Actors in the game
     */
    private static List<Actor> actors = new ArrayList<>();
    /**
     * The ID of the Actor
     */
    @XmlElement
    private final int id;
    /**
     * The name of the Actor
     */
    @XmlElement
    private final String name;
    /**
     * Current HP of the Actor
     */
    @XmlElement
    private int hp;
    /**
     * Maximum HP of the Actor
     */
    @XmlElement
    private int maxHP;
    /**
     * Level of the Actor
     */
    @XmlElement
    private int level;  //default = 1
    /**
     * Damage the Actor does
     */
    @XmlElement
    private int damage;

    /**
     * default contructor - create a new Actor
     *
     * @param name name of the Actor
     * @param maxHP maximum HP of the actor
     * @param level level of the Actor
     * @param damage damage done by the Actor
     */
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
