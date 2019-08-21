package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.Game;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BattleController {

    private Player player;
    private Monster monster;
    private Random rnd = new Random();

    private boolean playerFled = false;

   //----------------------------------------------------------------------//

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    /**
     * This method gets called when the player enters a room or place that contains an enemy
     *
     * Monster Damage Calculation: MonsterDamage + RndNr (1-2) - PlayerArmorPoints
     */
    public void startBattle(Scanner scanner) {
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
            if (monsterAttacks(monsterAlive, scanner) && !playerFled) {
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

    public boolean monsterAttacks(boolean monsterAlive, Scanner scanner) {
        if (monsterAlive) {
            int rng = monster.getDamage() + (rnd.nextInt(2) + 1);
            int monsterDamage = rng - player.getArmor().getArmorPoints() - player
              .getTemporaryDefBoost();

            monsterDamage = (monsterDamage < 0) ? 0 : monsterDamage;

            player.setHp(player.getHp() - monsterDamage);

            System.out.println(
              ANSI_RED + monster + ANSI_RESET + " hit you for " + ANSI_YELLOW + monsterDamage + ANSI_RESET + " damage!\n");

            player.resetTemporaryDefBoost();
            isPlayerAlive(scanner);
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
       Game game = new Game();
    }

    /**
     * checks if the Player is alive every time he got hit by an enemy
     */
    private void isPlayerAlive(Scanner scanner) {
        if (player.getHp() <= 0) {
            clearScreen();
            System.out.println("You died!");
            scanner.nextLine();
            resetGame();
        }
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

    public void checkRoomForMonster(Scanner scanner) {
        if (player.getCurrentRoom().hasLivingMonster()) {
            setMonster(player.getCurrentRoom().getPresentMonster());
            startBattle(scanner);
        }
    }

    public void checkRoomForMerchant(Scanner scanner, DialogController dialogController) {
        if (player.getCurrentRoom().hasMerchant()) {
            dialogController.printMerchantDialog(player, scanner);
        }
    }
}
