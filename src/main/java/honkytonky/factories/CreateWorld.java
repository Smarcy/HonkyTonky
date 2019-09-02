package honkytonky.factories;

public class CreateWorld {

    /**
     * reads all needed CSV files to populate the World with Monsters, Doors, Merchants
     */
    public static void populateWorld() {
        //@formatter:off
       // roomFactory            = new RoomFactory();
        //doorFactory            = new DoorFactory();
        //monsterFactory         = new MonsterFactory();
        //merchantFactory        = new MerchantFactory();
        //weaponFactory          = new WeaponFactory();
        //armorFactory           = new ArmorFactory();
        //potionFactory          = new PotionFactory();
        //playerDialogController = new PlayerDialogController();
        //merchantController     = new MerchantController();
       // battleController       = new BattleController();
       // playerController       = new PlayerController();
        //@formatter:on

        RoomFactory.getInstance().createRooms();
        DoorFactory.getInstance().createDoors();
        MonsterFactory.getInstance().createMonsters();
        WeaponFactory.getInstance().createWeapons();
        ArmorFactory.getInstance().createArmors();
        PotionFactory.getInstance().createPotions();
        MerchantFactory.getInstance().createMerchants();

    }

    /**
     * delete everything in the world - only useable if the very next methodcall is 'populateWorld()' !!!
     */
    public static void wipeWorld() {
//        doorFactory.wipeDoors();
//        monsterFactory.wipeMonsters();
//        merchantFactory.wipeMerchants();
//        roomFactory.wipeRooms();
    }
}
