package battle.core;
import battle.enums.*;

// Provides base stats (allows future polymorphism)
public interface CombatStatProvider {
    void getAttack(int amount); 
    void getHeal(int amount);
    int getDamage();
    void boost(int amount);

    void getEnergy(int amount);
    boolean requestEnergy(int amount);

    boolean isAlive();

    ElementType getElementType();

    void addAbility(battle.core.ability.Ability ability);
    void addStatus(StatusProvider status);
    void tickStatus();

    void setGroup(int group);
    void earnEnergy(int amount);
}