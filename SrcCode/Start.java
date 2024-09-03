package SrcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import  SrcCode.SubCategories.ProductNotFoundException;
import java.io.IOException;
public class Start {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        AccountChoice acc = new AccountChoice();
        String[] arr = acc.getInfo();
        Scanner input = new Scanner(System.in);
        //now user is logged in
        String type = Login.typeOfUser();
        //check admin or customer
        if(type.equalsIgnoreCase("customer"))
        {
        	do
        	{
        		System.out.println("Access profile or start shopping? (Enter profile or shopping) ");
            	if(input.next().equalsIgnoreCase("Shopping"))
            	{
	            	try {
						DisplayProducts display = new DisplayProducts();
					} catch (FileNotFoundException | ProductNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                        
                        
                        CustomerShoppingSystem customer = new CustomerShoppingSystem();
            	}
            	else if (input.next().equalsIgnoreCase("Profile"))
            	{

            		Customer customer = new Customer(arr[0],arr[2],arr[3],arr[4],arr[5],arr[6]);
            		customer.ProfileInterface();

            	}
        	}while(!input.next().equalsIgnoreCase("Shopping") && !input.next().equalsIgnoreCase("Profile"));
        }
        	
        	else if(type.equalsIgnoreCase("admin"))
        	// THIS IS DONE FOR ADMIN
        	{
        		String product,subcat,choice;
        		//We chose a specific product
        		Admin admin = new Admin(arr[0],arr[2],arr[3],arr[4],arr[5],arr[6]);
        		do
        		{
        			System.out.println("Do you want to add items for sale, remove items from sale or add and remove discount code? (Enter add or remove or discount)");
            		   choice = input.next();
        			
            		if(choice.equalsIgnoreCase("add"))
            		{
                            System.out.println("Enter product subcategory: ");
        			subcat=input.next();
        			System.out.println("Enter product name: ");
        			product=input.next();
            			try {
    						admin.addProduct(product, subcat);
    					} catch (FileNotFoundException e) {

    						e.printStackTrace();
    					}
            		}
            		else if(choice.equalsIgnoreCase("remove"))
            		{  System.out.println("Enter product subcategory: ");
        			subcat=input.next();
        			System.out.println("Enter product name: ");
        			product=input.next();
            				admin.removeProduct(product, subcat);
    					
            		}
            		else if(choice.equalsIgnoreCase("discount"))
            		{
            			do
            			{
            				System.out.println("Enter discount code: ");
                			String code=input.next();
            				System.out.println("Do you want to add or remove discount code? ");
                			if(input.next().equalsIgnoreCase("add"))
                			{
                			System.out.println("Enter percentage: ");
                			int percent=input.nextInt();
                			admin.addDiscountCode(code, percent);
                			}
                			else if(input.next().equalsIgnoreCase("remove"))
                			{
                				admin.removeDiscountCode(code);
                			}
            			}while(!input.next().equalsIgnoreCase("add") && input.next().equalsIgnoreCase("remove"));
            		}
        		}while(!choice.equalsIgnoreCase("add") && !choice.equalsIgnoreCase("remove") && !choice.equalsIgnoreCase("discount") );
                       ////////////////////////////////////////////////// 
                        do{
                      System.out.println("do you want to visit your profile (Enter Y or N)");
                      choice=input.next();
                      if(choice.equalsIgnoreCase("Y")){
                      
                      admin.ProfileInterface();
                      
                      }
                      else if(choice.equalsIgnoreCase("N"))
                      System.out.println("thank you for visiting the site");
                        }while(!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("Y"));
        
                
                
                
                
                }
        



//File file =new File("..\\Online-Shopping-System\\data\\SubCategories\\Fashion\\Blouse.txt");
//if(file.exists()){
//
//System.out.println("the file exists");
//
//}
//else {
//    System.out.println("the file  does not exists");
// }

//File file = new File("..\\Online-Shopping-System\\data\\SubCategories");
//            	String[] contents = file.list();
//     for(int i=0;i<contents.length;i++)
//         System.out.println(contents[i]);
//     
     
//             SubCategories sub=new SubCategories(contents[0]);
//             System.out.println(sub.getProducts().get(0).getProductId());
//String nameOfProduct="Blouse";
//File myfile=new File("..\\Online-Shopping-System\\data\\SubCategories\\"+contents[0]+"\\" + nameOfProduct+ ".txt");
////File file =new File("..\\Online-Shopping-System\\data\\SubCategories\\Fashion\\Blouse.txt");
//if(myfile.exists()){
//
//System.out.println("the file exists");
//
//}
//else {
//    System.out.println("the file  does not exists");
// }

            
   } 
    }

    
