package battle.core;

import battle.enums.*;

public class DamageCalculator {
    private static double elementMultiplier(ElementType attackType, ElementType targetType) {
        return switch( attackType ){
            case FIRE -> switch( targetType ) {
                case NATURE -> 1.25;
                case WATER -> 0.75;
                default -> 1.0;
            };
            case WATER -> switch( targetType ) {
                case FIRE -> 1.25;
                case NATURE -> 0.75;
                default -> 1.0;
            };
            case NATURE -> switch( targetType ) {
                case WATER -> 1.25;
                case FIRE -> 0.75;
                default -> 1.0;
            };
            default -> {
                System.err.println("Unknown element type: " + attackType);
                yield 0.0;
            }
        };
    }
    private static double randomVariance(double Lval, double Rval) {
        return Math.random() * (Rval - Lval) + Lval;
    }
    
    public static int calculate(Character source, Character target, double Lval, double Rval) {
        double multiplier = elementMultiplier(source.getElementType(), target.getElementType());
        double damage = source.getDamage() * randomVariance(Lval, Rval);
        // TODO: absolute character defense
        return (int)Math.round(damage * multiplier);
    }

    public static int calculate(Character source, Character target) {
        return calculate(source, target, 1.0, 1.0);
    }
}