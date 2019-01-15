package honkytonky.resources;

import static honkytonky.resources.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.resources.ANSI_Color_Codes.ANSI_YELLOW;

import honkytonky.objects.Player;

public class CharacterInfoPattern {

    public void printCharacterInfo(Player player) {
        printNameInfo(player);

        printWeaponInfo(player);

        printArmorInfo(player);

        printLocationInfo(player);

        printHealthInfo(player);
    }

    /**
     * prints info for the type and strength (armorpoints) of the player's current armor
     * Output pattern: "Your Current Armor Is: ArmorType (x Armor Points)"
     *
     * @param player the playerobject that is wearing the armor
     */
    private void printArmorInfo(Player player) {
        if (player.getArmor().getArmorPoints() <= 1) {
            System.out.println("Your Current Armor Is: " + ANSI_YELLOW + String
              .format("%c[%d;%df", 0x1B, 3, 30) + player.getArmor()
              + " (" + player.getArmor().getArmorPoints() + " Armor Point)" + ANSI_RESET);
        } else {
            System.out.println("Your Current Armor Is: " + ANSI_YELLOW + String
              .format("%c[%d;%df", 0x1B, 3, 30) + player.getArmor()
              + " (" + player.getArmor().getArmorPoints() + " Armor Points)" + ANSI_RESET);
        }
    }

    /**
     * prints info for the type and strength (damage) of the player's current weapon
     * Output pattern: "Your Current Weapon Is: WeaponType (x Damage)"
     *
     * @param player the playerobject that is carrying the weapon
     */
    private void printWeaponInfo(Player player) {
        System.out.println(
          "Your Current Weapon Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 2, 30)
            + player.getWeapon() + " (" + player.getWeapon().getDamage() + " Damage)" + ANSI_RESET);
    }

    /**
     * print info for the current Location (read: room) of the player
     * @param player the playerobject thats location is printed
     */
    private void printLocationInfo(Player player) {
        System.out.println(
          "You Are Currently In: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 4, 30)
            + player.getCurrentRoom() + ANSI_RESET);
    }

    /**
     * prints info for the current and maximal health of the player
     * Output pattern: Your Current HP: (currentHP) / (maximalHP)
     * @param player the playerobject thats health is printed
     */
    private void printHealthInfo(Player player) {
        System.out
          .println("Your Current HP: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 5, 30)
            + player.getHp() + " / " + player.getMaxHP() + ANSI_RESET);
    }

    /**
     *prints info for the name of the player
     * @param player the playerobject thats name is printed
     */
    private void printNameInfo(Player player) {
        System.out.println(
          "Your Name Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 1, 30) + player
            + ANSI_RESET);
    }
}
