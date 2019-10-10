package henry.grocery.model.basket;

import henry.grocery.model.discounts.Discount;

import java.math.BigDecimal;

public class AppliedDiscount {

    private Discount discount;

    private BigDecimal amount;

    public AppliedDiscount(Discount discount, BigDecimal amount) {
        this.discount = discount;
        this.amount = amount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
