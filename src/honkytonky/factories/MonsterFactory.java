package honkytonky.factories;

import static honkytonky.objects.Monster.MonsterType.BUTTERFLY;
import static honkytonky.objects.Monster.MonsterType.ZOMBIE;

import honkytonky.objects.Monster;
import java.util.ArrayList;
import java.util.List;

public class MonsterFactory
{
    private List<Monster> monsterList = new ArrayList<>();

    public MonsterFactory()
    {
        monsterList.add(new Monster("Zombie", 15, 1, 1, 2, 1, 70, ZOMBIE));
        monsterList.add(new Monster("Butterfly", 10, 2, 2, 1, 2, 100, BUTTERFLY));
    }

    public List<Monster> getMonsterList()
    {
        return monsterList;
    }
}
