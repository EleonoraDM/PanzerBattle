package models.vehicles;

import java.math.BigDecimal;

public class Vanguard extends VehicleImpl {
    private static final double WEIGHT_INCREASE_VALUE = 2;
    private static final double ATTACK_DECREASE_VALUE = 0.75;
    private static final double DEFENSE_INCREASE_VALUE = 1.5;
    private static final double HIT_POINTS_INCREASE_VALUE = 1.75;

    public Vanguard(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price, attack, defense, hitPoints);
        this.setWeight(weight);
        this.setAttack(attack);
        this.setDefense(defense);
        this.setHitPoints(hitPoints);
    }

    @Override
    public void setWeight(double weight) {
        super.setWeight(weight * WEIGHT_INCREASE_VALUE);
    }

    @Override
    public void setAttack(int attack) {
        super.setAttack((int) (attack * ATTACK_DECREASE_VALUE));
    }

    @Override
    public void setDefense(int defense) {
        super.setDefense((int) (defense * DEFENSE_INCREASE_VALUE));
    }

    @Override
    public void setHitPoints(int hitPoints) {
        super.setHitPoints((int) (hitPoints * HIT_POINTS_INCREASE_VALUE));
    }
}
