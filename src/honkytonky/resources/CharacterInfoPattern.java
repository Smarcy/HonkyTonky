package honkytonky.resources;

import static honkytonky.resources.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.resources.ANSI_Color_Codes.ANSI_YELLOW;

import honkytonky.objects.Player;

public class CharacterInfoPattern {

    /**
     * prints info for the type and strength (armorpoints) of the player's current armor
     *
     * @param player the playerobject that is wearing the armor
     * @return "Your Current Armor Is: ArmorType (x Armor Points)"
     */
    public void printArmorInfo(Player player) {
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

    public void printWeaponInfo(Player player) {
        System.out.println(
          "Your Current Weapon Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 2, 30)
            + player.getWeapon() + ANSI_RESET);
    }

    public void printLocationInfo(Player player) {
        System.out.println(
          "You Are Currently In: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 4, 30)
            + player.getCurrentRoom() + ANSI_RESET);
    }

    public void printHealthInfo(Player player) {
        System.out
          .println("Your Current HP: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 5, 30)
            + player.getHp() + " / " + player.getMaxHP() + ANSI_RESET);
    }

    public void printNameInfo(Player player) {
        System.out.println(
          "Your Name Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 1, 30) + player
            + ANSI_RESET);
    }
}
