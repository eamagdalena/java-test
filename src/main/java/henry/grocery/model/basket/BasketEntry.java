package henry.grocery.model.basket;

import henry.grocery.model.Product;

import java.math.BigDecimal;

public class BasketEntry {

    private final Product product;

    private BigDecimal quantity;

    public BasketEntry(Product product, BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
