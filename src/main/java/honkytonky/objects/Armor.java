package honkytonky.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Armor extends Item {

    private final int armorPoints;
    private int durability;
    private int maxDurability;

    public Armor(int id, String name, int armorPoints, int durability, int maxDurability) {
        //@formatter:off
        super(id, name);
        this.armorPoints    = armorPoints;
        this.durability     = durability;
        this.maxDurability  = maxDurability;
        //@formatter:on
    }
}
