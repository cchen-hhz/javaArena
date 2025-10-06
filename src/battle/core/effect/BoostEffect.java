package battle.core.effect;

import java.util.List;
import java.util.Map;
import battle.core.Character;

public class BoostEffect extends Effect {
    private int boostAmount;
    private List<Character> targets;
    
    private BoostEffect() {}
    private BoostEffect(Map<String, Object> params) {
        this.boostAmount = (Integer) params.get("value");
        @SuppressWarnings("unchecked")
        List<Character> targetList = (List<Character>) params.get("targets");
        this.targets = targetList;
    }

    public static final BoostEffect INSTANCE = new BoostEffect();

    @Override
    /**
     * boostEffect factorial method
     * @param params params map
     * - "targets": List<Character>
     * - "value": Integer
     * @return BoostEffect
     */
    public BoostEffect of(Map<String, Object> params) {
        try {
            validation(params);
            return new BoostEffect(params);
        } catch (Exception e) {
            System.err.println("Invalid parameters for BoostEffect");
            return new BoostEffect();
        }
    }

    @Override
    protected void validation(Map<String, Object> params) {
        if (params.get("value") instanceof Integer
            && params.get("targets") instanceof List<?> ) {
        }
        else {
            throw new IllegalArgumentException("Invalid parameters for BoostEffect");
        }
    }
    
    public void apply() {
        for (Character target : this.targets) {
            System.out.println("boosts " + target.getName() + " by " + this.boostAmount + " strength" + " using " + this.boostAmount + " energy.");
            System.out.println();
            target.boost(this.boostAmount);
        }
    }
}