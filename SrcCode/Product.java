package SrcCode;

/**
 *
 * @author Omar Mamon
 */

import java.io.*;
import java.util.*;

public final class Product implements Comparable<Product> {
    private File file;
    private String name, description, subCategoryName;
    private int productId, quantity, numberOfRatings;
    private double price, rating;

    // Exception handling
    public static class InvalidRatingException extends Exception {

        public InvalidRatingException(String message) {
            super(message);
        }
    }

    /*
     * will be mostly used in algorithm of code
     * depending on file tree structure
     * we need to read and write data to files
     * also for admin and user it will be more efficient way to deal with .txt files
     */
    public Product(File file) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                this.file = file;
                this.name = scanner.nextLine();
                this.description = scanner.hasNextLine() ? scanner.nextLine() : "No Product found with this name";
                this.productId = scanner.hasNextLine() ? Integer.parseInt(scanner.nextLine()) : 0;
                this.quantity = scanner.hasNextLine() ? Integer.parseInt(scanner.nextLine()) : 0;
                this.price = scanner.hasNextLine() ? Double.parseDouble(scanner.nextLine()) : 0.0;
                this.rating = scanner.hasNextLine() ? Double.parseDouble(scanner.nextLine()) : 0.0;
                this.numberOfRatings = scanner.hasNextLine() ? Integer.parseInt(scanner.nextLine()) : 0;
                this.subCategoryName = file.getParentFile().getName();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Product(String name, String description, int productId, int quantity, double price, String subCategoryName) {
        this.name = name;
        this.description = description;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.rating = 0;
        this.numberOfRatings = 0;
        this.subCategoryName = subCategoryName;
        File CheckFile = new File("..\\Online-Shopping-System\\data\\SubCategories\\" + this.subCategoryName + "\\" + this.name + ".txt");

        if (CheckFile.exists()) {
            this.file = CheckFile;
            updateFile();
        } else {
            try {
                FileWriter writer = new FileWriter(
                        "..\\Online-Shopping-System\\data\\SubCategories\\" + this.subCategoryName + "\\" + this.name + ".txt");
                file = new File("..\\Online-Shopping-System\\data\\SubCategories\\" + this.subCategoryName + "\\" + this.name + ".txt");
                updateFile();
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // defining setters
    public void setPrice(double price) {
        // to set price of product if changed
        this.price = price;
        updateFile();
    }

    public void setQuantity(int quantity) {
        // for setting in/out products
        this.quantity = quantity;
        updateFile();
    }

    // defining getters
    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public int getAvailableQuantity() {
        return this.quantity;
    }

    public double getRating() {
        return this.rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public double ratingCalc(int rating) throws InvalidRatingException {
        // calculate rating
        if (rating < 0 || rating > 5) {
            throw new InvalidRatingException("Invalid rating, rating should be between 0 and 5");

        } else {
            this.rating = (this.rating * this.numberOfRatings + rating) / (numberOfRatings + 1);

            // update the Number of Ratings
            this.numberOfRatings++;
            updateFile();

            System.out.println(
                    "Thanks for rating our products your opinion matters us !\n New rating is: " + this.rating);
            return this.rating;
        }

    }

    public ArrayList<String> getDetails() {
        // return all data members
        // Create an ArrayList object to contain all the details

        ArrayList<String> productDetails = new ArrayList<String>();
        productDetails.add(this.subCategoryName);
        productDetails.add(this.name);
        productDetails.add(this.description);
        productDetails.add(Integer.toString(this.productId));
        productDetails.add(Integer.toString(this.quantity));
        productDetails.add(Double.toString(this.price));
        productDetails.add(Double.toString(this.rating));
        productDetails.add(Integer.toString(this.numberOfRatings));

        return productDetails;
    }

    public void printDetails() {
        System.out.println("------------------ Details of " + this.name + " -------------------");
        System.out.println("Product Sub Category: " + this.subCategoryName);
        System.out.println("Product Name: " + this.name);
        System.out.println("Product Description: " + this.description);
        System.out.println("Product ID: #" + this.productId);

        if (this.quantity == 0) {
            System.out.println("Product is out of stock");
        } else {
            System.out.println("There are: " + this.quantity + " products left");
        }

        System.out.println("Product Price: " + this.price + "LE");
        if (this.rating == 0 && this.numberOfRatings == 0) {
            System.out.println("Not Rated yet");
        } else {
            System.out.println("Product Rating: " + this.rating + " out of 5");
        }
        System.out.println(this.numberOfRatings + " people rated this product");
        System.out.println("---------------------------------------------------------");
    }

    public void addedToCart(int quantity) throws NullPointerException {
        // product added to cart
        if (this.quantity == 0) {
            System.out.println("Product out of stock");
        } else if (this.quantity >= quantity) {
            this.quantity -= quantity;
            updateFile();
        } else {
            System.out.println("not enough quantity available");
        }
    }

    public void removedFromCart(int quantity) {
        // product added to cart
        this.quantity += quantity;
        updateFile();
    }

    public void updateFile() {
        String filePath = this.file.toPath().toString();
        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(this.name + "\n");
            bufferedWriter.write(this.description + "\n");
            bufferedWriter.write(this.productId + "\n");
            bufferedWriter.write(this.quantity + "\n");
            bufferedWriter.write(this.price + "\n");
            bufferedWriter.write(this.rating + "\n");
            bufferedWriter.write(this.numberOfRatings + "\n");

            // Flushes the stream
            bufferedWriter.flush();
            // Closes the stream
            bufferedWriter.close();

            System.out.println("Successfully updated the file.");

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Product other) {
        // compare products based on their rating
        if (this.rating > other.rating) {
            return -1; // for descending order
        } else if (this.rating < other.rating) {
            return 1; // for descending order
        } else {
            // if ratings are equal, sort by price in ascending order
            if (this.price < other.price) {
                return -1;
            } else if (this.price > other.price) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
