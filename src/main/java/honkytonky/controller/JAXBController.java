package honkytonky.controller;

import honkytonky.objects.Player;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public static Player unmarshall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Player.class);
        return (Player) context.createUnmarshaller()
          .unmarshal(new FileReader("./player.xml"));
    }

}
