package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_CYAN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;

import honkytonky.factories.ArmorFactory;
import honkytonky.factories.CreateWorld;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.RoomFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Armor;
import honkytonky.objects.Item;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBController {

    /**
     * save the current state of the Player to XML
     *
     * @param player the player Object
     */
    public static void PlayerToXML(Player player) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Player.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(player, new File("./player.xml"));
    }

    /**
     * save the current state of all Rooms to XML
     */
    public static void RoomsToXML() throws JAXBException {
        RoomFactory roomFactory = CreateWorld.getRoomFactory();
        JAXBContext context = JAXBContext.newInstance(RoomFactory.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(roomFactory, new File("./rooms.xml"));
    }

    /**
     * load the last saved state of the Player from XML
     *
     * @return a dummy Player object
     * @throws IOException file not found
     */
    public static Player unmarshallPlayer() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Player.class);
        return (Player) context.createUnmarshaller()
          .unmarshal(new FileReader("./player.xml"));
    }

    /**
     * load the last saved state of all Rooms from XML
     *
     * @return RoomFactory containing all Rooms with Merchant/Monster Info
     * @throws IOException file not found
     */
    public static RoomFactory unmarshallRooms() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(RoomFactory.class);
        return (RoomFactory) context.createUnmarshaller()
          .unmarshal(new FileReader("./rooms.xml"));
    }

    /**
     * Loads Rooms from most recent savestate and adjusts all rooms that are already in the game according to this information The savestate contains
     * info about hasMonster and hasMerchant, so Monsters and Merchants don't respawn after reload
     */
    public static RoomFactory loadRooms() throws IOException, JAXBException {
        RoomFactory roomFactory = CreateWorld.getRoomFactory();
        RoomFactory tmpRoomFactory;

        tmpRoomFactory = JAXBController.unmarshallRooms();

        for (Room room : tmpRoomFactory.getRooms()) {
            Room realRoom = roomFactory.getRoomByName(room.getName());
            realRoom.setHasMerchant(room.isHasMerchant());
            realRoom.setHasLivingMonster(room.isHasLivingMonster());
        }
        System.out.println(ANSI_CYAN + "\nRooms succesfully loaded!" + ANSI_RESET);
        return roomFactory;
    }

    /**
     * Loads Player from most recent savestate and prepares a fully compatible Player object
     */
    public static Player loadPlayer() throws JAXBException, IOException {
        WeaponFactory weaponFactory = CreateWorld.getWeaponFactory();
        ArmorFactory armorFactory = CreateWorld.getArmorFactory();
        RoomFactory roomFactory = CreateWorld.getRoomFactory();
        PotionFactory potionFactory = CreateWorld.getPotionFactory();
        PlayerController playerController = CreateWorld.getPlayerController();
        BattleController battleController = CreateWorld.getBattleController();

        Player dummy = JAXBController.unmarshallPlayer();

        // Extract real Items from "dummys"
        Weapon dummyWeapon = weaponFactory.getWeaponByName(dummy.getWeapon().getName());
        Armor dummyArmor = armorFactory.getArmorByName(dummy.getArmor().getName());
        Room dummyRoom = roomFactory.getRoomByName(dummy.getCurrentRoom().getName());

        Player player = new Player(dummy.getName(), dummy.getMaxHP(), dummyWeapon, dummyArmor, dummyRoom);
        player.setHp(dummy.getHp());
        player.setExperience(dummy.getExperience());
        player.setLevel(dummy.getLevel());
        player.setGold(dummy.getGold());
        player.setDamage(dummy.getDamage());

        // Retrieve Potions
        int i = 0;
        for (Item item : dummy.getInventory()) {
            if (item instanceof Potion) {
                Potion dummyPotion = potionFactory.getPotionByName(item.getName());
                Integer potionValue = dummy.getPlayersPotions().get(dummy.getInventory().get(i));
                player.getInventory().add(dummyPotion);
                player.getPlayersPotions().put(dummyPotion, potionValue);
            } else if (!(item.equals(dummy.getArmor())) && !(item.equals(dummy.getWeapon()))) {
                player.getInventory().add(item);
            }
            i++;
        }

        playerController.setPlayer(player);
        battleController.setPlayer(player);

        System.out.print(ANSI_CYAN + "Player succesfully loaded!" + ANSI_RESET);
        return player;
    }
}
