package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {

    /**
     * ID of the Room
     */
    @XmlTransient
    private final int id;
    /**
     * name of the Room
     */
    @XmlElement
    private final String name;
    /**
     * contains all Doors attached to this Room
     */
    @XmlTransient
    private final List<Door> doors;
    /**
     * true if the Room contains a Monster that is alive
     */
    @XmlElement
    private boolean hasLivingMonster;
    /**
     * true if the Room contains a (living) Merchant
     */
    @XmlElement
    private boolean hasMerchant;
    /**
     * the actual Monster in this Room
     */
    @XmlTransient
    private Monster presentMonster;
    /**
     * the actual Merchant in this Room
     */
    @XmlTransient
    private Merchant presentMerchant;

    /**
     * default constructor needed for persisting with JAXB
     */
    public Room() {
        id = 0;
        name = null;
        doors = null;
    }

    /**
     * create a Room in the game
     *
     * @param id id of the Room
     * @param name name of the Room
     */
    public Room(int id, String name) {
        //@formatter:off
        this.id                 = id;
        this.name               = name;
        this.hasLivingMonster   = false;
        this.hasMerchant        = false;
        this.doors              = new ArrayList<>();
        //@formatter:on
    }

    /**
     * add a Door to this Room
     *
     * @param door the Door to add
     */
    public void addDoor(Door door) {
        assert doors != null;
        doors.add(door);
    }

    /**
     * add a Monster to this Room
     *
     * @param monster the Monster to add
     */
    public void addMonster(Monster monster) {
        this.presentMonster = monster;
        this.hasLivingMonster = true;
    }

    /**
     * add a Merchant to this Room
     *
     * @param merchant the Merchant to add
     */
    public void addMerchant(Merchant merchant) {
        if (!hasMerchant) {
            this.presentMerchant = merchant;
            hasMerchant = true;
        } else {
            throw new IllegalArgumentException(this.name + " already has a merchant!");
        }
    }

    /**
     * return all Doors of this Room
     *
     * @return List of all Doors of this Room
     */
    public List<Door> getDoors() {
        return doors;
    }

    /**
     * prints all Doors the Player can take from this Room
     */
    public void listDoorOptions() {
        System.out.println("Where would you like to go?\n");
        System.out.println("There are doors to the following rooms:\n");

        int i = 1;

        assert doors != null;
        for (Door door : doors) {
            System.out.println(i + ") " + door.getTargetRoom());
            i++;
        }
    }

    /**
     * check if there is a Monster in this Room
     *
     * @return true if this Room has a (living) Monster
     */
    public boolean hasLivingMonster() {
        return this.hasLivingMonster;
    }

    /**
     * check if there is a Merchant in this Room
     *
     * @return true if there is a Merchant
     */
    public boolean hasMerchant() {
        return this.hasMerchant;
    }

    /**
     * returns the actual Monster this Room contains
     *
     * @return the Monster in this Room
     */
    public Monster getPresentMonster() {
        return presentMonster;
    }

    /**
     * returns the actual Merchant this Room contains
     *
     * @return the Merchant in this Room
     */
    public Merchant getPresentMerchant() {
        return this.presentMerchant;
    }

    /**
     * sets the Monsters status in this Room to 'killed'
     */
    public void monsterKilled() {
        this.hasLivingMonster = false;
    }

    /**
     * returns the name of this Room
     *
     * @return the Name of this Room
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
