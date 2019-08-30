package honkytonky.misc;

import java.util.HashMap;
import java.util.Map;

public class ExpTable {

    /**
     * contains all level thresholds
     */
    private static final Map<Integer, Integer> expTable = new HashMap<>() {{
        put(1, 100);
    }};

    public static void createLevels() {
        for (int i = 2; i < 100; ++i) {
            int predecessor = expTable.get(i-1);
            double expNeededForNextLevel = predecessor * 1.2;
            expTable.put(i, (int) expNeededForNextLevel);
        }
    }

    /**
     * compare players exp with thresholds to check if the leveled up
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
