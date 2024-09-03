package SrcCode;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import  SrcCode.ShoppingCart.QuantityExceededException;
import SrcCode.SubCategories.ProductNotFoundException;
import java.io.IOException;

public class CustomerShoppingSystem {
	
	Scanner input = new Scanner(System.in);
	private ShoppingCart cart = new ShoppingCart();
	CustomerShoppingSystem() throws IOException
	{
		{
        	// THIS IS DONE FOR CUSTOMER
        	//Inside a specific product choose options for customer
        	int exists=0;
        	Product product = DisplayProducts.product(); //has object of the product you chose
        	for(int i=0;i<cart.getItems().size();i++)
        	{
        		if(cart.getItems().get(i).equals(product))
        		{
        			exists=1;
        			break;
        		}
        	}
        	System.out.println("Add to cart or remove from cart: (Enter add or remove)");       	
        	if(input.next().equalsIgnoreCase("add"))
        	{
        		if(exists==0) //not in cart
        			{
        				cart.addItem(product);
        			}
        		else //in cart already
        		{
        			try {
						cart.Increase(product);
					} catch (QuantityExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	}
        	else if(input.next().equalsIgnoreCase("remove"))
        	{
        		if(exists==0)//not incart
        		{
        			cart.removeItem(product);
        		}
        		else //in cart already
        		{
        			cart.Decrease(product);
        		}
        	}
        	String mychoice;
        	do
        	{
        		
        		System.out.println("Continue shopping or access shopping cart? (Enter continue or cart)");
        		mychoice=input.next();
            	if(mychoice.equalsIgnoreCase("continue"))
            	{
                    try {
						DisplayProducts display2 = new DisplayProducts();
					} catch (FileNotFoundException e) {
						
						e.printStackTrace();
					} catch (ProductNotFoundException e) {
						
						e.printStackTrace();
					}
            	}
            	else if(mychoice.equalsIgnoreCase("cart"))
            	{
            		cart.displayCart();
            		System.out.println(cart.getTotalPrice());
            		
                        do{
                            System.out.println("Do you want to clear cart, get checkout, cancel an order or remove item from cart? (Enter: clear, checkout,cancel, remove)");
            		if(input.next().equalsIgnoreCase("clear"))
            			cart.clearCart();
            		else if(input.next().equalsIgnoreCase("checkout"))
            		{
                            System.out.println("checkouthello");
            			Order order = cart.checkout();
            			double amount=0;
        				do
        				{
        					System.out.println("Do you have a discount code? (Enter Y/N)");
            				if(input.next().equalsIgnoreCase("Y"))
            				{
            					cart.applyDiscountCode();
            					amount = cart.getTotalPriceAfterDiscount();
            				}
            				else if(input.next().equalsIgnoreCase("N"))
            				{
            					amount = cart.getTotalPriceBeforeDiscount();
            				}
        				}while(!input.next().equalsIgnoreCase("Y") && !input.next().equalsIgnoreCase("N"));
            			do
            			{
            				System.out.println("Do you want to pay by credit card, gift card or cash on delivery? (Enter credit,giftcard or cash): ");
            				order.pay(input.next());
            			}while(!input.next().equalsIgnoreCase("credit") && !input.next().equalsIgnoreCase("giftcard")&& !input.next().equalsIgnoreCase("cash"));
            			order.Confirmation();
            			
            		}	
            		else if(input.next().equalsIgnoreCase("remove"))
            		{
            			int found=0;
            			do
            			{
                			System.out.println("Choose product to remove");
                			for(int i=0;i<cart.getItems().size();i++)
                			{
                				if(input.next().equalsIgnoreCase(cart.getItems().get(i).getName()))
                				{
                					cart.Decrease(cart.getItems().get(i));
                					found=1;
                					break;
                				}
                			}
                			if(found==0)
                			{
                				System.out.println("Item is not in cart, retry: ");
                			}
            			}while(found==0);
            		}
            		else if(input.next().equalsIgnoreCase("cancel"))
            		{
            			System.out.println("Do you want to cancel an order? Y/N");
            			if(input.next().equalsIgnoreCase("Y"))
            			{
            				System.out.println("Enter Date: (MM/DD/YYYY) ");
            				String strDate = input.next();
            		        LocalDate orderDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("M/d/u"));
            				Order order = new Order(cart,orderDate);
            				order.CancelOrder();
            			}
            			else if(input.next().equalsIgnoreCase("N"))
            			{
            				break;
            			}
        			}while(!input.next().equalsIgnoreCase("Y") && !input.next().equalsIgnoreCase("N"));
                        }while(!input.next().equalsIgnoreCase("clear") && !input.next().equalsIgnoreCase("checout")&&!input.next().equalsIgnoreCase("cancel")&&!input.next().equalsIgnoreCase("remove"));
            		}
            	}
            	while (!mychoice.equalsIgnoreCase("continue") && !mychoice.equalsIgnoreCase("cart"));
        	}
	}

}
