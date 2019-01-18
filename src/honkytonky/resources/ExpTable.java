package honkytonky.resources;

import honkytonky.objects.Player;
import java.util.HashMap;
import java.util.Map;

public class ExpTable {

    private Map<Integer, Integer> expTable = new HashMap<>();

    public ExpTable()
    {
        expTable.put(1, 100);
        expTable.put(2, 500);
        expTable.put(3, 1300);
        expTable.put(4, 2000);
    }

    public boolean hasLevelUp(int level, int currentExp)
    {
        if(expTable.get(level) <= currentExp )
        {
            return true;
        }

        return false;
    }

    public int calculatePercentalExperience(Player player)
    {
        return (player.getExperience() / expTable.get(player.getLevel()) * 100);
    }
}
