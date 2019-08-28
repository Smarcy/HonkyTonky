package honkytonky.objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Item {

    Item() {}

    @XmlElement
    private int id;

    @XmlElement
    private String name;

    @XmlTransient
    private int value;
}
