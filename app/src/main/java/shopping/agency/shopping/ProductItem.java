package shopping.agency.shopping;

import java.io.Serializable;

public class ProductItem implements Serializable{
    String id;
    String product_id;
    String title;
    String price;
    String imageurl;
    String description;
    int noofitems;

    public int getNoofitems() {
        return noofitems;
    }

    public void setNoofitems(int noofitems) {
        this.noofitems = noofitems;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
