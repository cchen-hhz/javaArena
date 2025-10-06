package battle.core;

import java.util.*;
import battle.enums.ElementType;

public class CharacterGenerator {
    public static Character gen(int option) {
        String name = NameGenerator.generateName();
        Map<String, Object> stats;
        if (option == 1) {
            stats = Stat.BasicStats;
        } else {
            stats = Stat.randomStats();
        }
        return new Character(name, 
            (ElementType)stats.get("elementType"),
            (int)stats.get("maxHealth"),
            (int)stats.get("basicDamage"),
            (int)stats.get("energy")
        );
    }
}

class NameGenerator {
    private static final List<String> firstNames = Arrays.asList("Aring", "Borin", "Cirin", "wowow");
    private static final List<String> lastNames = Arrays.asList("bigfan", "lowyuy", "ubuntu", "koishi");

    public static String generateName() {
        Random rand = new Random();
        String firstName = firstNames.get(rand.nextInt(firstNames.size()));
        String lastName = lastNames.get(rand.nextInt(lastNames.size()));
        return firstName + " " + lastName;
    }
}

class Stat {
    public static Map<String, Object> BasicStats = Map.of(
        "elementType", ElementType.FIRE,
        "maxHealth", 100,
        "basicDamage", 5,
        "energy", 50
    );
    public static Map<String, Object> randomStats() {
        Random rand = new Random();
        ElementType[] elements = ElementType.values();
        ElementType elementType = elements[rand.nextInt(elements.length)];
        int maxHealth = 80 + rand.nextInt(41); 
        int basicDamage = 3 + rand.nextInt(8); 
        int energy = 40 + rand.nextInt(21); 
        return Map.of(
            "elementType", elementType,
            "maxHealth", maxHealth,
            "basicDamage", basicDamage,
            "energy", energy
        );
    }
}