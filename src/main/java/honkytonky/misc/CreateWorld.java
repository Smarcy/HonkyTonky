package honkytonky.misc;

import honkytonky.factories.DoorFactory;
import honkytonky.factories.MerchantFactory;
import honkytonky.factories.MonsterFactory;
import honkytonky.factories.RoomFactory;

public class CreateWorld {

    /**
     * reads all needed CSV files to populate the World with Monsters, Doors, Merchants
     * @param roomFactory instance of RoomFactory needed for other factories
     */
    public static void populateWorld(RoomFactory roomFactory) {
        DoorFactory doorFactory = new DoorFactory(roomFactory);
        MonsterFactory monsterFactory = new MonsterFactory(roomFactory);
        MerchantFactory merchantFactory = new MerchantFactory(roomFactory);
    }

}
