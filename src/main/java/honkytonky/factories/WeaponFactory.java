package honkytonky.factories;

import honkytonky.objects.Weapon;
import honkytonky.enumtypes.WeaponType;
import java.util.ArrayList;
import java.util.List;

public class WeaponFactory {

    private List<Weapon> weaponList = new ArrayList<>();

    public WeaponFactory() {
        weaponList.add(new Weapon(weaponList
          .size(), "One-Handed Sword", WeaponType.SWORD, 2, 100, 100, false));
        weaponList.add(new Weapon(weaponList
          .size(), "Two-Handed Sword", WeaponType.SWORD, 3, 100, 100, true));
        weaponList
          .add(new Weapon(weaponList.size(), "One-Handed Axe", WeaponType.AXE, 2, 100, 100, false));
        weaponList
          .add(new Weapon(weaponList.size(), "Two-Handed Axe", WeaponType.AXE, 3, 100, 100, true));
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }
}
