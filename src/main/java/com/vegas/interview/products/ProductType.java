package com.vegas.interview.products;

/**
 * Enum class used for product type information
 */
public enum ProductType {
    HOTEL("HOTEL"),
    SHOW("SHOW"),
    TOUR("TOUR"),
    TOTAL_PACKAGE("TOTAL_PACKAGE");

    public final String label;

    ProductType(String label) {
        this.label = label;
    }
}