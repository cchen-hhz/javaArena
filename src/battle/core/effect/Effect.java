package battle.core.effect;

import java.util.Map;

public abstract class Effect {
    public abstract void apply();
    public abstract Effect of(Map<String, Object> params);
    protected abstract void validation(Map<String, Object> params);
}