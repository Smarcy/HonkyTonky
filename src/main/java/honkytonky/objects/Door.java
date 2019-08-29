package honkytonky.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Door {

    private final int id;
    private final String name;
    private final Room sourceRoom;
    private final Room targetRoom;
}
