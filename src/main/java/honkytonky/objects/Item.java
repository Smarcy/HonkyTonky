package honkytonky.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
abstract class Item {

    private int id;
    private String name;
    private int value;
}
