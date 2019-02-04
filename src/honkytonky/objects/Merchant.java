package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Merchant extends Actor {

    List<Item> itemsForSell = new ArrayList<>();

    public Merchant(String name, int maxHP, int level, List<Item> itemsForSell) {
        super(name, maxHP, level);

        this.itemsForSell = itemsForSell;
    }

    public void addItemToShop(Item item) {
        this.itemsForSell.add(item);
    }

    public void removeItemFromShop(int id) {
        this.itemsForSell.remove(itemsForSell.get(id));
    }

}
