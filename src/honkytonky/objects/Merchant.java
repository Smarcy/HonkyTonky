package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Merchant extends Actor {

    List<Item> itemsForSell = new ArrayList<>();

    public Merchant(String name, int maxHP, int level) {
        super(name, maxHP, level);
    }

    public void addItemToShop(Item item) {
        this.itemsForSell.add(item);
    }

    public void removeItemFromShop(int id) {
        this.itemsForSell.remove(itemsForSell.get(id));
    }

}
