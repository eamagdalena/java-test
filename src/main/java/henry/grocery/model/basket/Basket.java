package henry.grocery.model.basket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Basket {

    private final List<BasketEntry> entries = new ArrayList<>();

    private List<AppliedDiscount> discounts = new ArrayList<>();

    private final Date date;

    public Basket() {
        date = new Date();
    }

    public Basket(Date date) {
        this.date = date;
    }

    public BigDecimal getPriceTotal() {

        return entries
                .stream()
                .map(
                        e -> e.getProduct().getCost().multiply(e.getQuantity())
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(
                        discounts
                                .stream()
                                .map(d -> d.getAmount())
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                );
    }

    /* --- */

    public List<BasketEntry> getEntries() {
        return entries;
    }

    public List<AppliedDiscount> getDiscounts() {
        return discounts;
    }

    public Date getDate() {
        return date;
    }

    public void setDiscounts(List<AppliedDiscount> discounts) {
        this.discounts = discounts;
    }


}
