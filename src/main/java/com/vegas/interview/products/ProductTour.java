package com.vegas.interview.products;

import java.math.BigDecimal;

/**
 * Product class for Tour Objects
 */
public class ProductTour extends Product {
    public ProductTour(ProductType productType, int itemId, BigDecimal price) {
        super(productType, itemId, price);
    }

    @Override
    public int hashCode() {
        return itemId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof ProductTour arg) {
            return arg.productType == productType && arg.itemId == itemId && arg.price.equals(price);
        }

        return false;
    }

    @Override
    public int compareTo(Product o) {
        return getPrice().compareTo(o.getPrice());
    }
}
