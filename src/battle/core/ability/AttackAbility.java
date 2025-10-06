package battle.core.ability;

import java.util.Map;
import java.util.List;
import battle.core.Character;
import battle.core.effect.EffectProvider;

public class AttackAbility implements Ability {
    private final String NAME = "Attack";
    private int DEFAULT_COST = 5;
    private int DEFAULT_DAMAGE = 15;
    private EffectProvider provider = null;

    public static final AttackAbility INSTANCE = new AttackAbility();

    private AttackAbility() {}
    private AttackAbility(Map<String, Object> params) {
        if (params.containsKey("DEFAULT_DAMAGE")) {
            this.DEFAULT_DAMAGE = (int) params.get("DEFAULT_DAMAGE");
        }
        if (params.containsKey("DEFAULT_COST")) {
            this.DEFAULT_COST = (int) params.get("DEFAULT_COST");
        }
        if (params.containsKey("PROVIDER")) {
            this.provider = (EffectProvider) params.get("PROVIDER");
        }
    }

    @Override
    public Ability of(Map<String, Object> params) {
        return new AttackAbility(params);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getCost() {
        return DEFAULT_COST;
    }

    @Override
    public int apply(Character source, Character... targets) {
        if( this.provider==null ){
            System.err.println("EffectProvider is null in AttackAbility.");
            return -1;
        }
        if (!source.requestEnergy(getCost())) {
            return -1;
        }
        if (targets.length == 0) {
            return -1;
        }
        for (Character target : targets) {
            provider.generator("damage", Map.of("targets", List.of(target), "value", DEFAULT_DAMAGE));
        }
        return 0;
    }
}
