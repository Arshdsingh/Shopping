package shopping.agency.shopping;

import java.io.Serializable;

public class ProductItem1 implements Serializable{
    String id;
    String product_id;
    String title;
    String price;
    int imageurl;
    String description;
    int noofitems;

    public String getCateogory() {
        return cateogory;
    }

    public void setCateogory(String cateogory) {
        this.cateogory = cateogory;
    }

    String cateogory;

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

    public int getImageurl() {
        return imageurl;
    }

    public void setImageurl(int imageurl) {
        this.imageurl = imageurl;
    }
}
