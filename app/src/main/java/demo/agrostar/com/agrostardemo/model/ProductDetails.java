package demo.agrostar.com.agrostardemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by prade on 02-03-2016.
 */
public class ProductDetails implements Serializable {
    private String productName;
    private double productRate;
    private List<Integer> productImages;

    public List<Integer> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<Integer> productImages) {
        this.productImages = productImages;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductRate() {
        return productRate;
    }

    public void setProductRate(double productRate) {
        this.productRate = productRate;
    }
}
