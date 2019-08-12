package honkytonky.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Door {

    private int id;
    private String name;
    private Room targetRoom;
}
