package battle.core.effect;

import java.util.Map;
import battle.core.container.EffectContainer;

public class EffectProvider {
    private EffectContainer container;
    private Map<String, Object> effects = Map.of(
        "heal", HealEffect.INSTANCE,
        "damage", DamageEffect.INSTANCE,
        "damageBoost", BoostEffect.INSTANCE
    );
    public EffectProvider(EffectContainer container) {
        this.container = container;
    }
    public int generator(String name, Map<String, Object> params) {
        if( !effects.containsKey(name) ) {
            System.err.println("Effect " + name + " not found.");
            return -1;
        }
        if( container == null ) {
            System.err.println("EffectContainer is null.");
            return -1;
        }

        Effect effect = (Effect) effects.get(name);
        this.container.add(effect.of(params));
        this.container.applyAll();
        return 0;
    }
}