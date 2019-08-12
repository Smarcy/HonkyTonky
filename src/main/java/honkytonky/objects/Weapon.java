package honkytonky.objects;

import honkytonky.resources.WeaponType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Weapon extends Item {

    private int damage;
    private int durability;
    private int maxDurability;
    private boolean twoHanded; // false = onehanded, true = twohanded
    private WeaponType weaponType;

    public Weapon(int id, String name, WeaponType weaponType, int damage, int durability,
      int maxDurability, boolean twoHanded) {

        //@formatter:off
        super(id, name);
        this.weaponType     = weaponType;
        this.damage         = damage;
        this.durability     = durability;
        this.maxDurability  = maxDurability;
        this.twoHanded      = twoHanded;
        //@formatter:on

    }
}
