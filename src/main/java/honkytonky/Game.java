package honkytonky;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.controller.BattleController;
import honkytonky.controller.MerchantController;
import honkytonky.controller.PlayerController;
import honkytonky.controller.PlayerDialogController;
import honkytonky.factories.ArmorFactory;
import honkytonky.factories.DoorFactory;
import honkytonky.factories.MerchantFactory;
import honkytonky.factories.MonsterFactory;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.RoomFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {

    // @formatter:off
    private Player player                                           = null;
    private final Scanner scanner                                   = new Scanner(System.in);
    private final WeaponFactory weaponFactory                       = new WeaponFactory();
    private final ArmorFactory armorFactory                         = new ArmorFactory();
    private final PotionFactory potionFactory                       = new PotionFactory();
    private final RoomFactory roomFactory                           = new RoomFactory();
    private final DoorFactory doorFactory                           = new DoorFactory(roomFactory);
    private final MonsterFactory monsterFactory                     = new MonsterFactory(roomFactory);
    private final MerchantFactory merchantFactory                   = new MerchantFactory(roomFactory);
    private final PlayerDialogController playerDialogController     = new PlayerDialogController();
    private final MerchantController merchantController             = new MerchantController();
    private final BattleController battleController                 = new BattleController();
    private final PlayerController playerController                 = new PlayerController();
    private final List<Room> rooms                                  = roomFactory.getRooms();
    // @formatter:on

    public Game() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.showIntro();
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
            System.out.println("2) Create New Player\n");
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
                          .createPlayer(armorFactory, weaponFactory, potionFactory,
                            battleController, rooms);

                        player.getInventory().add(potionFactory.getPotionByName("Small Health Potion")); // give Player startPotion
                        player.getPlayersPotions().put(potionFactory.getPotionByName("Small Health Potion"), 1);
                        player.setCurrentRoom(roomFactory.getRoomByName("Town Square"));        // !!!DELETEME - TESTING PURPOSES!!!
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
            System.out.println("5) Exit Game");
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
                        playerDialogController.printUsePotionDialog();
                        break;
                    case 3:
                        playerDialogController.printCharacterInfo();
                        break;
                    case 4:
                        playerDialogController.printInventoryDialog();
                        break;
                    case 5:
                        System.exit(0);
                    case 6:
                        battleController.checkRoomForMerchant(merchantController);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                clearScreen();
                gameLoop();
            }
            clearScreen();
        }
    }
}
