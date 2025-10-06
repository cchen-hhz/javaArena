package battle.enums;

import java.util.Map;
import battle.core.Character;
import battle.core.effect.EffectProvider;


enum StatusType {
    // Some basic Status
    HEAL, ENERGY,
    // Some extra Status
    POISON, STUN, WEAKEN, STRONG
}

public class StatusProvider {
    private final StatusType type;
    private int value;
    private int duration;
    private Character source;

    private Map<String, Object> extraData;
    private StatusProvider(StatusType type, int value, int duration, Character source, Map<String, Object> extraData) {
        this.type = type;
        this.value = value;
        this.duration = duration;
        this.source = source;   
        this.extraData = extraData;
    }

    // getters and setters
    public StatusType getType() {
        return type;
    }
    public int getValue() {
        return value;
    }   
    public void setValue(int value) {
        this.value = value;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public Map<String, Object> getExtraData() {
        return extraData;
    }

    // Static factory methods
    public static StatusProvider energyCondition(Character source, int value, int duration) {
        return new StatusProvider(StatusType.ENERGY, value, duration, source, null);
    }
    public static StatusProvider poisonCondition(Character source, int value, int duration) {
        return new StatusProvider(StatusType.POISON, value, duration, source, null);
    }

    // apply method
    public void apply(EffectProvider provider) {
        if( this.type == StatusType.POISON ) {
            provider.generator("poison", Map.of(
                "target", this.source,
                "value", this.value
            ));
        }
        else if( this.type == StatusType.HEAL ) {
            provider.generator("heal", Map.of(
                "target", this.source,
                "value", this.value
            ));
        }
    }
}