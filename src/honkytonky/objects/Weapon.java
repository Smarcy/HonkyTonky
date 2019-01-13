package honkytonky.objects;

public class Weapon extends Item
{

    public enum WeaponType
    {
        SWORD,
        AXE,
        HAMMER
    }

    private int damage;
    private int durability;
    private int maxDurability;
    private boolean twoHanded; // false = onehanded, true = twohanded
    private WeaponType weaponType;

    public Weapon(int id, String name, WeaponType weaponType, int damage, int durability, int maxDurability, boolean twoHanded)
    {
        super(id, name);

        this.weaponType = weaponType;
        this.damage = damage;
        this.durability = durability;
        this.maxDurability = maxDurability;
        this.twoHanded = twoHanded;
    }

    @Override
    public String toString()
    {
        return this.getName();
    }

    public int getDamage()
    {
        return damage;
    }
}
