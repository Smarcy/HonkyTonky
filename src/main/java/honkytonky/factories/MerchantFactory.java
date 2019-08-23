package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.enumtypes.PotionType;
import honkytonky.objects.Merchant;
import honkytonky.objects.Potion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MerchantFactory {


    private final List<Merchant> merchants = new ArrayList<>();
    private final RoomFactory roomFactory;

    public MerchantFactory(RoomFactory roomFactory) {
        this.roomFactory = roomFactory;
        createMerchantsFromFile();
        addItemsToMerchantCRAPPYSTATIC();
    }

    public Merchant getMerchantByName(String name) {
        for (Merchant merchant : merchants) {
            if (merchant.toString().equals(name)) {
                return merchant;
            }
        }
        return null;
    }

    private void createMerchantsFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/merchants");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Merchant currMerchant;
                merchants.add(currMerchant = new Merchant(
                  nextRecord[0],                    // name
                  Integer.parseInt(nextRecord[1]),  // maxHP
                  Integer.parseInt(nextRecord[2]),  // level
                  nextRecord[4]                     // smalltalk
                ));
                roomFactory.getRoomByName(nextRecord[3]).addMerchant(currMerchant);
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Datei merchants!");
        }
    }

    private void addItemsToMerchantCRAPPYSTATIC() {
        getMerchantByName("Belechor").addItemToShop(new Potion(1, "Small Health Potion", 10, 15, PotionType.HEALTH));
    }
}
