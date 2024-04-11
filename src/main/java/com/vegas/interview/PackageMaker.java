package com.vegas.interview;

import com.vegas.interview.products.Product;
import com.vegas.interview.totalpackages.TotalPackageCalculator;
import com.vegas.interview.util.FileUtil;

import java.math.BigDecimal;
import java.util.List;

public class PackageMaker {
    public static void main(String[] args) {
        TotalPackageCalculator totalPackageCalculator = bundleSortPackagesFromFile(args);
        FileUtil.printTotalPackagesToFile(totalPackageCalculator.getTotalPackageList(), args[4]);
    }

    /**
     * Bundles and sorts packages according to command line arguments
     *
     * @param args command line arguments
     * @return {@link TotalPackageCalculator)
     */
    public static TotalPackageCalculator bundleSortPackagesFromFile(String[] args) {
        if (args.length != 5) {
            throw new IllegalArgumentException("Wrong number of arguments");
        } else if (!FileUtil.isNumeric(args[1]) || !FileUtil.isNumeric(args[2])) {
            throw new IllegalArgumentException("Second and third arguments must be numeric");
        }

        List<Product> products = FileUtil.getProductsFromFile(args[0]);
        TotalPackageCalculator totalPackageCalculator = new TotalPackageCalculator(new BigDecimal(args[1]),
                new BigDecimal(args[2]), args[3]);
        totalPackageCalculator.createTotalPackageList(products);

        return totalPackageCalculator;
    }
}