package models.parts;

import contracts.Modelable;

import java.math.BigDecimal;

public interface Part extends Modelable {
    double getWeight();

    BigDecimal getPrice();
}