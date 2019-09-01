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

    /**
     * List that contains every Merchant in the game
     */
    private List<Merchant> merchants = new ArrayList<>();
    /**
     * Instance of RoomFactory to attach a Merchant to a specific Room
     */
    private final RoomFactory roomFactory;

    /**
     * read all Merchants from CSV-File
     *
     */
    public MerchantFactory() {
        this.roomFactory = CreateWorld.getRoomFactory();
        createMerchantsFromFile();
        addItemsFromFileToMerchant();
    }

    /**
     * Finds a Merchant from the List of all Merchants by its name
     *
     * @param name the name of the Merchant
     * @return the Merchant
     */
    public Merchant getMerchantByName(String name) {
        for (Merchant merchant : merchants) {
            if (merchant.toString().equals(name)) {
                return merchant;
            }
        }
        throw new IllegalArgumentException("Merchant " + name + " not found!");
    }

    /**
     * Reads all contents from a CSV-File and creates a new Item from the contents on the fly
     */
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
                  Integer.parseInt(nextRecord[4]),  // grantedExp
                  Integer.parseInt(nextRecord[5]),   // grantedGold
                  nextRecord[7]                     // smalltalk
                ));
                roomFactory.getRoomByName(nextRecord[6]).addMerchant(currMerchant);
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Datei merchants!");
        }
    }

    /**
     * Reads all contents from a CSV-File and adds the items to the corresponding merchant
     */
    private void addItemsFromFileToMerchant() {
        try (InputStream inputStream = getClass().getResourceAsStream("/merchantitems");
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
          BufferedReader reader = new BufferedReader(inputStreamReader)) {
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Merchant currMerchant = getMerchantByName(nextRecord[0]);
                String itemToGive = nextRecord[1];

                switch (nextRecord[2]) {
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

    public void wipeMerchants() {
        merchants = new ArrayList<>();
    }
}
