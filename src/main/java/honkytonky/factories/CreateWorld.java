package honkytonky.factories;

public class CreateWorld {

    /**
     * reads all needed CSV files to populate the World with Monsters, Doors, Merchants
     */
    public static void populateWorld() {
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
        DoorFactory.getInstance().wipeDoors();
        MonsterFactory.getInstance().wipeMonsters();
        MerchantFactory.getInstance().wipeMerchants();
        RoomFactory.getInstance().wipeRooms();
    }
}
