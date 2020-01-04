package models.parts;

import contracts.DefenseModifyingPart;

import java.math.BigDecimal;

public class ShellPart extends PartImpl implements DefenseModifyingPart {
    private static final String ADDITIONAL_PARAM = "Defense";
    private int defenseModifier;

    public ShellPart(String model, double weight, BigDecimal price, int defenseModifier) {
        super(model, weight, price);
        this.defenseModifier = defenseModifier;
    }

    @Override
    public int getDefenseModifier() {
        return this.defenseModifier;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("+%d %s", this.getDefenseModifier(), ADDITIONAL_PARAM);
    }
}
