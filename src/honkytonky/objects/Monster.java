package honkytonky.objects;

public class Monster extends Actor
{
    public enum MonsterType
    {
        ZOMBIE,
        BUTTERFLY
    }

    private int damage;
    private int grantedExperience;
    private MonsterType monsterType;

    public Monster(String name, int maxHP, int x, int y, int damage, int level, int grantedExperience, MonsterType monsterType)
    {
        super(name, maxHP, x, y, level);
        this.damage             = damage;
        this.grantedExperience  = grantedExperience;
        this.monsterType        = monsterType;
    }

    public int getDamage()
    {
        return damage;
    }

    public int getGrantedExperience()
    {
        return this.grantedExperience;
    }
}
