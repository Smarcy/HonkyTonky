package honkytonky.objects;

import honkytonky.enumtypes.ArmorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Armor extends Item {

    @XmlTransient
    private final int armorPoints;

    @XmlElement
    private final int durability;

    @XmlTransient
    private final int maxDurability;

    @XmlTransient
    private final ArmorType armorType;

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
