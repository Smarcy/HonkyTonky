package honkytonky.objects;

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

    public int getDamage() {
        return damage;
    }

    public int getGrantedExperience() {
        return this.grantedExperience;
    }

    public enum MonsterType {
        ZOMBIE,
        BUTTERFLY
    }
}
