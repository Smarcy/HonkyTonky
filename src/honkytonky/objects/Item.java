package honkytonky.objects;



public class Item
{
    private int id;
    private String name;

    Item(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
