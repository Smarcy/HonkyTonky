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

    @XmlElement
    private final int id;

    @XmlElement
    private final String name;

    @XmlElement
    private int hp;

    @XmlElement
    private int maxHP;

    @XmlElement
    private int level;  //default = 1

    @XmlElement
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
