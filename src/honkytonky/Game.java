package honkytonky;

import static honkytonky.resources.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.resources.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.resources.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.resources.ANSI_Color_Codes.ANSI_YELLOW;

import honkytonky.factories.ArmorFactory;
import honkytonky.factories.ArmorFactory.ArmorType;
import honkytonky.factories.MapLayout;
import honkytonky.factories.MonsterFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Actor;
import honkytonky.objects.Armor;
import honkytonky.objects.Door;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import honkytonky.resources.CharacterInfoPattern;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Game {


    // @formatter:off
    private final Scanner scanner                   = new Scanner(System.in);
    private final ProcessBuilder pb                 = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
    private final MonsterFactory monsterFactory     = new MonsterFactory();
    private final WeaponFactory weaponFactory       = new WeaponFactory();
    private final ArmorFactory armorFactory         = new ArmorFactory();
    private final MapLayout mapLayout               = new MapLayout();
    private final CharacterInfoPattern charInfo     = new CharacterInfoPattern();

    private final List<Monster> monsterList         = monsterFactory.getMonsterList();
    private final Map<ArmorType, Armor> armorMap    = armorFactory.getArmorMap();
    private final List<Room> rooms                  = mapLayout.getRooms();

    private Player player   = null;
    private Monster monster = null;
    private int monsterID;

    private Random rnd = new Random();
    // @formatter:on

    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game();
        game.showIntro();
    }

    /**
     * Shows the menu to choose an option
     */
    private void showIntro() throws InterruptedException, NumberFormatException {

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
                            startGame();
                            run = false;
                            break;
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
                                            armorMap.get(ArmorType.LEATHER), rooms
                                              .get(0)); // One-Handed Sword
                        break;
                    case 2:
                        player = new Player(name, 20, 0, 0, startWeapon,
                                            armorMap.get(ArmorType.LEATHER), rooms
                                              .get(0)); // Two-Handed Sword
                        break;
                    case 3:
                        player = new Player(name, 20, 0, 0, startWeapon,
                                            armorMap.get(ArmorType.LEATHER), rooms
                                              .get(0)); // One-Handed Axe
                        break;
                    case 4:
                        player = new Player(name, 20, 0, 0, startWeapon,
                                            armorMap.get(ArmorType.LEATHER), rooms
                                              .get(0)); // Two-Handed Axe
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
            printCurrentLocation();

            System.out.println("\nChoose an option:\n");
            System.out.println("1) Move");
            System.out.println("2) Character Info");
            System.out.println("3) Exit Game");
            System.out.print("\n> ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        move();
                        checkRoomForMonster();
                        break;
                    case 2:
                        printCharacterInfo();
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

    private void checkRoomForMonster() throws InterruptedException {
        if (player.getCurrentRoom().hasLivingMonster()) {
            startBattle();
        }
    }

    /**
     * Console output of the current Room or Place
     */
    private void printCurrentLocation() {
        System.out.println(
          "You are currently in: " + ANSI_GREEN + player.getCurrentRoom()
            + ANSI_RESET);
    }

    private void printCharacterInfo() {
        clearScreen();

        charInfo.printCharacterInfo(player);

        scanner.nextLine();

        clearScreen();
    }

    /**
     * Lets the player move to a requested direction
     */
    private void move() {
        clearScreen();

        player.getCurrentRoom().listDoorOptions();

        int option = Integer.parseInt(scanner.nextLine());

        Door targetDoor = player.getCurrentRoom().getDoors().get(option - 1);
        Room targetRoom = mapLayout.getRoomByName(targetDoor.getTargetRoom().getName());

        player.setCurrentRoom(targetRoom);

        clearScreen();
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
      InterruptedException {
        clearScreen();

        boolean monsterAlive = true;
        monster = monsterFactory.getMonsterList().get(monsterID);

        System.out.println("You encountered " + ANSI_RED + monster + ANSI_RESET + "!\n");

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

            if (monsterAttacks(monsterAlive)) {
                break;
            }


        }
    }

    private boolean monsterAttacks(boolean monsterAlive) throws InterruptedException {
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

            return false;
        } else {
            System.out.println("You killed " + ANSI_RED + monster + ANSI_RESET + "!");

            rewardPlayer();

            scanner.nextLine(); // Stop here until a key is pressed

            clearScreen();

            return true;
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

        int wepDMG = player.getWeapon().getDamage();
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
        int xpReward = calculateExperienceReward();

        player.increaseExperience(xpReward);

        System.out
          .println("\n\nYou received " + ANSI_GREEN + xpReward + " Experience Points!" + ANSI_RESET);

        player.checkForLevelUp(player);
    }

    private int calculateExperienceReward() {
        return monster.getGrantedExperience();
    }

    private void resetGame() {

    }

    /**
     * checks if the Player is alive every time he got hit by an enemy
     */
    private void isPlayerAlive() throws InterruptedException {
        if (player.getHp() <= 0) {
            clearScreen();

            System.out.println("You died!");

            resetGame();

            scanner.nextLine(); // Stop here until a key is pressed

            showIntro();
        }
    }
}