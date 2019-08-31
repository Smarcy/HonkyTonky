package honkytonky.factories;

public class CreateWorld {


    private static MerchantFactory merchantFactory;
    private static MonsterFactory monsterFactory;
    private static DoorFactory doorFactory;
    private static RoomFactory roomFactory;

    /**
     * reads all needed CSV files to populate the World with Monsters, Doors, Merchants
     *
     * @param roomFactory instance of RoomFactory needed for other factories
     */
    public static void populateWorld(RoomFactory roomFactory) {
        CreateWorld.roomFactory = roomFactory;
        doorFactory = new DoorFactory(roomFactory);
        monsterFactory = new MonsterFactory(roomFactory);
        merchantFactory = new MerchantFactory(roomFactory);
    }

    public static void wipeWorld() {
        doorFactory.wipeDoors();
        monsterFactory.wipeMonsters();
        merchantFactory.wipeMerchants();
        roomFactory.wipeRooms();
    }

}
