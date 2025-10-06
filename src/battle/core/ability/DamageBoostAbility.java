package battle.core.ability;

import java.util.Map;
import java.util.List;
import battle.core.Character;
import battle.core.effect.EffectProvider;

public class DamageBoostAbility implements Ability {
    private String NAME = "DamageBoost";
    private int DEFAULT_COST = 15;
    private int BOOST_AMOUNT = 5;
    private EffectProvider provider = null;

    public static final DamageBoostAbility INSTANCE = new DamageBoostAbility();
    private DamageBoostAbility() {}
    private DamageBoostAbility(Map<String, Object> params) {
        if (params.containsKey("BOOST_AMOUNT")) {
            this.BOOST_AMOUNT = (int) params.get("BOOST_AMOUNT");
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
        return new DamageBoostAbility(params);
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
            System.err.println("EffectProvider is null in DamageBoostAbility.");
            return -1;
        }
        if (!source.requestEnergy(getCost())) {
            return -1;
        }
        // Assuming DamageBoost should affect the source character
        provider.generator("damageBoost", Map.of("targets", List.of(source), "value", BOOST_AMOUNT));
        return 0;
    }
}
