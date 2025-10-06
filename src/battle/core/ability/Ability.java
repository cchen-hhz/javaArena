package battle.core.ability;

import battle.core.Character;
import java.util.Map;

public interface Ability {
    int apply(Character source, Character... targets);
    String getName();
    int getCost();
    Ability of(Map<String, Object> params);
}
