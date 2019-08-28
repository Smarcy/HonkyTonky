package honkytonky.controller;

import honkytonky.objects.Player;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBController {


    public static void ObjectToXML(Player player) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Player.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(player, new File("./player.xml"));
    }

}
