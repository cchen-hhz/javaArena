package battle.core.ability;

import java.util.HashMap;
import java.util.Map;

public class AbilityFactory {
    private static final AbilityFactory INSTANCE = new AbilityFactory();

    private final Map<String, Ability> primitiveAbilities = new HashMap<>();
    
    private AbilityFactory() {
        primitiveAbilities.put("Heal", HealAbility.INSTANCE);
        primitiveAbilities.put("Attack", AttackAbility.INSTANCE);
        primitiveAbilities.put("DamageBoost", DamageBoostAbility.INSTANCE);
    }

    public static AbilityFactory getInstance() {
        return INSTANCE;
    }

    public Ability create(String name, Map<String, Object> params) {
        if (primitiveAbilities.containsKey(name)) {
            return primitiveAbilities.get(name).of(params);
        } 
        throw new IllegalArgumentException("Ability not found: " + name);
    }
}
