package henry.grocery.model.discounts;

import henry.grocery.model.basket.AppliedDiscount;
import henry.grocery.model.basket.Basket;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    static class GenericDiscount extends Discount {

        public GenericDiscount(Date validFrom, Date validTo) {
            this.validFrom = validFrom;
            this.validTo = validTo;
        }

        @Override
        public AppliedDiscount checkBasket(Basket basket) {
            return null;
        }
    }

    @Test
    void validBasketDate_givenABasketOnTheDateRangeOfTheDiscountThenReturnsTrue() {

        GenericDiscount discount = new GenericDiscount(
                new Date(System.currentTimeMillis() - 5000),
                new Date(System.currentTimeMillis() + 5000)
        );

        assertTrue(discount.validBasketDate(new Basket()));
    }

    @Test
    void validBasketDate_givenABasketOutOfRangeOfTheDiscountThenReturnsFalse() {

        GenericDiscount discount = new GenericDiscount(
                new Date(System.currentTimeMillis() + 5000),
                new Date(System.currentTimeMillis() + 15000)
        );

        assertFalse(discount.validBasketDate(new Basket()));
    }
}