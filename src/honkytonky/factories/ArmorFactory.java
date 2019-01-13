package honkytonky.factories;

import honkytonky.objects.Armor;
import java.util.ArrayList;
import java.util.List;

public class ArmorFactory
{

    private List<Armor> armorList = new ArrayList<>();

    public ArmorFactory()
    {
        armorList.add(new Armor(0, "Leather Armor", 1));
    }
}
