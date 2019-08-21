package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int id;
    private final String name;
    private final List<Door> doors;
    private boolean hasLivingMonster;
    private boolean hasMerchant;
    private Monster presentMonster;
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

    @Override
    public String toString() {
        return this.name;
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
        return name;
    }
}
