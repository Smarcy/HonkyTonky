package honkytonky.objects;


abstract class Item {

    private int id;
    private String name;

    Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString()
        {
            return this.name;
        }
}
