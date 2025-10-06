package battle.core.context;

// Using random stragety now
import java.util.*;
import battle.core.Character;
import battle.core.ability.Ability;

public class BotController {

    private static final Random random = new Random();

    public static void apply(Character source, List<Character> allCharacters) {
        if (source == null || allCharacters == null || allCharacters.isEmpty()) {
            return;
        }
        if(source.isAlive() == false) {
            return;
        }

        List<Ability> abilities = source.getAbilities();
        if (abilities.isEmpty()) {
            System.out.println(source.getName() + " has no abilities to use.");
            return;
        }

        int numberOfActions = random.nextInt(4) + 1;
        System.out.println(source.getName() + " will perform " + numberOfActions + " action(s).");

        for (int i = 0; i < numberOfActions; i++) {
            Ability abilityToUse = abilities.get(random.nextInt(abilities.size()));

            Character target = allCharacters.get(random.nextInt(allCharacters.size()));

            String abilityName = abilityToUse.getName();
            if (abilityName.equals("Attack") || abilityName.equals("Heal") || abilityName.equals("DamageBoost")) {
                abilityToUse.apply(source, target);
            } else {
                System.err.println("Invalid ability format for BotController: " + abilityName);
                return;
            }
        }
    }
}