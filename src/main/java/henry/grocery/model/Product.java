package henry.grocery.model;

import java.math.BigDecimal;

public class Product {

    private final String name;

    private final Unit unit;

    private final BigDecimal cost;

    public Product(String name, Unit unit, BigDecimal cost) {
        this.name = name;
        this.unit = unit;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Unit getUnit() {
        return unit;
    }

    public BigDecimal getCost() {
        return cost;
    }

}
