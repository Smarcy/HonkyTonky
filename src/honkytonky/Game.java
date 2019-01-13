package honkytonky;

import honkytonky.factories.ArmorFactory;
import honkytonky.factories.ArmorFactory.ArmorType;
import honkytonky.factories.MonsterFactory;
import honkytonky.factories.RoomFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Actor;
import honkytonky.objects.Armor;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private final Scanner scanner = new Scanner(System.in);
    private final ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
    private final MonsterFactory monsterFactory = new MonsterFactory();
    private final RoomFactory roomFactory = new RoomFactory();
    private final WeaponFactory weaponFactory = new WeaponFactory();
    private final ArmorFactory armorFactory = new ArmorFactory();

    private final Room[][] roomList = roomFactory.createRooms();
    private final List<Monster> monsterList = monsterFactory.getMonsterList();
    private final Map<ArmorType, Armor> armorMap = armorFactory.getArmorMap();

    private Player player = null;
    private Monster monster = null;
    private int monsterID;

    private Random rnd = new Random();

    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game();
        game.showIntro();
    }

    /**
     * Shows the menu to choose an option
     */
    private void showIntro() throws InterruptedException, NumberFormatException {
        introLoop:
        while (true) {
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
                            startGame();
                            break introLoop;
                        } else {
                            System.out.println("Please create a new player first!");
                            TimeUnit.SECONDS.sleep(3);
                        }
                        break;
                    case 2:
                        createPlayer();
                        break;
                    default:
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
    private void createPlayer()
      throws NumberFormatException, InputMismatchException, IndexOutOfBoundsException {
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
                        player = new Player(name, 20, 0, 0, startWeapon,
                          armorMap.get(ArmorType.LEATHER)); // One-Handed Sword
                        break;
                    case 2:
                        player = new Player(name, 20, 0, 0, startWeapon,
                          armorMap.get(ArmorType.LEATHER)); // Two-Handed Sword
                        break;
                    case 3:
                        player = new Player(name, 20, 0, 0, startWeapon,
                          armorMap.get(ArmorType.LEATHER)); // One-Handed Axe
                        break;
                    case 4:
                        player = new Player(name, 20, 0, 0, startWeapon,
                          armorMap.get(ArmorType.LEATHER)); // Two-Handed Axe
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
    private void startGame()
      throws InputMismatchException, NumberFormatException, InterruptedException {
        while (true) {
            whereAmI();

            System.out.println("Choose an option:\n");
            System.out.println("1) Move");
            System.out.println("2) Character Info");
            System.out.println("3) Exit Game");
            System.out.print("\n> ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        clearScreen();
                        move();
                        clearScreen();
                        if (roomHasMonster()) {
                            startBattle();
                        }
                        break;
                    case 2:
                        showCharacterInfo();
                        clearScreen();
                        break;
                    case 3:
                        System.exit(0);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                clearScreen();
                startGame();
            }

        }
    }

    /**
     * Console output of the current Room or Place
     */
    private void whereAmI() {
        System.out.println(
          "You are currently in: " + ANSI_GREEN + roomList[player.getX()][player.getY()]
            + ANSI_RESET);  // set Console color to green and reset after
    }

    private void showCharacterInfo() {
        clearScreen();

        System.out.println(
          "Your Name Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 1, 30) + player
            + ANSI_RESET);

        System.out.println(
          "Your Current Weapon Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 2, 30)
            + player.getWeapon() + ANSI_RESET);

        System.out.println(
          "Your Current Armor Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 3, 30)
            + player.getArmor() + ANSI_RESET);

        System.out.println(
          "You Are Currently In: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 4, 30)
            + roomList[player.getX()][player.getY()] + ANSI_RESET);
        System.out.println(

          "Your Current HP: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 5, 30)
            + player.getHp() + " / " + player.getMaxHP() + ANSI_RESET);

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lets the player move to a requested direction
     */
    private void move() throws InterruptedException {
        System.out.println("Where would you like to go?\n");
        System.out.println("\nType west, east, south or north\n");
        String direction;

        try {
            direction = (scanner.nextLine().trim().toLowerCase());

            if (isValidMove(direction)) {
                switch (direction) {
                    case "north":
                        player.setLocation(player.getX(), player.getY() + 1);
                        break;
                    case "east":
                        player.setLocation(player.getX() + 1, player.getY());
                        break;
                    case "south":
                        player.setLocation(player.getX(), player.getY() - 1);
                        break;
                    case "west":
                        player.setLocation(player.getX() - 1, player.getY());
                        break;
                    default:
                        System.out.println(direction + " is not a valid command!");
                }
            } else {
                clearScreen();
                System.out
                  .println(ANSI_RED + "There is no place connected in this direction! (" + direction
                    + ")\n" + ANSI_RESET);
                startGame();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            clearScreen();
            System.out.println(
              ANSI_RED + "There is no place connected in this direction!\n" + ANSI_RESET);
            startGame();
        }
    }

    /**
     * Checks if a requested move is valid by comparing input string & roomList[x][y] != null &
     * Room.has[direction]Exit
     *
     * @param direction where the player wants to move
     * @return if the requested move is valid
     */
    private boolean isValidMove(String direction) {
        if (direction.equals("north") && roomList[player.getX()][player.getY() + 1] != null
          && roomList[player.getX()][player.getY()].hasNorthExit()) {
            return true;
        } else if (direction.equals("east") && roomList[player.getX() + 1][player.getY()] != null
          && roomList[player.getX()][player.getY()].hasEastExit()) {
            return true;
        } else if (direction.equals("south") && roomList[player.getX()][player.getY() - 1] != null
          && roomList[player.getX()][player.getY()].hasSouthExit()) {
            return true;
        } else {
            return direction.equals("west") && roomList[player.getX() - 1][player.getY()] != null
              && roomList[player.getX()][player.getY()].hasWestExit();
        }
    }

    /**
     * Clear the console completely
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Checks after every move if the current room contains a monster
     *
     * @return true if monster in room, false if no monster in room
     */
    private boolean roomHasMonster() {
        for (Actor actor : monsterList) {
            if (actor.getX() == player.getX() && actor.getY() == player.getY()) {
                monsterID = actor.getID();
                return true;
            }
        }
        return false;
    }

    /**
     * This method gets called when the player enters a room or place that contains an enemy
     *
     * Monster Damage Calculation: MonsterDamage + RndNr (1-2) - PlayerArmorPoints
     */
    private void startBattle()
      throws InputMismatchException, NumberFormatException, ArrayIndexOutOfBoundsException,
      InterruptedException, NumberFormatException {
        clearScreen();

        boolean monsterAlive = true;
        monster = monsterFactory.getMonsterList().get(monsterID);

        System.out.println(ANSI_RED + "You encountered a " + monster + "!\n" + ANSI_RESET);

        while (monsterAlive) {
            System.out.println("What do you want to do this round?\n");
            System.out.println(
              "1) Attack with your " + ANSI_YELLOW + player.getWeapon() + ANSI_RESET);
            System.out.println("2) Defensive Mode");
            System.out.println("3) Flee from " + ANSI_YELLOW + monster + ANSI_RESET);
            System.out.print("\n> ");

            try {
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        monsterAlive = playerAttacks();
                        break;
                    case 2:
                        playerDefends();
                        clearScreen();
                        break;
                    case 3:
                        playerFlees();
                        break;
                    default:
                        clearScreen();
                        break;
                }
            } catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                clearScreen();
                continue;
            }

            if (monsterAlive) {
                int rng = monster.getDamage() + (rnd.nextInt(2) + 1) - player.getArmor()
                  .getArmorPoints();

                if (rng < 0) {
                    rng = 0;
                }

                player.setHp(player.getHp() - rng);

                System.out.println(
                  ANSI_RED + monster + ANSI_RESET + " hit you for " + ANSI_YELLOW + rng + ANSI_RESET + " damage!\n");

                isPlayerAlive();
            } else {
                System.out.println("You killed " + ANSI_RED + monster + ANSI_RESET + "!");

                rewardPlayer();

                try {
                    System.in.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                clearScreen();
                break;
            }

        }
    }

    /**
     * Is called when the Player is in a battle and chooses to attack the enemy
     *
     * Player Damage Calculation: (WeaponDamage) + (RndNr from 1 to WeaponDamage+2) If maximum
     * damage was reached, it counts as critical hit
     */
    private boolean playerAttacks() {
        clearScreen();

        int wepDMG = player.getWeapon()
          .getDamage();
        int rng = rnd.nextInt(wepDMG + 2) + 1;

        int dmg = wepDMG + rng;

        monster.setHp(monster.getHp() - dmg);

        if ((wepDMG + 2) == rng) {
            System.out.println(
              "You hit " + ANSI_RED + monster + ANSI_RESET + " for " + ANSI_YELLOW + dmg + ANSI_RESET + " damage!" + ANSI_RED + " (Critical Hit!)" + ANSI_RESET);
        } else {
            System.out.println(
              "You hit " + ANSI_RED + monster + ANSI_RESET + " for " + ANSI_YELLOW + dmg + ANSI_RESET + " damage!");
        }

        return monster.getHp() >= 0;
    }

    /**
     * Is called when the Player is in a battle and chooses defend himself
     */
    private boolean playerDefends() {
        return true;
    }

    /**
     * Is called when the Player is in a battle and chooses to flee from the fight
     */
    private boolean playerFlees() {
        return true;
    }

    private void rewardPlayer() {

    }

    private void resetGame() {

    }

    /**
     * checks if the Player is alive every time he got hit by an enemy
     * @throws InterruptedException
     */
    private void isPlayerAlive() throws InterruptedException {
        if (player.getHp() <= 0) {
            clearScreen();

            System.out.println("You died!");

            resetGame();

            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }

            showIntro();
        }
    }
}