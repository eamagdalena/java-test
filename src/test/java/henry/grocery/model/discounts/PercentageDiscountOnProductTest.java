package henry.grocery.model.discounts;

import henry.grocery.model.basket.Basket;
import henry.grocery.model.basket.BasketEntry;
import henry.grocery.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PercentageDiscountOnProductTest {

    private ProductRepository productRepository = new ProductRepository();

    @Test
    void checkBasket_GivenTheProductIsOnTheBasketThenADiscountIsReturned() {

        Discount discount = new PercentageDiscountOnProduct(
                new BigDecimal("0.20"),
                productRepository.findByName("soup"),
                new Date(System.currentTimeMillis() - 6000),
                new Date(System.currentTimeMillis() + 6000)
        );

        Basket basket = new Basket();

        basket.getEntries().add(
                new BasketEntry(
                        productRepository.findByName("soup"), new BigDecimal(2)
                )
        );

        assertNotNull(discount.checkBasket(basket));

        assertEquals(new BigDecimal("0.26") ,discount.checkBasket(basket).getAmount());

    }

    @Test
    void checkBasket_GivenTheProductIsNotOnTheBasketThenNoDiscountIsReturned() {


        Discount discount = new PercentageDiscountOnProduct(
                new BigDecimal("0.20"),
                productRepository.findByName("bread"),
                new Date(System.currentTimeMillis() - 6000),
                new Date(System.currentTimeMillis() + 6000)
        );

        Basket basket = new Basket();

        basket.getEntries().add(
                new BasketEntry(
                        productRepository.findByName("soup"), new BigDecimal(2)
                )
        );

        assertNull(discount.checkBasket(basket));

    }

}