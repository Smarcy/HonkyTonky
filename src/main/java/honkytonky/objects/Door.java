package honkytonky.objects;

public class Door {

    private int id;
    private String name;
    private Room targetRoom;

    public Door(int id, String name, Room targetRoom) {
        this.id = id;
        this.name = name;
        this.targetRoom = targetRoom;
    }

    public Room getTargetRoom() {
        return targetRoom;
    }

    public String getName() {
        return name;
    }

}
