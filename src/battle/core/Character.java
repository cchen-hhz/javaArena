package battle.core;

import battle.core.ability.Ability;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import battle.enums.*;

public class Character implements CombatStatProvider {
    //basic fields
    private UUID id;
    private String name;
    private int group;

    private ElementType elementType;
    private int health, maxHealth;
    private int Damage;
    private double DamageMultiplier;
    private int energy, maxEnergy;
    
    private final List<Ability> abilities = new ArrayList<>();
    List<StatusProvider> status;
    public Character(String name, ElementType elementType, int maxHealth, int basicDamage, int energy) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Health must be positive");
        }
        if (basicDamage < 0) {
            throw new IllegalArgumentException("Basic damage cannot be negative");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.elementType = elementType;
        this.health = this.maxHealth = maxHealth;
        this.Damage = basicDamage;
        this.DamageMultiplier = 1.0;
        this.energy = this.maxEnergy = energy;
        this.status = new ArrayList<>();
        this.group = -1;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public void setGroup(int group) {
        this.group = group;
    }
    public int getGroup() {
        return this.group;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void takeDamage(int amount) {
        amount = Math.max(0, amount);
        this.health = Math.max(0, this.health - amount);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void addAbility(Ability ability) {
        if (ability == null) {
            throw new IllegalArgumentException("Ability cannot be null");
        }
        if (abilities.contains(ability)) return;
        System.out.println("Character " + name + " learned ability:" + ability.getName());
        abilities.add(ability);
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    
    @Override
    public void getAttack(int amount) {
        amount = Math.max(0, amount);
        this.health = Math.max(0, this.health - amount);
    }
    @Override
    public void getHeal(int amount) {
        if(!isAlive()) return;
        amount = Math.max(0, amount);
        this.health = Math.min(this.maxHealth, this.health + amount);
    }
    @Override
    public int getDamage() {
        return (int)Math.round(this.Damage * this.DamageMultiplier);
    }
    @Override
    public void getEnergy(int amount) {
        amount = Math.max(0, amount);
        this.energy = Math.min(this.maxEnergy, this.energy + amount);
    }
    @Override
    public ElementType getElementType() {
        return this.elementType;
    }
    @Override
    public void boost(int amount) {
        amount = Math.max(0, amount);
        this.Damage += amount;
    }
    @Override
    public boolean requestEnergy(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Energy request cannot be negative");
        }
        if (this.energy < amount) {
            return false;
        }
        else {
            this.energy -= amount;
            return true;
        }
    }

    @Override
    public void addStatus(StatusProvider status) {
        if (status == null) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.status.add(status);
    }
    @Override
    public void tickStatus() {
        status.removeIf((s) -> {
            int remaining = s.getDuration() - 1;
            if(remaining <= 0) {
                return true;
            }
            else {
                s.setDuration(remaining);
                return false;
            }
        });
    }
    @Override
    public void earnEnergy(int amount) {
        amount = Math.max(0, amount);
        this.energy = Math.min(this.maxEnergy, this.energy + amount);
    }
}