package honkytonky.factories;

import honkytonky.enumtypes.PotionType;
import honkytonky.objects.Potion;

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