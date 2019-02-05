package honkytonky.factories;

import honkytonky.objects.Merchant;
import java.util.ArrayList;
import java.util.List;

class MerchantFactory {


    PotionFactory potionFactory = new PotionFactory();
    List<Merchant> merchants = new ArrayList<>();

    MerchantFactory() {

        merchants.add(new Merchant("Belechor", 50, 3));
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
