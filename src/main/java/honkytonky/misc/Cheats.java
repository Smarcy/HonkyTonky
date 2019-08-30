package honkytonky.misc;

import honkytonky.objects.Player;

/**
 * This class is just for testing purposes for the Developer and will be deleted later
 */
public class Cheats {

    /**
     * increase players gold by a high amount
     * @param player player object
     */
    public static void increaseGold(Player player) {
        player.giveGold(1000);
    }

    /**
     * increase players exp by a high amount
     * @param player player object
     */
    public static void increaseExperience(Player player) {
        player.increaseExperience(1000);
        player.checkForLevelUp();
    }

    /**
     * increase player damage by a high amount
     * @param player player object
     */
    public static void increaseDamage(Player player) {
        player.getWeapon().setDamage(100);
    }

}
