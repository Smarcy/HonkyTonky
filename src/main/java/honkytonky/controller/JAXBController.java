package honkytonky.controller;

import honkytonky.factories.RoomFactory;
import honkytonky.objects.Player;
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
     *
     * @param roomFactory roomfactory containing all rooms with Merchant/Monster Info
     */
    public static void RoomsToXML(RoomFactory roomFactory) throws JAXBException {
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

}
