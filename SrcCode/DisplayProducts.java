package SrcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import SrcCode.SubCategories.ProductNotFoundException;

public class DisplayProducts {
	static Product product1=null;
	Scanner input = new Scanner(System.in);
	SubCategories subcat=null;
	DisplayProducts() throws FileNotFoundException, ProductNotFoundException
	{

        String product,cat,answer;
        
        do {
        	System.out.println("Choose a category or search a product (Enter choose or search): ");
            answer=input.next();
            if(answer.equalsIgnoreCase("Choose"))
            {
            try {
            	File file = new File("..\\Online-Shopping-System\\data\\SubCategories");
            	String[] contents = file.list();
            	System.out.println("Enter subcategory name: ");
            	String sub=input.next();
                int found=0;
                for (int i = 0; i < contents.length; i++) {
                	if(sub.equalsIgnoreCase(contents[i])) {
                		subcat= new SubCategories(contents[i]);
//                		System.out.println(contents[i]);
//                                System.out.println(subcat.getProducts().get(0));
                		System.out.println("Choose a product");
                		String product2 = input.next();
                		product1= new Product(new File("..\\Online-Shopping-System\\data\\SubCategories\\"+contents[i]+"\\" + product2 + ".txt"));
                		subcat.searchProduct(product2);
                		found=1;
                	}
                	
                    
                }
                if(found==0)
                	{
                		System.out.println("Product not found, retry: ");
                		DisplayProducts prod = new DisplayProducts();
                	}
            }
            catch(FileNotFoundException e)
            {
            	System.out.println("An error occurred.");
                e.printStackTrace();
            }
            }
            else if(answer.equalsIgnoreCase("Search"))
            {
            	System.out.println("Enter name of product: ");
            	product= input.next();
            	int found=0;
            	do {
            		try
                	{
                		subcat.searchProduct(product);
                	}
                	catch(ProductNotFoundException e)
                	{
                		System.out.println("Product doesn't exist");
                		found=1;
                	}
            		
            	}while(found==1);
            	
            }
            
        } while (!answer.equalsIgnoreCase("Choose") && !answer.equalsIgnoreCase("Search"));
	}
	public static Product product()
	{
		return product1;
	}

}