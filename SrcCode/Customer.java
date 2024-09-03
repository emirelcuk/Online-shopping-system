package SrcCode;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    // Constructor
    public Customer(String username, String name, String email, String age, String address, String phoneNo) {
        super(username, name, email, age, address, phoneNo);
    }

    // Method to update user data
    public void updateUserData() {
        String userDataFilePath = "..\\Online-Shopping-System\\data\\Customers\\Data.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(userDataFilePath));
            int lineIndex = findLineIndex(lines, super.getUsername());
            if (lineIndex != -1) {
                String updatedLine = createUpdatedLine(lines.get(lineIndex).split(" "));
                lines.set(lineIndex, updatedLine);
                Files.write(Paths.get(userDataFilePath), lines);
                System.out.println("User data updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    // Method to find the line index
    private int findLineIndex(List<String> lines, String username) {
        for (int i = 0; i < lines.size(); i++) {
            String[] userData = lines.get(i).split(" ");
            if (userData[0].trim().equals(username.trim())) {
                return i;
            }
        }
        return -1;
    }

    // Method to create an updated line
    private String createUpdatedLine(String[] userData) {
        String password = userData.length > 1 ? userData[1] : "";
        return super.getUsername() + " " + password + " " + super.getName() + " " + super.getEmail() + " " + super.getAge() + " " + super.getAddress() + " " + super.getPhoneNo();
    }

    // Method to interface with the profile
    @Override
    public void ProfileInterface() {
        Scanner input = new Scanner(System.in);
        String response;
        printProfileDetails();
        System.out.println("Any Changes? Y/N");
        response = input.nextLine();
        if (response.equalsIgnoreCase("y")) {
            String[] options = {"UserName", "Name", "email", "age", "address", "PhoneNumber"};
            printOptions(options);
            System.out.println("Enter the change you wanna make");
            response = input.nextLine();
            int change = Arrays.asList(options).indexOf(response);
            if (change != -1) {
                System.out.println("Enter the new value");
                String newValue = input.nextLine();
                updateProfileDetails(change, newValue);
                updateUserData(); // update user data after making changes
            }
        }
    }

    // Method to print profile details
    private void printProfileDetails() {
        System.out.println("Name:" + super.getName());
        System.out.println("UserName:" + super.getUsername());
        System.out.println("Email:" + super.getEmail());
        System.out.println("Age:" + super.getAge());
        System.out.println("Address:" + super.getAddress());
        System.out.println("Phone Number:" + super.getPhoneNo());
    }

    // Method to print options
    private void printOptions(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
    }

    // Method to update profile details
    private void updateProfileDetails(int change, String newValue) {
        if (change == 0) {
            super.setUsername(newValue);
        } else if (change == 1) {
            super.setName(newValue);
        } else if (change == 2) {
            super.setEmail(newValue);
        } else if (change == 3) {
            super.setAge(newValue);
        } else if (change == 4) {
            super.setAddress(newValue);
        } else if (change == 5) {
            super.setPhoneNo(newValue);
        }
    }

    // Main method
//    public static void main(String[] args){
//        Login acc = new Login();
//        Customer customer = new Customer(acc.getUserData()[0], acc.getUserData()[2],acc.getUserData()[3],acc.getUserData()[4],acc.getUserData()[5],acc.getUserData()[6]);
//        customer.ProfileInterface();
//    }
}