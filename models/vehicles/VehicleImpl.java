package models.vehicles;

import models.miscellaneous.VehicleAssembler;
import models.parts.Part;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VehicleImpl implements Vehicle {
    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defense;
    private int hitPoints;
    private VehicleAssembler assembler;

    protected VehicleImpl(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        this.model = model;
        this.setWeight(weight);
        this.setPrice(price);
        this.setAttack(attack);
        this.setDefense(defense);
        this.setHitPoints(hitPoints);
        assembler = new VehicleAssembler();
    }

    @Override
    public double getTotalWeight() {
        return this.weight + this.assembler.getTotalWeight();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.price.add(this.assembler.getTotalPrice());
    }

    @Override
    public long getTotalAttack() {
        return this.attack + this.assembler.getTotalAttackModification();
    }

    @Override
    public long getTotalDefense() {
        return this.defense + this.assembler.getTotalDefenseModification();
    }

    @Override
    public long getTotalHitPoints() {
        return this.hitPoints + this.assembler.getTotalHitPointModification();
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.assembler.addArsenalPart(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.assembler.addShellPart(shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.assembler.addEndurancePart(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() {
        List<Part> allParts = new LinkedList<>();
        allParts.addAll(this.assembler.getArsenalParts());
        allParts.addAll(this.assembler.getShellParts());
        allParts.addAll(this.assembler.getEnduranceParts());
        return allParts;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s - %s", this.getClass().getSimpleName(), this.getModel())).
                append(System.lineSeparator());
        sb.append(String.format("Total Weight: %.3f", this.getTotalWeight())).
                append(System.lineSeparator());
        sb.append(String.format("Total Price: %.3f", this.getTotalPrice())).
                append(System.lineSeparator());
        sb.append(String.format("Attack: %d", this.getTotalAttack())).
                append(System.lineSeparator());
        sb.append(String.format("Defense: %d", this.getTotalDefense())).
                append(System.lineSeparator());
        sb.append(String.format("HitPoints: %d", this.getTotalHitPoints())).
                append(System.lineSeparator());
        sb.append("Parts: ");

        if (this.getParts() == null) {
            sb.append("None");
        } else {
            List<Part> allParts = new ArrayList<>();
            this.getParts().forEach(allParts::add);
            sb.append(allParts.stream().map(Part::getModel).collect(Collectors.joining(", ")));
        }
        return sb.toString();
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    protected void setPrice(BigDecimal price) {
        this.price = price;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    protected void setDefense(int defense) {
        this.defense = defense;
    }

    protected void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
