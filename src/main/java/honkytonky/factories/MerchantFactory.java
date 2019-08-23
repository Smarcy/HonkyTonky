package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.objects.Merchant;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MerchantFactory {


    private final PotionFactory potionFactory = new PotionFactory();
    private final List<Merchant> merchants = new ArrayList<>();
    private final RoomFactory roomFactory;
    private Merchant currMerchant;

    public MerchantFactory(RoomFactory roomFactory) {
        this.roomFactory = roomFactory;
        createMerchantsFromFile();
    }

    Merchant getMerchantByName(String name) {
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
                merchants.add(currMerchant = new Merchant(
                  nextRecord[0],                    // name
                  Integer.parseInt(nextRecord[1]),  // maxHP
                  Integer.parseInt(nextRecord[2]),  // level
                  nextRecord[4]                     // smalltalk
                ));
                roomFactory.getRoomByName(nextRecord[3]).addMerchant(currMerchant);
            }
        } catch (Exception IOException) {
            System.err.println("Fehler beim Lesen der Datei doors!");
        }
    }
}
