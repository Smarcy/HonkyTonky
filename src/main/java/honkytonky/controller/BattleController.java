package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.Game;
import honkytonky.objects.Actor;
import honkytonky.objects.Merchant;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BattleController {

    private final Random rnd = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private Player player;
    private Monster monster;
    private Actor enemy;
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
     * @return ???
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
                //FIXME: reward player for killing a Merchant (separate reward-method?!)
            }
            scanner.nextLine();
            clearScreen();
            return true;
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
            "\n\nYou received " + ANSI_GREEN + xpReward + " Experience Points" + ANSI_RESET +
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
     * if the player died, reset the game - //FIXME!!!
     */
    private void resetGame() {
        Game game = new Game();
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

    /**
     * Is called when the Player is in a battle and chooses defend himself Calculation: tempDefBoost = (armorDef / 2) If (armorDef > 10) then = 2 If
     * (calcDef < 1) then = 1 NOT OPTIMAL!r
     */
    private void playerDefends() {
        player.giveTemporaryDefBoost();
    }

    /**
     * Is called when the Player is in a battle and chooses to flee from the fight
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
