package honkytonky.objects;

public class Potion extends Item {

    private int amount;
    private PotionType type;

    public Potion(int id, String name, int amount, PotionType type) {

        super(id, name);

        this.amount = amount;
        this.type   = type;
    }

    public int getAmount() {
        return this.amount;
    }

    public enum PotionType {
        HEALTH,
        MANA,
        SPEED,
        DEFENSE
    }
}
