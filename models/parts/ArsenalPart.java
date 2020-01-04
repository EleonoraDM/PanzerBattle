package models.parts;

import contracts.AttackModifyingPart;

import java.math.BigDecimal;

public class ArsenalPart extends PartImpl implements AttackModifyingPart {
    private static final String ADDITIONAL_PARAM = "Attack";
    private int attackModifier;

    public ArsenalPart(String model, double weight, BigDecimal price, int attackModifier) {
        super(model, weight, price);
        this.attackModifier = attackModifier;
    }

    @Override
    public int getAttackModifier() {
        return this.attackModifier;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("+%d %s", this.getAttackModifier(), ADDITIONAL_PARAM);
    }
}
