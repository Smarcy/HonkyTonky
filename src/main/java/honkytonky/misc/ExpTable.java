package honkytonky.misc;

import java.util.HashMap;
import java.util.Map;

public class ExpTable {

    private static final Map<Integer, Integer> expTable = new HashMap<>() {{
        put(1, 100);
        put(2, 500);
        put(3, 1300);
        put(4, 2000);
    }};

    public static boolean hasLevelUp(int level, int currentExp) {
        return expTable.get(level) <= currentExp;
    }

    public static float calculatePercentalExperience(int experience, int level) {
        return ((float) experience / (float) expTable.get(level)) * 100;
    }
}
