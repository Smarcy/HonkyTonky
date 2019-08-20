package honkytonky.factories;

import honkytonky.objects.Armor;
import honkytonky.enumtypes.ArmorType;
import java.util.HashMap;
import java.util.Map;

public class ArmorFactory {

    private Map<String, Armor> armorMap = new HashMap<>();

    public ArmorFactory() {
        armorMap.put("Leather Armor", new Armor(armorMap.size(), "Leather Armor", 1, 100, 100, ArmorType.LEATHER));
        armorMap.put("Iron Armor", new Armor(armorMap.size(), "Iron Armor", 2, 100, 100, ArmorType.IRON));
        armorMap.put("Steel Armor", new Armor(armorMap.size(), "Steel Armor", 3, 100, 100, ArmorType.STEEL));
    }

    public Map<String, Armor> getArmorMap() {
        return armorMap;
    }
}
