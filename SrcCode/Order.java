package SrcCode;

/**
 *
 * @author Nouran
 */

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Order {
    private int orderNumber;
    private LocalDate OrderDate;
    private LocalDate DeliveryDate;
    private ShoppingCart shoppingCart;
    List<Product> items;
    double TotalPrice_afterDiscount;
    String creditno;
    ShoppingCart item = new ShoppingCart();

    public Order(ShoppingCart shoppingCart,LocalDate orderDate) {
        this.shoppingCart = shoppingCart;
        this.OrderDate = orderDate;
        this.orderNumber = generateOrderNumber();
    }

 

    // --------setters and getters--------//

    public Order(ShoppingCart shoppingCart2) {
		
	}



	public LocalDate getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.OrderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return (DeliveryDate = OrderDate.plusDays(7));
    }

    // ------------Methods-----------//

    public void Display_Date() { //Done
        System.out.println("Your order will arrive after 7 days");
        System.out.println("OrderDate : " + getOrderDate());
        System.out.println("DeliveryDate : " + getDeliveryDate());
    }

    public void PriceAfterDiscount( ) {
        TotalPrice_afterDiscount = shoppingCart.getTotalPriceAfterDiscount();  // Calculate total price after discount
        System.out.println("Total price after discount = " + TotalPrice_afterDiscount+"$");
    }

    public void CancelOrder() {
        shoppingCart.getItems().clear();
        System.out.println("Order canceled. All items removed from the order.");
    }

    public void pay(String paymentType) {
                if ("cash".equalsIgnoreCase(paymentType)) {
                 CashOnDelivery cashOnDelivery = new CashOnDelivery(TotalPrice_afterDiscount);
				 cashOnDelivery.pay(TotalPrice_afterDiscount);
              
                } else if ("credit".equalsIgnoreCase(paymentType)) {
          	    System.out.println("Enter Credit no :");
                Scanner input = new Scanner(System.in);
        	  try {
            	    creditno = input.nextLine();
            	    if (creditno.length() != 16) 
                    throw new IllegalArgumentException("Credit card number must be 16 digits");
                	      
            	    System.out.print("Enter expiration date (MM/YY): ");
            	    String expirationDate = input.nextLine();
            	    if (!expirationDate.matches("^(0[1-9]|1[0-2])/[0-9]{2}$"))          
                    throw new IllegalArgumentException("Invalid expiration date");
                          
            	    System.out.print("Enter CVV: ");
            	    String cvv = input.nextLine();
            	    if (cvv.length() != 3)
            	    throw new IllegalArgumentException("invalid CVV");         	
               	   CreditCard creditCard = new CreditCard(creditno, expirationDate, cvv);;
				   creditCard.pay(TotalPrice_afterDiscount);
            
             } catch (InputMismatchException e) {
            	       System.out.println("Credit card number must be a valid number");
             } catch (IllegalArgumentException e) {
            	        System.out.println(e.getMessage());
            	  }
            	
           
        } else if ("giftcard".equalsIgnoreCase(paymentType)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter gift card number: ");
            String cardNumber = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            System.out.print("Enter balance: ");
            double balance = scanner.nextDouble();
            GiftCard giftCard = new GiftCard(cardNumber, pin, balance);
            giftCard.pay(TotalPrice_afterDiscount);
        } else {
            System.out.println("Invalid payment method. Please try again.");
        }
    }
    
    

    public void isDelivered() {
        try {
            LocalDate currentDate = LocalDate.now();
            if (currentDate.isAfter(DeliveryDate)) {
                System.out.println("Order is delivered");
            } else {
                if (currentDate.isBefore(DeliveryDate))
                    System.out.println("Order hasn't been delivered yet : Pending");
            }
        } catch (Exception e) {
            System.out.println("Error checking delivery status: " + e.getMessage());
        }
    }

    private int generateOrderNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        System.out.println("Order number : "+ randomNumber);
        return (int) (randomNumber);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    // unfinished
    public Order Confirmation() {
        Order order = new Order(shoppingCart,getOrderDate()); // create a new order with a copy of the shopping cart
        try {
            // write order details to a file
            File file = new File("..\\Online-Shopping-System\\data\\ConfirmedOrders\\" + 1 + ".txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("Order Number: " + order.getOrderNumber() + "\n");
            // writer.write("User: " + user.getUsername() + "\n");
            buffer.write("Order Date: " + new Date() + "\n");
            buffer.write("Items:\n");
            for (int i = 0; i < items.size(); i++) {
                Product item = items.get(i);
                List<Integer> quantity = shoppingCart.getQuantity();
                buffer.write(
                        item.getName() + " x " + quantity.get(i) + " - " + item.getPrice() * quantity.get(i) + " LE\n");
            }
            buffer.write("Total Price: " + shoppingCart.getTotalPriceBeforeDiscount() + " LE\n");
            buffer.write("Discount: "
                    + (shoppingCart.getTotalPriceBeforeDiscount() - shoppingCart.getTotalPriceAfterDiscount())
                    + " LE\n");
            buffer.write("Total Price After Discount: " + shoppingCart.getTotalPriceAfterDiscount() + " LE\n");
            buffer.close();
            System.out.println("Order saved to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing order to file: " + e.getMessage());
        }
        return order;

    }

//    public static void main(String[] args) throws FileNotFoundException {
//        File ProductFile = new File("..\\DataFiles\\Products\\Samsung Phone.txt");
//        Product product1 = new Product(ProductFile);
//        product1.printDetails();
//        List<Product> items = new ArrayList<>();
//        items.add(product1);
//
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.addItem(product1);
//        //shoppingCart.displayCart();
//    
//        LocalDate date = LocalDate.now(); //order date for example
//        Order order1=new Order(shoppingCart,date);
//        order1.Display_Date();      //checked
//        order1.PriceAfterDiscount();  //logical error :prints zero $
//        order1.CancelOrder();      //checked
//        order1.pay("cash");       //checked but also prints zero $
//        order1.generateOrderNumber();  //checked
//        order1.isDelivered();  //checked
//    }
//    
//    
       
 }


