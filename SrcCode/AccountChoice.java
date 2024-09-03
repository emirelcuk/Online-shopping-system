package SrcCode;

import java.util.Scanner;

public class AccountChoice {
	 private static String[] dataArr;
	 private String response;
	 Scanner input = new Scanner(System.in);
	AccountChoice()
	{

        do {
            System.out.println("Do you have an account? Y/N");
            response = input.nextLine();
            if (response.equalsIgnoreCase("y")) {
                
                Login account = new Login();
                dataArr = account.getUserData();
            } else if (response.equalsIgnoreCase("n")) {
                   Register account = new Register();
            } else {
                System.out.println("Invalid response. Please enter 'y' or 'n'.");
            } 
        } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
	}
	public String[] getInfo()
	{
		return dataArr;
	}

}
