package com.vegas.interview.products;

import java.math.BigDecimal;

/**
 * Abstract class used to hold Product information needed for sorting
 */
public abstract class Product implements Comparable<Product> {

    protected int itemId;
    protected BigDecimal price;
    protected ProductType productType;

    public Product(ProductType productType, int itemId, BigDecimal price) {
        this.productType = productType;
        this.itemId = itemId;
        this.price = price;
    }

    /**
     * Get product type
     *
     * @return {@link ProductType}
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * Get product item id
     *
     * @return item id
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Get product price
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

}
