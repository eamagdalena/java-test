package henry.grocery.repository;

import henry.grocery.model.Product;
import henry.grocery.model.Unit;

import java.math.BigDecimal;
import java.util.HashMap;

public class ProductRepository {

    private static HashMap<String, Product> productTable = new HashMap();

    static {
        productTable.put("soup", new Product("soup", Unit.tin, new BigDecimal("0.65")));
        productTable.put("bread", new Product("bread", Unit.loaf, new BigDecimal("0.80")));
        productTable.put("milk", new Product("milk", Unit.bottle, new BigDecimal("1.30")));
        productTable.put("apples", new Product("apples", Unit.single, new BigDecimal("0.10")));

    }

    public Product findByName(String name) {
        return productTable.get(name);
    }
}
