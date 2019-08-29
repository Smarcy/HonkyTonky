package honkytonky.misc;

import java.util.HashMap;
import java.util.Map;

public class ExpTable {

    /**
     * contains all level threshholds
     */
    private static final Map<Integer, Integer> expTable = new HashMap<>() {{
        put(1, 100);
        put(2, 500);
        put(3, 1300);
        put(4, 2000);
    }};

    /**
     * compare players exp with threshholds to check if the leveled up
     *
     * @param level players current level
     * @param currentExp players current experience points
     * @return true if player leveled up
     */
    public static boolean hasLevelUp(int level, int currentExp) {
        return expTable.get(level) <= currentExp;
    }

    /**
     * calculate the percental experience the player earned towards the next level
     *
     * @param experience players current experience points
     * @param level players current level
     * @return progress to next level
     */
    public static float calculatePercentalExperience(int experience, int level) {
        return ((float) experience / (float) expTable.get(level)) * 100;
    }
}
