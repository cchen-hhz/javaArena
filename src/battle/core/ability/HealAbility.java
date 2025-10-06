package battle.core.ability;

import java.util.Map;
import java.util.List;
import battle.core.Character;
import battle.core.effect.EffectProvider;

public class HealAbility implements Ability {
    private final String NAME = "Heal";
    private int DEFAULT_COST = 10;
    private int DEFAULT_AMOUNT = 5;
    private EffectProvider provider = null;

    public static final HealAbility INSTANCE = new HealAbility();

    private HealAbility() {}
    private HealAbility(Map<String, Object> params) {
        if (params.containsKey("DEFAULT_AMOUNT")) {
            this.DEFAULT_AMOUNT = (int) params.get("DEFAULT_AMOUNT");
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
        return new HealAbility(params);
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
            System.err.println("EffectProvider is null in HealAbility.");
            return -1;
        }
        if (!source.requestEnergy(getCost())) {
            return -1;
        }
        for (Character target : targets) {
            provider.generator("heal", Map.of("targets", List.of(target), "value", DEFAULT_AMOUNT)) ;
        }
        return 0;
    }
}
