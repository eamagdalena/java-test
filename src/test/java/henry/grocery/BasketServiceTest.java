package henry.grocery;

import henry.grocery.model.basket.Basket;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class BasketServiceTest {

    private BasketService service = new BasketService();

    @Test
    void acceptanceCriteriaExample1_aDiscountisAppliedOnOneOfTheUnitsOfTheDiscountedItem() {

        Basket basket = new Basket();

        service.addProductToBasket(basket, "soup", new BigDecimal(3));

        service.addProductToBasket(basket, "bread", new BigDecimal(2));

        assertEquals(1, basket.getDiscounts().size());

        assertEquals(new BigDecimal("3.15"), basket.getPriceTotal());

    }

    @Test
    void acceptanceCriteriaExample2_NoDiscountIsAppliedBecauseDiscountIsNotValidToday() {

        Basket basket = new Basket();

        service.addProductToBasket(basket, "apples", new BigDecimal(6));

        service.addProductToBasket(basket, "milk", new BigDecimal(1));

        assertEquals(new BigDecimal("1.90"), basket.getPriceTotal());

    }

    @Test
    void acceptanceCriteriaExample3_DiscountIsAppliedOnBasketInAFutureDate() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, 5);

        Basket basket = new Basket(calendar.getTime());

        service.addProductToBasket(basket, "apples", new BigDecimal(6));

        service.addProductToBasket(basket, "milk", new BigDecimal(1));

        assertEquals(1, basket.getDiscounts().size());

        assertEquals(new BigDecimal("1.84"), basket.getPriceTotal());

    }

    @Test
    void acceptanceCriteriaExample4_BothDiscountsAreApplied() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, 5);

        Basket basket = new Basket(calendar.getTime());

        service.addProductToBasket(basket, "apples", new BigDecimal(3));

        service.addProductToBasket(basket, "soup", new BigDecimal(2));

        service.addProductToBasket(basket, "bread", new BigDecimal(1));

        assertEquals(2, basket.getDiscounts().size());

        assertEquals(new BigDecimal("1.97"), basket.getPriceTotal());

    }


}