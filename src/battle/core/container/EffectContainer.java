package battle.core.container;

import java.util.*;
import battle.core.effect.*;

public class EffectContainer {
    private List<Effect> effects;
    public EffectContainer() {
        this.effects = new ArrayList<>();
    }

    public EffectContainer add(Effect effect) {
        this.effects.add(effect);
        return this;
    }
    public List<Effect> getEffects() {
        return this.effects;
    }

    public void applyAll() {
        for (Effect effect : this.effects) {
            effect.apply();
        }
        this.effects.clear();
    }
    public void applyOne() {
        if (!this.effects.isEmpty()) {
            Effect effect = this.effects.remove(0);
            effect.apply();
        }
    }
}