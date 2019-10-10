package henry.grocery.model.discounts;

import henry.grocery.model.Product;
import henry.grocery.model.basket.AppliedDiscount;
import henry.grocery.model.basket.Basket;
import henry.grocery.model.basket.BasketEntry;

import java.math.BigDecimal;
import java.util.Date;

public class PercentageDiscountOnProduct extends Discount {

    private BigDecimal discountRate;

    private Product product;

    public PercentageDiscountOnProduct(BigDecimal discountRate, Product product, Date validFrom, Date validTo) {
        this.discountRate = discountRate;
        this.product = product;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Override
    public AppliedDiscount checkBasket(Basket basket) {

        if(!validBasketDate(basket)) {
            return null;
        }

        BasketEntry basketEntry =
                basket.getEntries()
                        .stream()
                        .filter(be -> be.getProduct() == product)
                        .findAny()
                        .orElse(null);

        if (basketEntry == null) {
            return null;
        }

        BigDecimal discountAmount = basketEntry.getQuantity()
                .multiply(discountRate)
                .multiply(
                        basketEntry.getProduct().getCost()
                ).setScale(2);

        return new AppliedDiscount(this, discountAmount);
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return product.getName() + " have a " + (discountRate.multiply(new BigDecimal(100)) + " discount");
    }
}


