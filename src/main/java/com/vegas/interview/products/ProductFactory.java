package com.vegas.interview.products;

import java.math.BigDecimal;

/**
 * Product factory class used to build hotel, show, or tour products
 */
public class ProductFactory {

    private ProductFactory() {}

    /***
     * Returns concrete product type by parameters
     *
     * @param productType type of product
     * @param itemId item id of product
     * @param price price of Product
     * @return concrete product class
     */
    public static Product getProduct(ProductType productType, int itemId, BigDecimal price) {
        if (productType == ProductType.HOTEL) {
            return new ProductHotel(productType, itemId, price);
        } else if (productType == ProductType.SHOW) {
            return new ProductShow(productType, itemId, price);
        } else if (productType == ProductType.TOUR) {
            return new ProductTour(productType, itemId, price);
        }

        return null;
    }
}