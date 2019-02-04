package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Merchant extends Actor {

    List<Item> itemsForsell = new ArrayList<>();

    public Merchant(String name, int maxHP, int level) {
        super(name, maxHP, level);
    }
}
