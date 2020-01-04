package models.parts;

import contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class EndurancePart extends PartImpl implements HitPointsModifyingPart {
    private static final String ADDITIONAL_PARAM = "HitPoints";
    private int hitPointsModifier;

    public EndurancePart(String model, double weight, BigDecimal price, int hitPointsModifier) {
        super(model, weight, price);
        this.hitPointsModifier = hitPointsModifier;
    }

    @Override
    public int getHitPointsModifier() {
        return this.hitPointsModifier;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("+%d %s", this.getHitPointsModifier(), ADDITIONAL_PARAM);
    }
}
