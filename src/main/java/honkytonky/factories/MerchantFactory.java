package honkytonky.factories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import honkytonky.objects.Merchant;
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
        throw new IllegalArgumentException("Merchant " + name + " not found!");
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
                  Integer.parseInt(nextRecord[3]),  // damage
                  nextRecord[5]                     // smalltalk
                ));
                roomFactory.getRoomByName(nextRecord[4]).addMerchant(currMerchant);
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Datei merchants!");
        }
    }

    private void addItemsToMerchantCRAPPYSTATIC() {
        try (InputStream inputStream = getClass().getResourceAsStream("/merchantitems");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Merchant currMerchant = getMerchantByName(nextRecord[0]);
                String itemToGive = nextRecord[1];

                switch(nextRecord[2]) {
                    case "Potion":
                        currMerchant.addItemToShop(new PotionFactory().getPotionByName(itemToGive));
                        break;
                    case "Weapon":
                        currMerchant.addItemToShop(new WeaponFactory().getWeaponByName(itemToGive));
                        break;
                    case "Armor":
                        currMerchant.addItemToShop(new ArmorFactory().getArmorByName(itemToGive));
                        break;
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Datei merchantitems!");
        }
    }
}
