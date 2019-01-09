package honkytonky.objects;

public class Player extends Actor
{

    private int currentRoomID;
    private Weapon weapon;

    public Player(String name, int maxHP, int x, int y, Weapon weapon)
    {
        super(name, maxHP, x, y);

        this.currentRoomID = 0;
        this.weapon = weapon;
    }

    public int getCurrentRoomID()
    {
        return currentRoomID;
    }

    public void setCurrentRoomID(int currentRoomID)
    {
        this.currentRoomID = currentRoomID;
    }
}
