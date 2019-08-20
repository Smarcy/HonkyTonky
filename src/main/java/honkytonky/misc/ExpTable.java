package honkytonky.misc;

import java.util.HashMap;
import java.util.Map;

public class ExpTable {

    private Map<Integer, Integer> expTable = new HashMap<>();

    public ExpTable() {
        expTable.put(1, 100);
        expTable.put(2, 500);
        expTable.put(3, 1300);
        expTable.put(4, 2000);
    }

    public boolean hasLevelUp(int level, int currentExp) {
        return expTable.get(level) <= currentExp;

    }

    public float calculatePercentalExperience(int experience, int level) {
        int test = expTable.get(level);

        float moreTest = ((float) experience / (float) test);

        return ((float) experience / (float) expTable.get(level)) * 100;
    }
}
