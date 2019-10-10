package henry.grocery.model.discounts;

import henry.grocery.model.basket.AppliedDiscount;
import henry.grocery.model.basket.Basket;

import java.util.Date;

public abstract class Discount {

    protected Date validFrom;

    protected Date validTo;

    public abstract AppliedDiscount checkBasket(Basket basket);

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    protected boolean validBasketDate(Basket basket) {
        return basket.getDate().after(validFrom) &&
                basket.getDate().before(validTo);
    }

}
