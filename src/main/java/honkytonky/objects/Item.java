package honkytonky.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Item {

    private int id;
    private String name;
}
