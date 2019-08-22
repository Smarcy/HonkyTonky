package honkytonky.factories;

import honkytonky.objects.Merchant;
import java.util.ArrayList;
import java.util.List;

class MerchantFactory {


    private final PotionFactory potionFactory = new PotionFactory();
    private final List<Merchant> merchants = new ArrayList<>();

    MerchantFactory() {

        merchants.add(new Merchant("Belechor", 50, 3,
          "I used to be a powerful magician, but once my hair fell out I stopped being powerful."));
        merchants.get(0).addItemToShop(potionFactory.createSmallHealthPotion());
    }

    Merchant getMerchantByName(String name) {
        for (Merchant merchant : merchants) {
            if (merchant.toString().equals(name)) {
                return merchant;
            }
        }
        return null;
    }
}
