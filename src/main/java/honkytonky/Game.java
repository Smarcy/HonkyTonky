package honkytonky;

import honkytonky.controller.BattleController;
import honkytonky.controller.DialogController;
import honkytonky.factories.ArmorFactory;
import honkytonky.factories.MapLayout;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Door;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
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
     * Clear the console completely
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
                        createPlayer();
                        break;
                }
            } catch (NumberFormatException e) {
                showIntro();
            }
        }
    }

    /**
     * Lets the user create a Player Object
     */
    private void createPlayer() {
        clearScreen();

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        clearScreen();

        while (player == null) {
            clearScreen();

            System.out.println("Choose a weapon: ");
            System.out.println("1) One-Handed Sword");
            System.out.println("2) Two-Handed Sword");
            System.out.println("3) One-Handed Axe");
            System.out.println("4) Two-Handed Axe");

            try {
                int weapon = Integer.parseInt(scanner.nextLine());

                Weapon startWeapon = weaponFactory.getWeaponList().get(weapon - 1);

                switch (weapon) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        player = new Player(name, 20, startWeapon,
                          armorFactory.findArmorByName("Leather Armor"),
                          potionFactory.startPotion(), rooms
                          .get(0));
                        battleController.setPlayer(player);
                        break;
                    default:
                        clearScreen();
                        System.out.println(weapon + " is not a valid option!\n");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                e.printStackTrace();
                clearScreen();
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
                        move();
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

    /**
     * Lets the player move to a requested direction
     */
    private void move() {
        clearScreen();

        player.getCurrentRoom().listDoorOptions();

        try {
            int option = Integer.parseInt(scanner.nextLine());

            Door targetDoor = player.getCurrentRoom().getDoors().get(option - 1);
            Room targetRoom = mapLayout.getRoomByName(targetDoor.getTargetRoom().getName());

            player.setCurrentRoom(targetRoom);
        } catch (IndexOutOfBoundsException e) {
            move();
        }
        clearScreen();
    }
}
