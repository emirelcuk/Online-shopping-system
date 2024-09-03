package SrcCode;

import java.io.*;
import java.util.*;

/**
 *
 * @author Habiba
 */
public class ShoppingCart {

    private List<Product> items;
    private List<Integer> quantities;
    private double totalPrice;
    private double totalPriceAfterDiscount;

    public static class QuantityExceededException extends Exception {

        public QuantityExceededException(String message) {
            super(message);
        }
    }

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.quantities = new ArrayList<Integer>();
        this.totalPrice = 0;
        this.totalPriceAfterDiscount = 0;
    }

    // defining getters
    public List<Product> getItems() {
        return this.items;
    }

    public List<Integer> getQuantity() {
        return this.quantities;
    }

    public void addItem(Product product) {
        items.add(product);
        quantities.add(1);
        product.addedToCart(1);
    }

    public void removeItem(Product product) {
        items.remove(product);
        int index = items.indexOf(product);
        quantities.remove(index);
        product.removedFromCart(this.quantities.get(index));
    }

    public void Increase(Product product) throws QuantityExceededException {
        try {
            int index = items.indexOf(product);
            int quantity = this.quantities.get(index);
            int availableQuantity = product.getAvailableQuantity();
            if (quantity > availableQuantity) {
                throw new QuantityExceededException("Sorry, but this quantity exceeds the available quantity.");
            } else {
                this.quantities.set(index, ++quantity);
                product.addedToCart(1);
            }
        } catch (QuantityExceededException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Decrease(Product product) throws IndexOutOfBoundsException {
        int index = items.indexOf(product);
        if (index >= 0 && index < items.size()) {
            int quantityToDecrease = quantities.get(index);
            if (quantityToDecrease > 1) {
                quantities.set(index, quantityToDecrease - 1);
            } else {
                items.remove(index);
                quantities.remove(index);
            }
        } else {
            System.out.println("Index out of bounds.");
        }

    }

    public void clearCart() {
        for (int i = 0; i < items.size(); i++) {
            this.items.get(i).removedFromCart(this.quantities.get(i));
        }
        items.clear();
        quantities.clear();
    }

    public void displayCart() {
        ArrayList<String> productDetails = new ArrayList<String>();
        for (int i = 0; i < items.size(); i++) {
            productDetails = items.get(i).getDetails();
            if (items.get(i).getRating() == 0 && items.get(i).getNumberOfRatings() == 0) {
                productDetails.remove(productDetails.size() - 2);
                productDetails.remove(productDetails.size() - 1);
            }
            for (int j = 0; j < productDetails.size(); j++) {
                System.out.println(productDetails.get(j));
            }
            System.out.println("-------------------------");
        }
    }

    public double[] getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < items.size(); i++) {
            Product item = items.get(i);
            int quantity = quantities.get(i);
            totalPrice += item.getPrice() * quantity;
        }

        return new double[] { totalPrice, this.totalPriceAfterDiscount };
    }

    public double getTotalPriceBeforeDiscount() {
        double[] totalPrices = getTotalPrice();
        return totalPrices[0];
    }

    public double getTotalPriceAfterDiscount() {
        double[] totalPrices = getTotalPrice();
        return totalPrices[1];
    }

    public double applyDiscountCode() {
        Scanner input = new Scanner(System.in);
        double totalPrice = getTotalPriceBeforeDiscount();
        double discountAmount = 0;

        while (true) {
            System.out.println("Enter the discount code: ");
            String discountCode = input.nextLine();

            try {
                File file = new File("\\data\\DiscountCodes\\discount_codes.txt");
                Scanner scanner = new Scanner(file);
                boolean codeFound = false;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" gives ");
                    if (parts.length == 2) {
                        String code = parts[0];
                        double discountPercent = Double.parseDouble(parts[1]);

                        if (code.equals(discountCode)) {
                            discountAmount = totalPrice * (discountPercent / 100);
                            System.out.println("Discount applied: " + discountPercent + "%");
                            System.out.println("Discount amount: " + discountAmount + "LE");
                            codeFound = true;
                            break;
                        }
                    }
                }
                scanner.close();

                if (!codeFound) {
                    System.out.println("Invalid discount code");
                } else {
                    break;
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error reading discount codes: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("NullPointerException: The discount codes file may not exist or be accessible.");
            }
        }

        this.totalPriceAfterDiscount = totalPrice - discountAmount;
        return this.totalPriceAfterDiscount;
    }

    /*
     * public Order checkout() {
     * Order order = new Order(this);
     * return order;
     * }
     */
    public Order checkout() throws IOException {
        Order order = new Order(this); // create a new order with a copy of the shopping cart
        try {
            // write order details to a file
            File file = new File("..\\Online-Shopping-System\\data\\ConfirmedOrders\\" + 1 + ".txt");
            // File file = new File("orders/_" + order.getOrderNumber() + ".txt");
            FileWriter writer = new FileWriter(file);
            writer.write("Order Number: " + order.getOrderNumber() + "\n");
            // writer.write("User: " + user.getUsername() + "\n");
            writer.write("Order Date: " + new Date() + "\n");
            writer.write("Items:\n");
            for (int i = 0; i < items.size(); i++) {
                Product item = items.get(i);
                int quantity = quantities.get(i);
                writer.write(item.getName() + " x " + quantity + " - " + item.getPrice() * quantity + " LE\n");
            }
            writer.write("Total Price: " + getTotalPriceBeforeDiscount() + " LE\n");
            writer.write("Discount: " + (getTotalPriceBeforeDiscount() - getTotalPriceAfterDiscount()) + " LE\n");
            writer.write("Total Price After Discount: " + getTotalPriceAfterDiscount() + " LE\n");
            writer.close();
            System.out.println("Order saved to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing order to file: " + e.getMessage());
        }
        return order;

    }

}
