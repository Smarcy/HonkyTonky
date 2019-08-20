package honkytonky.objects;

import honkytonky.enumtypes.ArmorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Armor extends Item {

    private final int armorPoints;
    private final int durability;
    private final int maxDurability;
    private final ArmorType armorType;

    public Armor(int id, String name, int armorPoints, int durability, int maxDurability, ArmorType armorType) {
        //@formatter:off
        super(id, name);
        this.armorPoints    = armorPoints;
        this.durability     = durability;
        this.maxDurability  = maxDurability;
        this.armorType      = armorType;
        //@formatter:on
    }
}
