package honkytonky.factories;

import honkytonky.controller.BattleController;
import honkytonky.controller.MerchantController;
import honkytonky.controller.PlayerController;
import honkytonky.controller.PlayerDialogController;

public class CreateWorld {


    private static MerchantFactory merchantFactory;
    private static MonsterFactory monsterFactory;
    private static DoorFactory doorFactory;
    private static RoomFactory roomFactory;
    private static WeaponFactory weaponFactory;
    private static ArmorFactory armorFactory;
    private static PotionFactory potionFactory;
    private static PlayerDialogController playerDialogController;
    private static MerchantController merchantController;
    private static BattleController battleController;
    private static PlayerController playerController;

    /**
     * reads all needed CSV files to populate the World with Monsters, Doors, Merchants
     */
    public static void populateWorld() {
        //@formatter:off
        roomFactory            = new RoomFactory();
        doorFactory            = new DoorFactory(roomFactory);
        monsterFactory         = new MonsterFactory(roomFactory);
        merchantFactory        = new MerchantFactory(roomFactory);
        weaponFactory          = new WeaponFactory();
        armorFactory           = new ArmorFactory();
        potionFactory          = new PotionFactory();
        playerDialogController = new PlayerDialogController();
        merchantController     = new MerchantController();
        battleController       = new BattleController();
        playerController       = new PlayerController();
        //@formatter:on
    }

    public static void wipeWorld() {
        doorFactory.wipeDoors();
        monsterFactory.wipeMonsters();
        merchantFactory.wipeMerchants();
        roomFactory.wipeRooms();
    }

    public static MerchantFactory getMerchantFactory() {
        return merchantFactory;
    }

    public static MonsterFactory getMonsterFactory() {
        return monsterFactory;
    }

    public static DoorFactory getDoorFactory() {
        return doorFactory;
    }

    public static RoomFactory getRoomFactory() {
        return roomFactory;
    }

    public static WeaponFactory getWeaponFactory() {
        return weaponFactory;
    }

    public static ArmorFactory getArmorFactory() {
        return armorFactory;
    }

    public static PotionFactory getPotionFactory() {
        return potionFactory;
    }

    public static PlayerDialogController getPlayerDialogController() {
        return playerDialogController;
    }

    public static MerchantController getMerchantController() {
        return merchantController;
    }

    public static BattleController getBattleController() {
        return battleController;
    }

    public static PlayerController getPlayerController() {
        return playerController;
    }
}
