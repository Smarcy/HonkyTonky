package honkytonky.factories;

import honkytonky.objects.Potion;
import honkytonky.enumtypes.PotionType;
import java.util.ArrayList;
import java.util.List;

public class PotionFactory {

    public PotionFactory() {

    }

    public Potion startPotion() {
        return new Potion(0, "Small Health Potion", 10, PotionType.HEALTH);
    }

    public Potion createSmallHealthPotion() {
        return new Potion(0, "Small Health Potion", 10, PotionType.HEALTH);
    }
}