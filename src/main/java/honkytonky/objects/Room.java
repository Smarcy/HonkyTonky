package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {

    @XmlTransient
    private final int id;

    @XmlElement
    private final String name;

    @XmlTransient
    private final List<Door> doors;

    @XmlElement
    private boolean hasLivingMonster;

    @XmlTransient
    private boolean hasMerchant;

    @XmlTransient
    private Monster presentMonster;

    @XmlTransient
    private Merchant presentMerchant;

    public Room(int id, String name) {
        //@formatter:off
        this.id                 = id;
        this.name               = name;
        this.hasLivingMonster   = false;
        this.hasMerchant        = false;
        this.doors              = new ArrayList<>();
        //@formatter:on
    }

    public void addDoor(Door door) {
        doors.add(door);
    }

    public void addMonster(Monster monster) {
        this.presentMonster = monster;
        this.hasLivingMonster = true;
    }

    public void addMerchant(Merchant merchant) {
        if (!hasMerchant) {
            this.presentMerchant = merchant;
            hasMerchant = true;
        } else {
            throw new IllegalArgumentException(this.name + " already has a merchant!");
        }
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void listDoorOptions() {
        System.out.println("Where would you like to go?\n");
        System.out.println("There are doors to the following rooms:\n");

        int i = 1;

        for (Door door : doors) {
            System.out.println(i + ") " + door.getTargetRoom());
            i++;
        }
    }

    public boolean hasLivingMonster() {
        return this.hasLivingMonster;
    }

    public boolean hasMerchant() {
        return this.hasMerchant;
    }

    public Monster getPresentMonster() {
        return presentMonster;
    }

    public Merchant getPresentMerchant() {
        return this.presentMerchant;
    }

    public void monsterKilled() {
        this.hasLivingMonster = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
