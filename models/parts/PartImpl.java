package models.parts;

import common.ExceptionMessages;

import java.math.BigDecimal;

public abstract class PartImpl implements Part {
    private String model;
    private double weight;
    private BigDecimal price;

    protected PartImpl(String model, double weight, BigDecimal price) {
        this.setModel(model);
        this.setWeight(weight);
        this.setPrice(price);
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.EMPTY_MODEL);
        }
        this.model = model;
    }

    private void setWeight(double weight) {
        if (weight <= 0){
            throw new NullPointerException(ExceptionMessages.INVALID_WEIGHT);
        }
        this.weight = weight;
    }

    private void setPrice(BigDecimal price) {
        if (price == null || price.equals(BigDecimal.ZERO)){
            throw new NullPointerException(ExceptionMessages.INVALID_PRICE);

        }
        this.price = price;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        return String.format("%s Part – %s%n", this.getClass().getSimpleName(), this.getModel());

    }
}
