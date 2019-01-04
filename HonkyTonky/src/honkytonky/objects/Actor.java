package honkytonky.objects;

abstract class Actor
{
    private int id;
    private int hp;
    private String name;

    Actor(String name)
    {
        this.name = name;
    }
}
