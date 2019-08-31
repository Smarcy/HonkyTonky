package honkytonky.objects;

import honkytonky.enumtypes.WeaponType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Weapon extends Item {

    /**
     * Damage done by the Weapon
     */
    @XmlElement
    private int damage;
    /**
     * current durability of the Weapon
     */
    @XmlElement
    private int durability;
    /**
     * maximum durability of the Weapon
     */
    @XmlElement
    private int maxDurability;
    /**
     * true if the Weapon is Two-Handed, false if One-Handed
     */
    @XmlElement
    private boolean twoHanded; // false = onehanded, true = twohanded
    /**
     * Type of the Weapon
     */
    @XmlElement
    private WeaponType weaponType;

    /**
     * create a Weapon in the game
     *
     * @param id the id of the Weapon
     * @param name the name of the Weapon
     * @param weaponType Type of the Weapon
     * @param damage damage done by the Weapon
     * @param durability current durability of the Weapon
     * @param maxDurability maximal durability of the Weapon
     * @param value selling/buying price of the Weapon
     * @param twoHanded true if Weapon is Two-Handed
     */
    public Weapon(int id, String name, WeaponType weaponType, int damage, int durability,
      int maxDurability, int value, boolean twoHanded) {

        //@formatter:off
        super(id, name, value);
        this.weaponType     = weaponType;
        this.damage         = damage;
        this.durability     = durability;
        this.maxDurability  = maxDurability;
        this.twoHanded      = twoHanded;
        //@formatter:on
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
