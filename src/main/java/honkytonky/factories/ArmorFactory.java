package honkytonky.factories;

import honkytonky.objects.Armor;
import honkytonky.resources.ArmorType;
import java.util.EnumMap;
import java.util.Map;

public class ArmorFactory {

    private Map<ArmorType, Armor> armorMap = new EnumMap<>(ArmorType.class);

    public ArmorFactory() {
        armorMap.put(ArmorType.LEATHER, new Armor(armorMap.size(), "Leather Armor", 1, 100, 100));
        armorMap.put(ArmorType.IRON, new Armor(armorMap.size(), "Iron Armor", 2, 100, 100));
        armorMap.put(ArmorType.STEEL, new Armor(armorMap.size(), "Steel Armor", 3, 100, 100));
    }

    public Map<ArmorType, Armor> getArmorMap() {
        return armorMap;
    }
}
