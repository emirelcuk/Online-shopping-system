/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SrcCode;

/**
 *
 * @author Habiba
 */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Admin extends User {

    public Admin(String Username, String Name, String email, String age, String address, String phoneNo) {
        super(Username, Name, email, age, address, phoneNo);
    }

    @Override
    public void ProfileInterface() {
        Scanner input = new Scanner(System.in);
        String response;
        System.out.println("Name:" + super.getName());
        System.out.println("UserName:" + super.getUsername());
        System.out.println("Email:" + super.getEmail());
        System.out.println("Age:" + super.getAge());
        System.out.println("Address:" + super.getAddress());
        System.out.println("Phone Number:" + super.getPhoneNo());
        System.out.println("Any Changes? Y/N");
        response = input.nextLine();
        if (response.equalsIgnoreCase("y")) {
            String[] options = {"Age","PhoneNumber"};
            for (int i = 0; i < options.length; i++) {
                System.out.println(options[i]);
            }
            System.out.println("Enter the change you wanna make");
            response = input.nextLine();
            int change = Arrays.asList(options).indexOf(response);
            if (change != -1) {
                System.out.println("Enter the new value");
                String newValue = input.nextLine();
               if (change == 0) {
                    super.setAge(newValue);
                } else if (change == 1) {
                    super.setPhoneNo(newValue);
                }
                updateUserData();
                System.out.println("Done");
            } else {
                System.out.println("Invalid option.");
            }
        } else if (response.equalsIgnoreCase("n")) {
            System.out.println("As you like");
        } else {
            System.out.println("Invalid response. Please enter 'y' or 'n'.");
        }
    }

    @Override
    public void updateUserData() {
        String userDataFilePath = "..\\Online-Shopping-System\\data\\Admins\\Data.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(userDataFilePath));
            updateLine(lines, super.getUsername());
            Files.write(Paths.get(userDataFilePath), lines);
            System.out.println("User data updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLine(List<String> lines, String username) {
        for (int i = 0; i < lines.size(); i++) {
            String[] userData = lines.get(i).split(" ");
            if (userData[0].equals(username)) {
                String updatedLine = createUpdatedLine(userData);
                lines.set(i, updatedLine);
                break;
            }
        }
    }

    private String createUpdatedLine(String[] userData) {
        //String password = userData.length > 1 ? userData[1] : null;
        return super.getUsername() + " " + userData[1] + " " + super.getName() + " " + super.getEmail() + " " + super.getAge() + " " + super.getAddress() + " " + super.getPhoneNo();
    }

    public void addProduct(String ProductName, String SubCategoryName) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String subCategoryPath = "..\\Online-Shopping-System\\data\\SubCategories\\" + SubCategoryName;
        SubCategories NewSubCategory = new SubCategories(SubCategoryName);
        System.out.println("Enter Description of the product: ");
        String description = input.nextLine();
        System.out.println("Enter ProductId: ");
        int productId = input.nextInt();
        System.out.println("Enter avilable quantity: ");
        int quantity = input.nextInt();
        System.out.println("Enter Product price: ");
        double price = input.nextDouble();
        Product product = new Product(ProductName, description, productId, quantity, price, SubCategoryName);
    }

    public void removeProduct(String ProductName, String SubCategoryName) {
        String subCategoryPath = "..\\Online-Shopping-System\\data\\SubCategories\\" + SubCategoryName;
        File removedFile = new File(subCategoryPath + "\\" + ProductName + ".txt");
        removedFile.delete();
    }

    public void addDiscountCode(String DiscountCode, int percentage) {
        String discountFilePath = "..\\Online-Shopping-System\\data\\DiscountCodes\\discount_codes.txt";
        try {
            FileWriter writer = new FileWriter(discountFilePath, true);
            writer.write(DiscountCode + " gives " + percentage);
            writer.write("\n");
            writer.close();
            System.out.println("New Code added to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    public void removeDiscountCode(String DiscountCode) {
        String discountFilePath = "..\\Online-Shopping-System\\data\\DiscountCodes\\discount_codes.txt";
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(discountFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(DiscountCode)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(discountFilePath))) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
            System.out.println("Code deleted from the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) throws FileNotFoundException {
//        Admin admin = new Admin("HabibaMowafy", "Habiba", "hdsfs", "20", "hsiusfh", "454645894146");
//        //admin.addProduct("Blouse", "Fashion");
//        //admin.removeProduct("redDress", "Fashion");
//        admin.removeDiscountCode("ABC5d");
//    }

}
