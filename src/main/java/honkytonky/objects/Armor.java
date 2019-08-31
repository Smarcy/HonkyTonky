package honkytonky.objects;

import honkytonky.enumtypes.ArmorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Armor extends Item {

    /**
     * Damage absorption of the Armor
     */
    @XmlElement
    private final int armorPoints;
    /**
     * current durability of the Armor
     */
    @XmlElement
    private int durability;
    /**
     * maximum durability of the Armor
     */
    @XmlElement
    private final int maxDurability;
    /**
     * Type of the Armor
     */
    @XmlElement
    private final ArmorType armorType;

    //Needed for JAXB
    public Armor() {
        super();
        armorPoints = 0;
        maxDurability = 0;
        armorType = null;
    }

    /**
     * create an Armor
     *
     * @param id id of the Armor Object
     * @param name name of the Armor
     * @param armorPoints how much the Armor absorbs
     * @param durability current durability of the Armor
     * @param maxDurability maximal durability of the Armor
     * @param value selling/buying price of the Armor
     * @param armorType Type of the Armor
     */
    public Armor(int id, String name, int armorPoints, int durability, int maxDurability, int value, ArmorType armorType) {
        //@formatter:off
        super(id, name, value);
        this.armorPoints    = armorPoints;
        this.durability     = durability;
        this.maxDurability  = maxDurability;
        this.armorType      = armorType;
        //@formatter:on
    }



    @Override
    public String toString() {
        return this.getName();
    }
}
