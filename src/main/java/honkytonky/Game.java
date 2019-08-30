package honkytonky;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.controller.BattleController;
import honkytonky.controller.JAXBController;
import honkytonky.controller.MerchantController;
import honkytonky.controller.PlayerController;
import honkytonky.controller.PlayerDialogController;
import honkytonky.factories.ArmorFactory;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.RoomFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.misc.Cheats;
import honkytonky.misc.CreateWorld;
import honkytonky.misc.ExpTable;
import honkytonky.objects.Armor;
import honkytonky.objects.Item;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.xml.bind.JAXBException;

public class Game {

    // @formatter:off
    private Player player                                           = null;
    private final Scanner scanner                                   = new Scanner(System.in);
    private final WeaponFactory weaponFactory                       = new WeaponFactory();
    private final ArmorFactory armorFactory                         = new ArmorFactory();
    private final PotionFactory potionFactory                       = new PotionFactory();
    private final RoomFactory roomFactory                           = new RoomFactory();
    private final PlayerDialogController playerDialogController     = new PlayerDialogController();
    private final MerchantController merchantController             = new MerchantController();
    private final BattleController battleController                 = new BattleController();
    private final PlayerController playerController                 = new PlayerController();
    // @formatter:on


    public static void main(String[] args) {
        Game game = new Game();
        game.setUpWorld();
        game.showIntro();
    }

    private void setUpWorld() {
        ExpTable.createLevels();                // create levels with gradually increasing needed Exp
        CreateWorld.populateWorld(roomFactory); // create all needed Factories / read CSV files
    }

    /**
     * Shows the menu to choose an option
     */
    private void showIntro() {

        boolean run = true;
        while (run) {
            clearScreen();

            System.out.println("Welcome to HonkyTonky!");
            System.out.println("Please choose an option:\n");
            System.out.println("1) Start Game");
            System.out.println("2) Create New Player");
            System.out.println("3) Load Game \n");
            System.out.print("\n> ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        if (player != null) {
                            clearScreen();
                            gameLoop();
                            run = false;
                            break;
                        } else {
                            System.out.println("Please create a new player first!");
                            scanner.nextLine();
                        }
                        break;
                    case 2:
                        player = playerController
                          .createPlayer(armorFactory, weaponFactory,
                            battleController, roomFactory.getRooms());

                        player.getInventory().add(potionFactory.getPotionByName("Small Health Potion")); // give Player startPotion
                        player.getPlayersPotions().put(potionFactory.getPotionByName("Small Health Potion"), 1);
                        player.setCurrentRoom(roomFactory.getRoomByName("Town Square"));        // !!!DELETEME - TESTING PURPOSES!!!
                        break;
                    case 3:
                        loadPlayer();
                        break;
                }
            } catch (NumberFormatException e) {
                showIntro();
            }
        }
    }

    /**
     * main game loop
     */
    private void gameLoop() {

        playerDialogController.setPlayer(player);

        while (true) {
            playerDialogController.printCurrentLocation();

            System.out.println("\nChoose an option:\n");
            System.out.println("1) Move");
            System.out.println("2) Use Potion");
            System.out.println("3) Character Info");
            System.out.println("4) Show Inventory");
            System.out.println("5) Save & Exit");
            if (player.getCurrentRoom().hasMerchant()) {
                System.out.println("6) Talk to " + ANSI_YELLOW + player.getCurrentRoom()
                  .getPresentMerchant() + ANSI_RESET);
            }
            System.out.print("\n> ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        playerController.move(scanner, roomFactory);
                        battleController.checkRoomForMonster();
                        battleController.checkRoomForMerchant(merchantController);
                        break;
                    case 2:
                        playerDialogController.printUsePotionDialog(playerController);
                        break;
                    case 3:
                        playerDialogController.printCharacterInfo();
                        break;
                    case 4:
                        playerDialogController.printInventoryDialog(playerController);
                        break;
                    case 5:
                        JAXBController.ObjectToXML(player);
                        System.exit(0);
                    case 6:
                        battleController.checkRoomForMerchant(merchantController);
                        break;
                    case 1337:
                        Cheats.increaseGold(player);
                        Cheats.increaseExperience(player);
                        Cheats.increaseDamage(player);
                }
            } catch (InputMismatchException | NumberFormatException | JAXBException e) {
                clearScreen();
                gameLoop();
            }
            clearScreen();
        }
    }

    private void loadPlayer() {

        Player dummy = null;
        try {
            dummy = JAXBController.unmarshall();
        } catch (JAXBException e) {
            System.out.println("Something went wrong while loading your Character!");
            scanner.nextLine();
            return;
        } catch (IOException e) {
            System.out.println("Could not find savegame!");
            scanner.nextLine();
            return;
        }

            // Extract real Items from "dummys"
            Weapon dummyWeapon = weaponFactory.getWeaponByName(dummy.getWeapon().getName());
            Armor dummyArmor = armorFactory.getArmorByName(dummy.getArmor().getName());
            Room dummyRoom = roomFactory.getRoomByName(dummy.getCurrentRoom().getName());

            player = new Player(dummy.getName(), dummy.getMaxHP(), dummyWeapon, dummyArmor, dummyRoom);

            player.getCurrentRoom().setHasLivingMonster(dummy.getCurrentRoom().hasLivingMonster());
            player.setHp(dummy.getHp());
            player.setExperience(dummy.getExperience());
            player.setLevel(dummy.getLevel());
            player.setGold(dummy.getGold());
            player.setDamage(dummy.getDamage());

            // Retrieve Potions
            int i = 0;
            for (Item item : dummy.getInventory()) {
                if (item instanceof Potion) {
                    Potion dummyPotion = potionFactory.getPotionByName(item.getName());
                    Integer potionValue = dummy.getPlayersPotions().get(dummy.getInventory().get(i));
                    player.getInventory().add(dummyPotion);
                    player.getPlayersPotions().put(dummyPotion, potionValue);
                } else if (!(item.equals(dummy.getArmor())) && !(item.equals(dummy.getWeapon()))) {
                    player.getInventory().add(item);
                }
                i++;
            }

            playerController.setPlayer(player);
            battleController.setPlayer(player);
            clearScreen();
            gameLoop();
    }
}
