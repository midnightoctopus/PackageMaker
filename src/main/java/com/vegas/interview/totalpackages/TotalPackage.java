package com.vegas.interview.totalpackages;

import com.vegas.interview.products.Product;
import com.vegas.interview.products.ProductType;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

public class TotalPackage implements Comparable<TotalPackage> {

    private final List<Product> products;
    private BigDecimal totalPrice;
    private final boolean isSortByTotalPackage;

    public TotalPackage(List<Product> products, boolean isSortByTotalPackage) {
        this.products = products;
        this.isSortByTotalPackage = isSortByTotalPackage;
        setTotalPrice(products);
    }

    /**
     * Gets product by type from product list
     *
     * @param productType {@link ProductType}
     * @return product by type
     */
    public Product getProductByType(ProductType productType) {
        for (Product product : products) {
            if (product.getProductType() == productType) {
                return product;
            }
        }
        return null;
    }

    /**
     * Sets total price of package from product list
     *
     * @param products the list of products in the package
     */
    private void setTotalPrice(List<Product> products) {
        this.totalPrice = BigDecimal.ZERO;
        for (Product product : products) {
            totalPrice = totalPrice.add(product.getPrice());
        }
    }

    /**
     * Gets total prices of package
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Used to multi-sort products depending on type in order provided by command line
     *
     * @param totalPackage the object to be compared.
     * @return integer signifying equality, less than, or greater than
     */
    @Override
    public int compareTo(TotalPackage totalPackage) {
        int result = 0;
        if (isSortByTotalPackage) {
            result = totalPrice.compareTo(totalPackage.totalPrice);
        }
        for (int i = 0; i < products.size(); i++) {
            if (result == 0 && totalPackage.products.size() > i) {
                result = products.get(i).compareTo(totalPackage.products.get(i));
            }
        }
        return result;
    }
}
