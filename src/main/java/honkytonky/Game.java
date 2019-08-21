package honkytonky;

import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.controller.BattleController;
import honkytonky.controller.DialogController;
import honkytonky.controller.PlayerController;
import honkytonky.factories.ArmorFactory;
import honkytonky.factories.MapLayout;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {

    // @formatter:off
    private final Scanner scanner                   = new Scanner(System.in);
    private final WeaponFactory weaponFactory       = new WeaponFactory();
    private final ArmorFactory armorFactory         = new ArmorFactory();
    private final PotionFactory potionFactory       = new PotionFactory();
    private final MapLayout mapLayout               = new MapLayout();
    private final DialogController dialogController = new DialogController();
    private final BattleController battleController = new BattleController();
    private final PlayerController playerController = new PlayerController();
    private final List<Room> rooms                  = mapLayout.getRooms();

    private Player player                           = null;
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
                          .createPlayer(scanner, armorFactory, weaponFactory, potionFactory,
                            battleController, rooms);
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
        while (true) {
            dialogController.printCurrentLocation(player);

            System.out.println("\nChoose an option:\n");
            System.out.println("1) Move");
            System.out.println("2) Use Potion");
            System.out.println("3) Character Info");
            System.out.println("4) Show Inventory");
            System.out.println("5) Exit Game");
            System.out.print("\n> ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        playerController.move(scanner, mapLayout);
                        battleController.checkRoomForMonster(scanner);
                        checkRoomForMerchant();
                        break;
                    case 2:
                        dialogController.printUsePotionDialog(player, scanner);
                        break;
                    case 3:
                        dialogController.printCharacterInfo(player, scanner);
                        break;
                    case 4:
                        dialogController.printInventoryDialog(player, scanner);
                        break;
                    case 5:
                        System.exit(0);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                clearScreen();
                gameLoop();
            }
            clearScreen();
        }
    }

    private void checkRoomForMerchant() {
        if (player.getCurrentRoom().hasMerchant()) {
            dialogController.printMerchantDialog(player, scanner);
        }
    }
}
