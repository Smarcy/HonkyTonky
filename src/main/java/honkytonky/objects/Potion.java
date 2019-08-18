package honkytonky.objects;

import honkytonky.resources.PotionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Potion extends Item {

    private final int amount;
    private final PotionType type;

    public Potion(int id, String name, int amount, PotionType type) {
        super(id, name);

        this.amount = amount;
        this.type = type;
    }
}
