package honkytonky.objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Item {

    /**
     * ID of the Item
     */
    @XmlElement
    private int id;
    /**
     * name of the Item
     */
    @XmlElement
    private String name;
    /**
     * selling/buying price of the Item
     */
    @XmlTransient
    private int value;
}
