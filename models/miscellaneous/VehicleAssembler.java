package models.miscellaneous;

import contracts.AttackModifyingPart;
import contracts.DefenseModifyingPart;
import contracts.HitPointsModifyingPart;
import models.parts.Part;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class VehicleAssembler implements Assembler {
    private List<AttackModifyingPart> arsenalParts;
    private List<DefenseModifyingPart> shellParts;
    private List<HitPointsModifyingPart> enduranceParts;

    public VehicleAssembler() {
        this.arsenalParts = new LinkedList<>();
        this.shellParts = new LinkedList<>();
        this.enduranceParts = new LinkedList<>();
    }

    public List<AttackModifyingPart> getArsenalParts() {
        return Collections.unmodifiableList(this.arsenalParts);
    }

    public List<DefenseModifyingPart> getShellParts() {
        return Collections.unmodifiableList(this.shellParts);
    }

    public List<HitPointsModifyingPart> getEnduranceParts() {
        return Collections.unmodifiableList(this.enduranceParts);
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.arsenalParts.add((AttackModifyingPart) arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.shellParts.add((DefenseModifyingPart) shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.enduranceParts.add((HitPointsModifyingPart) endurancePart);
    }

    @Override
    public double getTotalWeight() {
        return this.arsenalParts.stream().mapToDouble(AttackModifyingPart::getWeight).sum()
                + this.shellParts.stream().mapToDouble(DefenseModifyingPart::getWeight).sum()
                + this.enduranceParts.stream().mapToDouble(HitPointsModifyingPart::getWeight).sum();
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal result = BigDecimal.ZERO;

        for (AttackModifyingPart arsenalPart : this.arsenalParts) {
            result = result.add(arsenalPart.getPrice());
        }
        for (DefenseModifyingPart shellPart : this.shellParts) {
            result = result.add(shellPart.getPrice());
        }
        for (HitPointsModifyingPart endurancePart : this.enduranceParts) {
            result = result.add(endurancePart.getPrice());
        }
        return result;
    }

    @Override
    public long getTotalAttackModification() {
        return this.arsenalParts.stream().mapToLong(AttackModifyingPart::getAttackModifier).sum();
    }

    @Override
    public long getTotalDefenseModification() {
        return this.shellParts.stream().mapToLong(DefenseModifyingPart::getDefenseModifier).sum();
    }

    @Override
    public long getTotalHitPointModification() {
        return this.enduranceParts.stream().mapToLong(HitPointsModifyingPart::getHitPointsModifier).sum();
    }
}