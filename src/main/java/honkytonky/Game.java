package honkytonky;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;

import honkytonky.controller.DialogController;
import honkytonky.factories.ArmorFactory;
import honkytonky.factories.MapLayout;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Armor;
import honkytonky.objects.Door;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    // @formatter:off
    private final Scanner scanner                   = new Scanner(System.in);
    private final ProcessBuilder pb                 = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
    private final WeaponFactory weaponFactory       = new WeaponFactory();
    private final ArmorFactory armorFactory         = new ArmorFactory();
    private final PotionFactory potionFactory       = new PotionFactory();
    private final MapLayout mapLayout               = new MapLayout();
    private final DialogController dialogController = new DialogController();
    private final List<Armor> armorList             = armorFactory.getArmorList();
    private final List<Room> rooms                  = mapLayout.getRooms();

    private Player player                           = null;
    private Monster monster                         = null;
    private boolean playerFled                      = false;

    private Random rnd = new Random();
    // @formatter:on

    private Game() {
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
                          armorFactory.findArmorByName("Leather Armor"), potionFactory.startPotion(), rooms
                          .get(0));
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
                        checkRoomForMonster();
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

    private void checkRoomForMonster() {
        if (player.getCurrentRoom().hasLivingMonster()) {
            startBattle();
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

    /**
     * Clear the console completely
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * This method gets called when the player enters a room or place that contains an enemy
     *
     * Monster Damage Calculation: MonsterDamage + RndNr (1-2) - PlayerArmorPoints
     */
    private void startBattle() {
        clearScreen();

        boolean monsterAlive = true;
        monster = player.getCurrentRoom().getPresentMonster();

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

            if (playerFled) {
                playerFled = false;
                break;
            }

            if (monsterAttacks(monsterAlive) && !playerFled) {
                break;
            }
        }
    }

    protected boolean monsterAttacks(boolean monsterAlive) {
        if (monsterAlive) {
            int rng = monster.getDamage() + (rnd.nextInt(2) + 1);
            int monsterDamage = rng - player.getArmor().getArmorPoints() - player
              .getTemporaryDefBoost();

            monsterDamage = (monsterDamage < 0) ? 0 : monsterDamage;

            player.setHp(player.getHp() - monsterDamage);

            System.out.println(
              ANSI_RED + monster + ANSI_RESET + " hit you for " + ANSI_YELLOW + monsterDamage + ANSI_RESET + " damage!\n");

            player.resetTemporaryDefBoost();
            isPlayerAlive();
            return false;
        } else {
            System.out.println("You killed " + ANSI_RED + monster + ANSI_RESET + "!");

            player.getCurrentRoom().monsterKilled();
            rewardPlayer();
            scanner.nextLine();
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
     * Is called when the Player is in a battle and chooses defend himself Calculation: tempDefBoost
     * = (armorDef / 2) If (armorDef > 10) then = 2 If (calcDef < 1) then = 1 NOT OPTIMAL!r
     */
    private void playerDefends() {
        player.giveTemporaryDefBoost();
    }

    /**
     * Is called when the Player is in a battle and chooses to flee from the fight
     */
    private void playerFlees() {
        clearScreen();
        float fleeChance = (float) (player.getLevel() / monster.getLevel());
        fleeChance -= rnd.nextFloat();

        if (fleeChance >= 0.5f) {
            System.out.println(ANSI_GREEN + player + ANSI_RESET + " ran away like a little girl!");
            playerFled = true;
        } else {
            System.out.println(ANSI_RED + "You are going nowhere, scrub!" + ANSI_RESET);
        }


    }

    private void rewardPlayer() {
        int xpReward = calculateExperienceReward();
        player.increaseExperience(xpReward);

        System.out
          .println(
            "\n\nYou received " + ANSI_GREEN + xpReward + " Experience Points!" + ANSI_RESET);

        player.checkForLevelUp();
    }

    private int calculateExperienceReward() {
        return monster.getGrantedExperience();
    }

    private void resetGame() {
        player = null;
        monster = null;
        showIntro();
    }

    /**
     * checks if the Player is alive every time he got hit by an enemy
     */
    private void isPlayerAlive() {
        if (player.getHp() <= 0) {
            clearScreen();
            System.out.println("You died!");
            scanner.nextLine();
            resetGame();
        }
    }
}
