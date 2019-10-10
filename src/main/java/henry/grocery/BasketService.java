package henry.grocery;

import henry.grocery.model.basket.Basket;
import henry.grocery.model.basket.BasketEntry;
import henry.grocery.repository.DiscountRepository;
import henry.grocery.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;

public class BasketService {

    private ProductRepository productRepository = new ProductRepository();

    private DiscountRepository discountRepository = new DiscountRepository();

    public Basket addProductToBasket(Basket basket, String productName, BigDecimal quantity) {

        basket.getEntries().add(
                new BasketEntry(
                        productRepository.findByName(productName),
                        quantity
                )
        );

        basket.setDiscounts(
                discountRepository
                        .findAll()
                        .stream()
                        .map(d -> d.checkBasket(basket))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );

        return basket;
    }
}
