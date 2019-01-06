package honkytonky.objects;

public class Player extends Actor
{

    private int currentRoomID;

    public Player(String name, int maxHP, int x, int y)
    {
        super(name, maxHP, x, y);

        this.currentRoomID = 0;
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
