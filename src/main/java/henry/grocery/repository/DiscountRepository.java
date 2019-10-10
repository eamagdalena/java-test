package henry.grocery.repository;

import henry.grocery.model.discounts.BuyAProductGetAUnitOfOtherAtHalfPriceDiscount;
import henry.grocery.model.discounts.Discount;
import henry.grocery.model.discounts.PercentageDiscountOnProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DiscountRepository {

    private final static List<Discount> discountsTable = new ArrayList<>();

    private final static ProductRepository productRepository = new ProductRepository();

    static {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);

        Date yesterday = calendar.getTime();

        calendar.add(Calendar.DATE, 7);

        Date for7Days = calendar.getTime();

        calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, 3);

        Date from3DaysHence = calendar.getTime();

        calendar.add(Calendar.MONTH, 2);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Date untilTheEndOfTheFollowingMonth = calendar.getTime();

        discountsTable.add(
                new BuyAProductGetAUnitOfOtherAtHalfPriceDiscount(
                        productRepository.findByName("soup"),
                        new BigDecimal("2"),
                        productRepository.findByName("bread"),
                        yesterday,
                        for7Days
                )
        );

        discountsTable.add(
                new PercentageDiscountOnProduct(
                        new BigDecimal("0.10"),
                        productRepository.findByName("apples"),
                        from3DaysHence,
                        untilTheEndOfTheFollowingMonth
                )
        );


    }

    public List<Discount> findAll() {
        return discountsTable;
    }
}
