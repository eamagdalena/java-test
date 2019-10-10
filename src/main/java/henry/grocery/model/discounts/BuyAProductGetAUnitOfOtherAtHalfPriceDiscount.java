package henry.grocery.model.discounts;

import henry.grocery.model.Product;
import henry.grocery.model.basket.AppliedDiscount;
import henry.grocery.model.basket.Basket;
import henry.grocery.model.basket.BasketEntry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class BuyAProductGetAUnitOfOtherAtHalfPriceDiscount extends Discount {

    private final static BigDecimal FIFTY_PERCENT = new BigDecimal("0.5");

    private Product requiredProduct;

    private BigDecimal quantityOfRequiredProduct;

    private Product discountedProduct;

    public BuyAProductGetAUnitOfOtherAtHalfPriceDiscount(
            Product requiredProduct,
            BigDecimal quantityOfRequiredProduct,
            Product discountedProduct,
            Date validFrom,
            Date validTo) {
        this.requiredProduct = requiredProduct;
        this.quantityOfRequiredProduct = quantityOfRequiredProduct;
        this.discountedProduct = discountedProduct;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Override
    public AppliedDiscount checkBasket(Basket basket) {

        if (!validBasketDate(basket)) {
            return null;
        }

        BasketEntry requiredProductBasketEntry =
                basket.getEntries()
                        .stream()
                        .filter(be -> be.getProduct() == requiredProduct)
                        .findAny()
                        .orElse(null);

        if (requiredProductBasketEntry == null) {
            return null;
        }

        BasketEntry discountedProductBasketEntry =
                basket.getEntries()
                        .stream()
                        .filter(be -> be.getProduct() == discountedProduct)
                        .findAny()
                        .orElse(null);

        if (discountedProductBasketEntry == null) {
            return null;
        }

        BigDecimal numberOfDiscountableUnits =
                requiredProductBasketEntry.getQuantity().divide(quantityOfRequiredProduct, 0, RoundingMode.FLOOR);

        if (numberOfDiscountableUnits.equals(BigDecimal.ZERO)) {
            return null;
        }

        BigDecimal unitsToDiscount =
                numberOfDiscountableUnits.compareTo(discountedProductBasketEntry.getQuantity()) > 0 ?
                        discountedProductBasketEntry.getQuantity() : numberOfDiscountableUnits;

        BigDecimal discountAmount = FIFTY_PERCENT
                .multiply(unitsToDiscount)
                .multiply(discountedProduct.getCost())
                .setScale(2);

        return new AppliedDiscount(this, discountAmount);
    }

    @Override
    public String toString() {
        return String.format(
                "Buy %d %s of %s and get a %s of %s half price",
                quantityOfRequiredProduct.intValue(),
                requiredProduct.getUnit(),
                requiredProduct.getName(),
                discountedProduct.getUnit(),
                discountedProduct.getName()
        );
    }
}
