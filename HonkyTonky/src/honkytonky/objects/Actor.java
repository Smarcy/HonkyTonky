package honkytonky.objects;

abstract class Actor
{
    private int id;
    private String name;

    Actor(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
