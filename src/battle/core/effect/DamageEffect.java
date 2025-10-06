package battle.core.effect;

import java.util.List;
import java.util.Map;

import battle.core.Character;

public class DamageEffect extends Effect {
    private int damageAmount;
    private List<Character> targets;
    private DamageEffect() {}
    private DamageEffect(Map<String, Object> params) {
        this.damageAmount = (Integer) params.get("value");
        @SuppressWarnings("unchecked")
        List<Character> targetList = (List<Character>) params.get("targets");
        this.targets = targetList;
    }
    public static final DamageEffect INSTANCE = new DamageEffect();
    @Override
    protected void validation(Map<String, Object> params) {
        if (params.get("value") instanceof Integer
            && params.get("targets") instanceof List<?> ) {
        }
        else {
            throw new IllegalArgumentException("Invalid parameters for DamageEffect");
        }
    }
    @Override 
    /**
     * DamageEffect factorial method
     * @param params params map
     * - "targets": List<Character>
     * - "value": Integer
     * @return DamageEffect
     */
    public DamageEffect of(Map<String, Object> params) {
        try {
            validation(params);
            return new DamageEffect(params);
        } catch (Exception e) {
            System.err.println("Invalid parameters for DamageEffect");
            return new DamageEffect();
        }
    }

    @Override
    public void apply() {
        for (Character target : this.targets) {
            System.out.println("deals " + this.damageAmount + " damage to " + target.getName() + "." + "Using " + this.damageAmount + " energy.");
            System.out.println();
            target.getAttack(this.damageAmount);
        }
    }
}