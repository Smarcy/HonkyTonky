package honkytonky.factories;

import honkytonky.objects.Potion;
import honkytonky.objects.Potion.PotionType;
import java.util.ArrayList;
import java.util.List;

public class PotionFactory {

    private List<Potion> potionList = new ArrayList<>();

    PotionFactory() {

        potionList.add(new Potion(0, "Health Potion", 10, PotionType.HEALTH));

    }

}
