package honkytonky.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Door {

    /**
     * ID of the Door
     */
    private final int id;
    /**
     * name of the Door
     */
    private final String name;
    /**
     * Room from that Player can enter the Door
     */
    private final Room sourceRoom;
    /**
     * Room where Player gets out after using the Door
     */
    private final Room targetRoom;
}
