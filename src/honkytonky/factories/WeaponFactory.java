package honkytonky.factories;

import honkytonky.objects.Weapon;
import honkytonky.objects.Weapon.WeaponType;
import java.util.ArrayList;
import java.util.List;

public class WeaponFactory
{
    private List<Weapon> weaponList = new ArrayList<>();

    public WeaponFactory()
    {
       weaponList.add(new Weapon(0, "One-Handed Sword", WeaponType.SWORD, 5, 100, false));
    }

    public List<Weapon> getWeaponList()
    {
        return weaponList;
    }

}
