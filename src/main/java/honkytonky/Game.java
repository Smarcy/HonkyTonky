package honkytonky;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.controller.BattleController;
import honkytonky.controller.JAXBController;
import honkytonky.controller.MerchantController;
import honkytonky.controller.PlayerController;
import honkytonky.controller.PlayerDialogController;
import honkytonky.factories.ArmorFactory;
import honkytonky.factories.CreateWorld;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.RoomFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.misc.Cheats;
import honkytonky.misc.ExpTable;
import honkytonky.objects.Player;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.xml.bind.JAXBException;

public class Game {

    // @formatter:off
    private final Scanner scanner                                   = new Scanner(System.in);
    private final WeaponFactory weaponFactory                       = new WeaponFactory();
    private final ArmorFactory armorFactory                         = new ArmorFactory();
    private final PotionFactory potionFactory                       = new PotionFactory();
    private final PlayerDialogController playerDialogController     = new PlayerDialogController();
    private final MerchantController merchantController             = new MerchantController();
    private final BattleController battleController                 = new BattleController();
    private final PlayerController playerController                 = new PlayerController();
    private RoomFactory roomFactory                                 = new RoomFactory();
    private Player player                                           = null;
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
                        boolean playerSuccess = false;
                        boolean roomsSuccess = false;
                        try {
                            roomFactory = JAXBController.loadRooms(roomFactory);    // load present Monsters/Merchants (killed) from CSV
                            roomsSuccess = true;
                        } catch (IOException | JAXBException e) {
                            System.err.println(ANSI_RED + "\nCould not find rooms.xml savestate!" + ANSI_RESET);
                        }
                        try {
                            player = JAXBController                                 // load Player from CSV savestate
                              .loadPlayer(weaponFactory, armorFactory, roomFactory, potionFactory, playerController, battleController);
                            playerSuccess = true;
                            scanner.nextLine();
                        } catch (IOException | JAXBException e) {
                            System.err.println(ANSI_RED + "Could not find player.xml savestate!" + ANSI_RESET);
                            scanner.nextLine();
                        }
                        if (roomsSuccess && playerSuccess) {
                            clearScreen();
                            gameLoop();
                        } else {
                            player = null;      // if at least one of the files could not be loaded, the player shouldn't be able to start the game
                        }
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
                System.out.println("6) Talk to " + ANSI_YELLOW + player.getCurrentRoom()    // If Merchant is present, offer option to talk to him
                  .getPresentMerchant() + ANSI_RESET);
            } else if(player.getCurrentRoom().getName().equals("Living Room")) {        // Living Room gives option to respawn all Merchants/Monsters
                System.out.println("0) Let all Monsters and Merchants respawn");
            }
            System.out.print("\n> ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        playerController.move(scanner, roomFactory);
                        battleController.checkRoomForMonster();
                       // battleController.checkRoomForMerchant(merchantController);    // if activated - show merchant directly when entering room
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
                        JAXBController.PlayerToXML(player);
                        JAXBController.RoomsToXML(roomFactory);
                        System.exit(0);
                    case 6:
                        battleController.checkRoomForMerchant(merchantController);
                        break;
                    case 0:
                        CreateWorld.wipeWorld();
                        roomFactory = new RoomFactory();        // Rooms gotta be present to read all other files!
                        CreateWorld.populateWorld(roomFactory);
                        break;
                    case 1337:
                        Cheats.increaseGold(player);
                        Cheats.increaseExperience(player);
                        Cheats.increaseDamage(player);
                        break;
                }
            } catch (InputMismatchException | NumberFormatException | JAXBException e) {
                clearScreen();
                gameLoop();
            }
            clearScreen();
        }
    }
}
