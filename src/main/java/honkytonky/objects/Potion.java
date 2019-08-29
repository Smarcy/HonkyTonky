package honkytonky.objects;

import honkytonky.enumtypes.PotionType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Potion extends Item {

    @XmlTransient
    private final int amount;

    @XmlTransient
    private final PotionType type;

    //Needed for JAXB
    public Potion() {
        super();
        amount = 0;
        type = null;
    }

    /**
     * create a Potion in the game
     *
     * @param id if of Potion
     * @param name name of the Potion
     * @param amount amount the Potion provides when used (e.g. Health)
     * @param value selling/buying price of the Potion
     * @param type Type of the Potion
     */
    public Potion(int id, String name, int amount, int value, PotionType type) {
        super(id, name, value);

        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
