package honkytonky.objects;

import honkytonky.enumtypes.MonsterType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Monster extends Actor {

    //private final int damage;
    private final int grantedExperience;
    private final int goldDropped;
    private final MonsterType monsterType;

    public Monster(String name, int maxHP, int damage, int level,
      int grantedExperience, int goldDropped, MonsterType monsterType) {
        super(name, maxHP, level, damage);
        //this.damage = damage;
        this.grantedExperience = grantedExperience;
        this.goldDropped = goldDropped;
        this.monsterType = monsterType;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
