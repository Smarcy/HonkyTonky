package honkytonky.objects;

public class Weapon extends Item
{
    enum WeaponType
    {
        SWORD,
        AXE,
        HAMMER
    }

    private int damage;
    private int durability;
    private boolean twohanded; // false = onehanded, true = twohanded
    private WeaponType weaponType;

    public Weapon(int id, String name, WeaponType weaponType, int damage, int durability, boolean twohanded)
    {
        super(id, name);

        this.weaponType = weaponType;
        this.damage = damage;
        this.durability = durability;
        this.twohanded = twohanded;

    }
}
