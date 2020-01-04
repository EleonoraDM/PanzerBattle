package models.vehicles;

import java.math.BigDecimal;

public class Revenger extends VehicleImpl {

    private static final double PRICE_INCREASE_VALUE = 1.5;
    private static final double ATTACK_INCREASE_VALUE = 2.5;
    private static final double DEFENSE_DECREASE_VALUE = 0.5;
    private static final double HIT_POINTS_DECREASE_VALUE = 0.5;

    public Revenger(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price, attack, defense, hitPoints);
        this.setPrice(price);
        this.setAttack(attack);
        this.setDefense(defense);
        this.setHitPoints(hitPoints);
    }

    @Override
    public void setPrice(BigDecimal price) {
        super.setPrice(price.multiply(BigDecimal.valueOf(PRICE_INCREASE_VALUE)));
    }

    @Override
    public void setAttack(int attack) {
        super.setAttack((int) (attack * ATTACK_INCREASE_VALUE));
    }

    @Override
    public void setDefense(int defense) {
        super.setDefense((int) (defense * DEFENSE_DECREASE_VALUE));
    }

    @Override
    public void setHitPoints(int hitPoints) {
        super.setHitPoints((int) (hitPoints * HIT_POINTS_DECREASE_VALUE));
    }
}
