package honkytonky.objects;

import honkytonky.enumtypes.PotionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Potion extends Item {

    private final int amount;
    private final PotionType type;

    public Potion(int id, String name, int amount, int value, PotionType type) {
        super(id, name, value);

        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
