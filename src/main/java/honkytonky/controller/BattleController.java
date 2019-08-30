package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_CYAN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.Game;
import honkytonky.objects.Actor;
import honkytonky.objects.Item;
import honkytonky.objects.Merchant;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BattleController {

    /**
     * Random Generator so damage is not too static
     */
    private final Random rnd = new Random();
    /**
     * Scanner for reading Player Inputs
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * The Player Object
     */
    private Player player;
    /**
     * The Monster the Player encountered
     */
    private Monster monster;
    /**
     * The hostile Merchant if the Player decides to attack
     */
    private Merchant enemyMerchant;
    /**
     * First Instance of Enemy, without dependence if its a Monster or Merchant
     */
    private Actor enemy;
    /**
     * true is the Player managed to flee from a fight
     */
    private boolean playerFled = false;

    /**
     * set the player for this instance of BattleController
     *
     * @param player the player object
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * This method gets called when the player enters a room or place that contains an enemy
     *
     * Monster Damage Calculation: MonsterDamage + RndNr (1-2) - PlayerArmorPoints
     */
    void startBattle(Actor enemy) {
        clearScreen();

        boolean enemyAlive = true;
        this.enemy = enemy;
        int durabilityLoss = 0;

        System.out.println("You encountered " + ANSI_RED + enemy + ANSI_RESET + "!\n");

        while (enemyAlive) {
            System.out.println("What do you want to do this round?\n");
            System.out.println(
              "1) Attack with your " + ANSI_YELLOW + player.getWeapon() + ANSI_RESET);
            System.out.println("2) Defensive Mode");
            System.out.println("3) Flee from " + ANSI_YELLOW + enemy + ANSI_RESET);
            System.out.print("\n> ");

            try {
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        durabilityLoss += (player.getWeapon().isTwoHanded()) ? (player.getWeapon().getDurability() * 0.07)
                          : (player.getWeapon().getDurability() * 0.05); // Two-Handed Weapon have slightly higher durability loss (for doing more dmg)
                        enemyAlive = playerAttacks();
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
            if (enemyAttacks(enemyAlive) && !playerFled) {
                player.getWeapon().setDurability(player.getWeapon().getDurability() - durabilityLoss);
                System.out.println(             // Your Weapon lost X Durability
                  "\nYour " + ANSI_CYAN + player.getWeapon() + ANSI_RESET + " lost " + ANSI_RED + durabilityLoss + ANSI_RESET + " Durability!");
                scanner.nextLine();
                clearScreen();
                break;
            }
        }
    }

    /**
     * Is called when the Player is in a battle and chooses to attack the enemy
     *
     * Player Damage Calculation: (WeaponDamage) + (RndNr from 1 to WeaponDamage+2) If maximum damage was reached, it counts as critical hit
     */
    private boolean playerAttacks() {
        clearScreen();

        int wepDMG = player.getWeapon().getDamage();
        int rng = rnd.nextInt(wepDMG + 2) + 1;
        int dmg = wepDMG + rng;

        enemy.setHp(enemy.getHp() - dmg);

        if ((wepDMG + 2) == rng) {
            System.out.println(
              "You hit " + ANSI_RED + enemy + ANSI_RESET + " for " + ANSI_YELLOW + dmg + ANSI_RESET + " damage!" + ANSI_RED + " (Critical Hit!)" + ANSI_RESET);
        } else {
            System.out.println(
              "You hit " + ANSI_RED + enemy + ANSI_RESET + " for " + ANSI_YELLOW + dmg + ANSI_RESET + " damage!");
        }

        return enemy.getHp() >= 0;
    }

    /**
     * lets the enemy attack the player if he is alive, calculating the damage done
     *
     * @param monsterAlive true if the enemy is still alive
     * @return true if monster is already dead, false if monster still alive
     */
    boolean enemyAttacks(boolean monsterAlive) {
        if (monsterAlive) {
            int rng = enemy.getDamage() + (rnd.nextInt(2) + 1);
            int monsterDamage = rng - player.getArmor().getArmorPoints() - player
              .getTemporaryDefBoost();

            monsterDamage = Math.max(monsterDamage, 0);

            player.setHp(player.getHp() - monsterDamage);

            System.out.println(
              ANSI_RED + enemy + ANSI_RESET + " hit you for " + ANSI_YELLOW + monsterDamage + ANSI_RESET + " damage!\n");

            player.resetTemporaryDefBoost();
            isPlayerAlive();
            return false;
        } else {
            System.out.println("You killed " + ANSI_RED + enemy + ANSI_RESET + "!");

            if (enemy instanceof Monster) {
                monster = (Monster) enemy;
                player.getCurrentRoom().monsterKilled();
                rewardPlayerForMonsterKill();
            } else if (enemy instanceof Merchant) {
                enemyMerchant = (Merchant) enemy;                   // convert the encountered enemy to Object of Type Merchant
                rewardPlayerForMerchantKill();
                player.getCurrentRoom().setHasMerchant(false);
                player.getCurrentRoom().setPresentMerchant(null);   // remove Merchant from the Room after he was killed
                return true;
            }
            return true;
        }
    }

    /**
     * Rewards Player for killing a Merchant by increasing his exp, giving him gold and checking for level up
     */
    private void rewardPlayerForMerchantKill() {
        int xpReward = enemyMerchant.getGrantedExperience();
        player.increaseExperience(xpReward);

        System.out
          .println(
            "\n\nYou received " + ANSI_GREEN + xpReward + " Experience Points" + ANSI_RESET +
              " and " + ANSI_GREEN + enemyMerchant.getGrantedGold() + " Gold!" + ANSI_RESET);

        player.giveGold(enemyMerchant.getGrantedGold());
        player.checkForLevelUp();

        System.out.println(ANSI_YELLOW + "\nLoot:" + ANSI_RESET);

        for (Item item : enemyMerchant.getItemsForSell()) {
            System.out.println(item.getName());

            player.getInventory().add(item);

            if (item instanceof Potion) {
                player.getPlayersPotions().put((Potion) item, player.getPlayersPotions().get(item) + 1);
            }
        }
    }

    /**
     * Rewards Player for killing a Monster by increasing his exp, giving him gold and checking for level up
     */
    void rewardPlayerForMonsterKill() {

        int xpReward = calculateExperienceReward();
        player.increaseExperience(xpReward);

        System.out
          .println(
            "\nYou received " + ANSI_GREEN + xpReward + " Experience Points" + ANSI_RESET +
              " and " + ANSI_GREEN + monster.getGoldDropped() + " Gold!" + ANSI_RESET);

        player.giveGold(monster.getGoldDropped());
        player.checkForLevelUp();
    }

    /**
     * calculate the exp the player gets rewarded
     *
     * @return exp amount to give player
     */
    private int calculateExperienceReward() {
        return monster.getGrantedExperience();
    }

    /**
     * checks if the Player is alive every time he got hit by an enemy
     */
    private void isPlayerAlive() {
        if (player.getHp() <= 0) {
            clearScreen();
            System.out
              .println(String.format("%c[%d;%df", 0x1B, 15, 53) + ANSI_RED + "YOU DIED!" + ANSI_RESET);
            System.out.println(String.format("%c[%d;%df", 0x1B, 16,
              10) + "Since this Game is in 'Hardcore Mode', the last time you saved will be your latest respawn point.");
            System.out.println(String.format("%c[%d;%df", 0x1B, 17, 10) + "If you want to go on load your last savestate or create a new Character!");
            scanner.nextLine();
            player = null;      // After death the character is lost
            Game.main(null);    // If Player died return to intro Menu
        }
    }

    /**
     * Is called when the Player is in a battle and chooses defend himself Calculation: tempDefBoost = (armorDef / 2) If (armorDef > 10) then = 2 If
     * (calcDef < 1) then = 1 NOT OPTIMAL!r
     */
    private void playerDefends() {
        player.giveTemporaryDefBoost();
    }

    /**
     * Is called when the Player is in a battle and tries to flee from the fight
     */
    private void playerFlees() {
        clearScreen();
        float fleeChance = (float) (player.getLevel() / enemy.getLevel());
        fleeChance -= rnd.nextFloat();

        if (fleeChance >= 0.5f) {
            System.out.println(ANSI_GREEN + player + ANSI_RESET + " ran away like a little girl!");
            playerFled = true;
        } else {
            System.out.println(ANSI_RED + "You are going nowhere, scrub!" + ANSI_RESET);
        }
    }

    /**
     * check if there is an enemy in the players current room to fight against
     */
    public void checkRoomForMonster() {
        if (player.getCurrentRoom().hasLivingMonster()) {
            startBattle(player.getCurrentRoom().getPresentMonster());
        }
    }

    /**
     * check if there is a merchant in the players current room to interact with
     *
     * @param merchantController instance of MerchantController
     */
    public void checkRoomForMerchant(MerchantController merchantController) {
        if (player.getCurrentRoom().hasMerchant()) {
            merchantController.printMerchantDialog(player, this);
        }
    }
}
