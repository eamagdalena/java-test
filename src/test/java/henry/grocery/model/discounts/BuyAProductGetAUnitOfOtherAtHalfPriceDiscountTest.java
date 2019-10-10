package henry.grocery.model.discounts;

import henry.grocery.model.basket.Basket;
import henry.grocery.model.basket.BasketEntry;
import henry.grocery.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BuyAProductGetAUnitOfOtherAtHalfPriceDiscountTest {

    private ProductRepository productRepository = new ProductRepository();


    @Test
    void givenABasketThatFulfillsTheConditionThenADiscountIsReturned() {

        Discount discount = new BuyAProductGetAUnitOfOtherAtHalfPriceDiscount(
                productRepository.findByName("soup"),
                new BigDecimal("2"),
                productRepository.findByName("bread"),
                new Date(System.currentTimeMillis() - 60000),
                new Date(System.currentTimeMillis() + 60000)
        );

        Basket basket = new Basket();

        basket.getEntries().add(
                new BasketEntry(
                        productRepository.findByName("soup"), new BigDecimal(2)
                )
        );

        basket.getEntries().add(
                new BasketEntry(
                        productRepository.findByName("bread"), new BigDecimal(1)
                )
        );

        assertNotNull(discount.checkBasket(basket));

        assertEquals(new BigDecimal("0.40"), discount.checkBasket(basket).getAmount());

    }

    @Test
    void givenABasketThatDoesNotFulfillsTheConditionThenNoDiscountIsReturned() {

        Discount discount = new BuyAProductGetAUnitOfOtherAtHalfPriceDiscount(
                productRepository.findByName("soup"),
                new BigDecimal("2"),
                productRepository.findByName("bread"),
                new Date(System.currentTimeMillis() - 60000),
                new Date(System.currentTimeMillis() + 60000)
        );

        Basket basket = new Basket();

        basket.getEntries().add(
                new BasketEntry(
                        productRepository.findByName("soup"), new BigDecimal(1)
                )
        );

        basket.getEntries().add(
                new BasketEntry(
                        productRepository.findByName("bread"), new BigDecimal(1)
                )
        );

        assertNull(discount.checkBasket(basket));

    }

}