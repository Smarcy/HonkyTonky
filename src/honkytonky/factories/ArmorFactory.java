package honkytonky.factories;

import honkytonky.objects.Armor;
import java.util.ArrayList;
import java.util.List;

public class ArmorFactory
{

    public enum Armors
    {
      LEATHER,
      IRON,
      STEEL
    }

    private List<Armor> armorList = new ArrayList<>();

    public ArmorFactory()
    {
        armorList.add(new Armor(armorList.size(), "Leather Armor", 1));
        armorList.add(new Armor(armorList.size(), "Iron Armor", 2));
        armorList.add(new Armor(armorList.size(), "Steel Armor", 3));
    }

    public List<Armor> getArmorList()
    {
        return armorList;
    }
}
