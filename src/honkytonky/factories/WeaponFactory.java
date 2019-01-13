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
       weaponList.add(new Weapon(weaponList.size(), "One-Handed Sword", WeaponType.SWORD,   5, 100, false));
       weaponList.add(new Weapon(weaponList.size(), "Two-Handed Sword", WeaponType.SWORD,   8, 100, true));
       weaponList.add(new Weapon(weaponList.size(), "One-Handed Axe",   WeaponType.AXE,     5, 100, false));
       weaponList.add(new Weapon(weaponList.size(), "Two-Handed Axe",   WeaponType.AXE,     9, 100, true));
    }

    public List<Weapon> getWeaponList()
    {
        return weaponList;
    }
}
