package SrcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
	private String userName, password;
	String[] userData;
	static String userType;

	Login() {
		Scanner input = new Scanner(System.in);
		System.out.println("Are you an admin or a customer ? (Enter 'admin' or 'customer')");
		userType = input.next();
		System.out.println("Enter username: ");
		userName = input.next();
		System.out.println("Enter password: ");
		password = input.next();

		try {
			File file = null;
			if (userType.equalsIgnoreCase("admin")) {
				file = new File("..//Online-Shopping-System\\data\\Admins\\Data.txt");
			} else if (userType.equalsIgnoreCase("customer")) {
				file = new File("..//Online-Shopping-System\\data\\Customers\\Data.txt");
			} else {
				System.out.println("Invalid input ...Try again");
				Login login = new Login();
			}

			Scanner scanner = new Scanner(file);
			boolean userFound = false;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				this.userData = line.split(" ");
				if (userData[0].equals(userName)) {
					userFound = true;
					if (userData[1].equals(password)) {
						System.out.println("Login successfully");
						if (userType.equalsIgnoreCase("admin")) {
							Admin admin = new Admin(userData[0], userData[2], userData[3], userData[4],
									userData[5], userData[6]);
							System.out.println("Welcome admin");
						} else {
							Customer customer = new Customer(userData[0], userData[2], userData[3], userData[4],
									userData[5], userData[6]);
						}
					} else {
						for (int i = 1; i <= 5; i++) {
							System.out.println("Invalid password");
							System.out.println("Attempts Remaining :" + (5 - i));
							System.out.println("Enter password Again: ");
							password = input.next();
							if (userData[1].equals(password) && (userType.equalsIgnoreCase("customer"))) {
								Customer customer = new Customer(userData[0], userData[2], userData[3], userData[4],
										userData[5], userData[6]);
								break;
							} else if (userData[1].equals(password) && (userType.equalsIgnoreCase("admin"))) {
								Admin admin = new Admin(userData[0], userData[2], userData[3], userData[4],
										userData[5], userData[6]);
								break;
							}
							if (i == 5) {
								System.out.println("Maximum Attempted reached ..login locked due to security actions");
                                                                System.exit(0);
							}
						}
					}
				}
			}
			scanner.close();

			if (!userFound) {
				System.out.println("Username doesn't exist, register an account");
				Register register = new Register();
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred. File Not Found");
			e.printStackTrace();
		}
	}
	public String[] getUserData(){
		return this.userData;
	}
	public static String typeOfUser()
	{
		return userType;
	}

//	public static void main(String[] args) {
//		Login login = new Login();
//                for (int i = 0; i < login.getUserData().length; i++){
//                    System.out.println((login.getUserData())[i]);
//                }
//	}
}