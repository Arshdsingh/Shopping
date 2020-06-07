package shopping.agency.shopping;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderItems implements Serializable {
    String username;
    ProductItem productItems;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProductItem getProductItems() {
        return productItems;
    }

    public void setProductItems(ProductItem productItems) {
        this.productItems = productItems;
    }
}
