package honkytonky.misc;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;

import honkytonky.objects.Player;

public class CharacterInfoPattern {

    public void printCharacterInfo(Player player) {
        printNameInfo(player);
        printHealthInfo(player);
        printLevelInfo(player);
        printExperienceInfo(player);
        printWeaponInfo(player);
        printArmorInfo(player);
        printLocationInfo(player);
    }

    /**
     * prints info for the type and strength (armorpoints) of the player's current armor Output
     * pattern: "Your Current Armor Is: ArmorType (x Armor Points)"
     *
     * @param player the playerobject that is wearing the armor
     */
    private void printArmorInfo(Player player) {
        if (player.getArmor().getArmorPoints() <= 1) {
            System.out.println("Armor: " + ANSI_YELLOW + String
              .format("%c[%d;%df", 0x1B, 6, 15) + player.getArmor()
                                 + " (" + player.getArmor()
              .getArmorPoints() + " Armor Point)" + ANSI_RESET);
        } else {
            System.out.println("Armor: " + ANSI_YELLOW + String
              .format("%c[%d;%df", 0x1B, 6, 15) + player.getArmor()
                                 + " (" + player.getArmor()
              .getArmorPoints() + " Armor Points)" + ANSI_RESET);
        }
    }

    /**
     * prints info for the type and strength (damage) of the player's current weapon Output pattern:
     * "Your Current Weapon Is: WeaponType (x Damage)"
     *
     * @param player the playerobject that is carrying the weapon
     */
    private void printWeaponInfo(Player player) {
        System.out.println(
          "Weapon: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 5, 15)
            + player.getWeapon() + " (" + player.getWeapon().getDamage() + " Damage)" + ANSI_RESET);
    }

    /**
     * print info for the current Location (read: room) of the player
     *
     * @param player the playerobject thats location is printed
     */
    private void printLocationInfo(Player player) {
        System.out.println(
          "Location: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 7, 15)
            + player.getCurrentRoom() + ANSI_RESET);
    }

    /**
     * prints info for the current and maximal health of the player Output pattern: Your Current HP:
     * (currentHP) / (maximalHP)
     *
     * @param player the playerobject thats health is printed
     */
    private void printHealthInfo(Player player) {
        System.out
          .println("Health: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 2, 15)
                     + player.getHp() + " / " + player.getMaxHP() + ANSI_RESET);
    }

    /**
     * prints info for the name of the player
     *
     * @param player the playerobject thats name is printed
     */
    private void printNameInfo(Player player) {
        System.out.println(
          "Name: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 1, 15) + player
            + ANSI_RESET);
    }

    private void printLevelInfo(Player player) {
        System.out.println(
          "Level: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 3, 15) + player
            .getLevel() + ANSI_RESET);
    }

    private void printExperienceInfo(Player player) {

        System.out.println(
          "Experience: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 4, 15) + player
            .getExperience() + " (" + player.getPercentalExperience() + "%)" + ANSI_RESET);
    }
}
