package honkytonky.misc;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;

import honkytonky.objects.Player;

public class CharacterInfoPattern {

    private Player player;

    /**
     * print all relevant infos about the player
     *
     * @param player the player object
     */
    public void printCharacterInfo(Player player) {
        this.player = player;
        printNameInfo();
        printHealthInfo();
        printLevelInfo();
        printExperienceInfo();
        printGoldInfo();
        printWeaponInfo();
        printArmorInfo();
        printLocationInfo();
    }

    /**
     * prints info for the type and strength (armorpoints) of the player's current armor Output pattern: "Your Current Armor Is: ArmorType (x Armor
     * Points)"
     */
    private void printArmorInfo() {
        if (player.getArmor().getArmorPoints() <= 1) {
            System.out.println("Armor: " + ANSI_YELLOW + String
              .format("%c[%d;%df", 0x1B, 7, 15) + player.getArmor()
              + " (" + player.getArmor()
              .getArmorPoints() + " Armor Point)" + ANSI_RESET);
        } else {
            System.out.println("Armor: " + ANSI_YELLOW + String
              .format("%c[%d;%df", 0x1B, 7, 15) + player.getArmor()
              + " (" + player.getArmor()
              .getArmorPoints() + " Armor Points)" + ANSI_RESET);
        }
    }

    /**
     * prints info for the type and strength (damage) of the player's current weapon Output pattern: "Your Current Weapon Is: WeaponType (x Damage)"
     */
    private void printWeaponInfo() {
        System.out.println(
          "Weapon: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 6, 15)
            + player.getWeapon() + " (" + player.getWeapon().getDamage() + " Damage)" + ANSI_RESET);
    }

    /**
     * print info for the current Location (read: room) of the player
     */
    private void printLocationInfo() {
        System.out.println(
          "Location: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 8, 15)
            + player.getCurrentRoom() + ANSI_RESET);
    }

    /**
     * prints info for the current and maximal health of the player Output pattern: Your Current HP: (currentHP) / (maximalHP)
     */
    private void printHealthInfo() {
        System.out
          .println("Health: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 2, 15)
            + player.getHp() + " / " + player.getMaxHP() + ANSI_RESET);
    }

    /**
     * prints info for the name of the player
     */
    private void printNameInfo() {
        System.out.println(
          "Name: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 1, 15) + player
            + ANSI_RESET);
    }

    /**
     * print info for the level of the player
     */
    private void printLevelInfo() {
        System.out.println(
          "Level: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 3, 15) + player
            .getLevel() + ANSI_RESET);
    }

    /**
     * print players current experience and progress to next level
     */
    private void printExperienceInfo() {
        System.out.println(
          "Experience: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 4, 15) + player
            .getExperience() + " (" + player.getPercentalExperience() + "%)" + ANSI_RESET);
    }

    /**
     * print players current gold amount
     */
    private void printGoldInfo() {
        System.out.println(
          "Gold: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 5, 15) + player
            .getGold() + ANSI_RESET);
    }
}
