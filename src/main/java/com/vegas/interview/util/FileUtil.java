package com.vegas.interview.util;

import com.vegas.interview.products.Product;
import com.vegas.interview.products.ProductFactory;
import com.vegas.interview.products.ProductType;
import com.vegas.interview.totalpackages.TotalPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FileUtil {

    private FileUtil() {}

    /**
     * Gets list of products from file
     *
     * @param fileName the input file name
     * @return list of products
     */
    public static List<Product> getProductsFromFile(String fileName) {
        ArrayList<Product> products = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(new File(fileName).toPath());
            for (String line : lines) {
                String[] lineParts;
                lineParts = line.split("\t+");
                Product product = ProductFactory.getProduct(ProductType.valueOf(lineParts[0]) , parseInt(lineParts[1]),
                        new BigDecimal(lineParts[2]));
                products.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    /**
     * Prints the total package list to file
     *
     * @param totalPackages the total package list to be output to file
     * @param fileName name of file to be output
     */
    public static void printTotalPackagesToFile(List<TotalPackage> totalPackages, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            for (TotalPackage totalPackage : totalPackages) {
                String packageString = String.format("PACKAGE\t%s",
                        totalPackage.getTotalPrice().doubleValue());
                String hotelString = "\t\t\t";
                String showString = "\t\t\t";
                String tourString = "\t\t\t";

                Product productHotel = totalPackage.getProductByType(ProductType.HOTEL);
                Product productShow = totalPackage.getProductByType(ProductType.SHOW);
                Product productTour = totalPackage.getProductByType(ProductType.TOUR);

                if (productHotel != null) {
                    hotelString = String.format("\tHOTEL\t%s\t%s", productHotel.getItemId(),
                            productHotel.getPrice().doubleValue());
                }

                if (productShow != null) {
                    showString = String.format("\tSHOW\t%s\t%s", productShow.getItemId(),
                            productShow.getPrice().doubleValue());
                }

                if (productTour != null) {
                    tourString = String.format("\tTOUR\t%s\t%s", productTour.getItemId(),
                            productTour.getPrice().doubleValue());
                }

                writer.write(packageString);
                writer.write(hotelString);
                writer.write(showString);
                writer.write(tourString);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if a string is number
     *
     * @param numStr the string to check
     * @return true if string is numeric
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isNumeric(String numStr) {
        if (numStr == null) {
            return false;
        }
        try {
            Double.parseDouble(numStr);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}


