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

    /**
     * creates a Monster in the game
     *
     * @param name name of the Monster
     * @param maxHP maximal Health Points of the Monster
     * @param damage damage done by the Monster
     * @param level level of the Monster
     * @param grantedExperience amount of experience earned for killing the Monster
     * @param goldDropped amount of gold earned for killing the Monster
     * @param monsterType Type of the Monster
     */
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
