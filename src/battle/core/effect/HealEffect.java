package battle.core.effect;

import java.util.List;
import java.util.Map;
import battle.core.Character;

public class HealEffect extends Effect {
    private int healAmount;
    private List<Character> targets;
    
    private HealEffect() {}
    private HealEffect(Map<String, Object> params) {
        this.healAmount = (Integer) params.get("value");
        @SuppressWarnings("unchecked")
        List<Character> targetList = (List<Character>) params.get("targets");
        this.targets = targetList;
    }
    
    public static final HealEffect INSTANCE = new HealEffect();

    @Override
    /**
     * healEffect factorial method
     * @param params params map
     * - "targets": List<Character>
     * - "value": Integer
     * @return HealEffect
     */
    public HealEffect of(Map<String, Object> params) {
        try {
            validation(params);
            return new HealEffect(params);
        } catch (Exception e) {
            System.err.println("Invalid parameters for HealEffect");
            return new HealEffect();
        }
    }

    @Override
    protected void validation(Map<String, Object> params) {
        if (params.get("value") instanceof Integer
            && params.get("targets") instanceof List<?> ) {
        }
        else {
            throw new IllegalArgumentException("Invalid parameters for HealEffect");
        }
    }
    
    public void apply() {
        for (Character target : this.targets) {
            System.out.println(" heals " + target.getName() + " for " + this.healAmount + " HP." + "Using " + this.healAmount + " energy.");
            System.out.println();
            target.getHeal(this.healAmount);
        }
    }
}