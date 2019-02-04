package honkytonky.factories;

import static honkytonky.objects.Monster.MonsterType.BUTTERFLY;
import static honkytonky.objects.Monster.MonsterType.ZOMBIE;

import honkytonky.objects.Monster;
import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {

    private List<Monster> monsterList = new ArrayList<>();

    public MonsterFactory() {
        monsterList.add(new Monster("Peanut Butter Zombie", 15, 1, 1, 70, ZOMBIE));
        monsterList.add(new Monster("Butterfly", 20, 2, 2, 100, BUTTERFLY));
    }

    Monster getMonsterByName(String name) {
        for (Monster monster : monsterList) {
            if (monster.toString().equals(name)) {
                return monster;
            }
        }

        return null;
    }
}
