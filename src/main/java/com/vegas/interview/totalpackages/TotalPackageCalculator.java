package com.vegas.interview.totalpackages;

import com.vegas.interview.products.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Total Package Calculator class used to sort and bundle all products and packages
 */
public class TotalPackageCalculator {

    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;
    private final String sortType;
    private boolean isSortByTotalPackage;
    private final List<ProductType> productTypeSortList;
    private final List<TotalPackage> totalPackageList;

    public TotalPackageCalculator(BigDecimal minPrice, BigDecimal maxPrice, String sortType) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sortType = sortType;
        this.isSortByTotalPackage = false;
        this.productTypeSortList = new ArrayList<>();
        this.totalPackageList = new ArrayList<>();
    }

    public List<TotalPackage> getTotalPackageList() {
        return totalPackageList;
    }

    /**
     * Creates total package list from products and sorts them by user-defined ordering
     *
     * @param products the list of products to be bundled and sorted
     */
    public void createTotalPackageList(List<Product> products) {
        HashSet<Product> hotels = new HashSet<>();
        HashSet<Product> shows = new HashSet<>();
        HashSet<Product> tours = new HashSet<>();
        List<List<Product>> productList = new ArrayList<>();
        setProductTypeSortList(sortType);

        for (Product product : products) {
            if (product.getProductType() == ProductType.HOTEL && productTypeSortList.contains(ProductType.HOTEL)) {
                hotels.add(product);
            } else if (product.getProductType() == ProductType.SHOW && productTypeSortList.contains(ProductType.SHOW)) {
                shows.add(product);
            } else if (product.getProductType() == ProductType.TOUR && productTypeSortList.contains(ProductType.TOUR)) {
                tours.add(product);
            }
        }

        setProductSortOrder(productList, hotels, shows, tours);
        sortTotalPackageList(getCartesianProduct(productList));
    }

    /**
     * Sets lists to be sorted in specific order provided by product type sort list
     *
     * @param productList list containing multiple product lists in sorted order
     * @param hotels list of hotel products
     * @param shows list of show products
     * @param tours list of tour products
     */
    private void setProductSortOrder(List<List<Product>> productList, HashSet<Product> hotels, HashSet<Product> shows,
                                     HashSet<Product> tours) {
        for (ProductType productType : productTypeSortList) {
            if (productType.equals(ProductType.HOTEL)) {
                productList.add(hotels.stream().toList());
            } else if (productType.equals(ProductType.SHOW)) {
                productList.add(shows.stream().toList());
            } else if (productType.equals(ProductType.TOUR)) {
                productList.add(tours.stream().toList());
            }
        }
    }

    /**
     * Gets the cartesian product of product list of lists
     * Uses recursion until all permutations are found
     *
     * @param productList list of all products to go into permutations
     * @return list of all permutations
     */
    private static List<List<Product>> getCartesianProduct(List<List<Product>> productList) {
        return cartesianProduct(productList,0).collect(Collectors.toList());
    }

    /**
     * Returns the cartesian product of list with empty list for stopping condition
     *
     * @param productList list of all products to go into permutations
     * @param index index of current product in list
     * @return Stream list of product permutations
     */
    private static Stream<List<Product>> cartesianProduct(List<List<Product>> productList, int index) {
        if (index == productList.size()) {
            List<Product> emptyList = new ArrayList<>();
            return Stream.of(emptyList);
        }
        List<Product> currentSet = productList.get(index);
        return currentSet.stream().flatMap(element ->
                cartesianProduct(productList, index + 1).<List<Product>>map(ArrayList::new)
                .peek(newList -> newList.add(0, element)));
    }

    /**
     * Sets product type sort list from sort type string
     *
     * @param sortType string passed from command line for sort order
     */
    private void setProductTypeSortList(String sortType) {
        for (int i = 0; i < sortType.length(); i++) {
            switch (sortType.charAt(i)) {
                case 'p':
                    isSortByTotalPackage = true;
                    break;
                case 'h':
                    productTypeSortList.add(ProductType.HOTEL);
                    break;
                case 's':
                    productTypeSortList.add(ProductType.SHOW);
                    break;
                case 't':
                    productTypeSortList.add(ProductType.TOUR);
                    break;
            }
        }
    }

    /**
     * Creates and sorts total package list
     *
     * @param productList the list of all products to be sorted
     */
    private void sortTotalPackageList(List<List<Product>> productList) {
        for (List<Product> products : productList) {
            TotalPackage totalPackage = new TotalPackage(products, isSortByTotalPackage);
            if (totalPackage.getTotalPrice().compareTo(minPrice) >= 0 &&
                    totalPackage.getTotalPrice().compareTo(maxPrice) <= 0 ) {
                totalPackageList.add(totalPackage);
            }
        }
        Collections.sort(totalPackageList);
    }
}
