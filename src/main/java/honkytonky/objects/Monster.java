package honkytonky.objects;

import honkytonky.resources.MonsterType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Monster extends Actor {

    private int damage;
    private int grantedExperience;
    private MonsterType monsterType;

    public Monster(String name, int maxHP, int damage, int level,
      int grantedExperience, MonsterType monsterType) {
        super(name, maxHP, level);
        this.damage = damage;
        this.grantedExperience = grantedExperience;
        this.monsterType = monsterType;
    }
}
