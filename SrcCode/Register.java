package SrcCode;
import java.io.*;
import java.util.*;

public class Register {

    private String name, email, username, password, address, userInfo;
    private long phoneNo;
    private int age;
    
    Register(String username){
        
    }

    Register() {
        try {
            FileWriter Writer = new FileWriter("..\\Online-Shopping-System\\data\\Customers\\Data.txt", true);
            setName();
            setAge();
            setEmail();
            setUsername();
            setPassword();
            setAddress();
            setPhoneNo();
            Writer.write(username + " " + password + " " + name + " " + email + " " + age + " " + address + " " + phoneNo+"\n");
            Writer.write(System.getProperty("line.separator"));
            Writer.close();
            System.out.println("Successful Registeration");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    void setUsername() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username: ");
        this.username = input.next();
    }

    void setEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter email: ");
        this.email = input.next();
    }

    void setAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter address: ");
        this.address = input.next();
    }

    void setPhoneNo() {
        String phoneNumber;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a phone number: ");
            phoneNumber = scanner.next();
            if (!phoneNumber.matches("\\d+")) {
                System.out.println("Phone number must contain numbers only. Please try again.");
                continue;
            }
            if (phoneNumber.length() != 11) {
                System.out.println("Phone number must be 11 digits. Please try again.");
                continue;
            }
            break;
        }
        this.phoneNo = Long.parseLong(phoneNumber);
    }

    void setName() {
        String Name;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a name: ");
            Name = scanner.next();

            if (Name.matches(".*\\d.*")) {
                System.out.println("Name can only have characters. Please enter a valid one.");
                continue;
            }

            break;
        }

        this.name = Name;
    }

    void setPassword() {
        Scanner scanner = new Scanner(System.in);
        String Password, confirmedPassword;
        while (true) {
            System.out.print("Enter a password: ");
            Password = scanner.next();
            if (Password.length() < 8) {
                System.out.println("Password must be at least 8 characters. Please try again.");
                continue;
            } else {
                System.out.print("Confirm your password: ");
                confirmedPassword = scanner.next();
                if (!confirmedPassword.equals(Password)) {
                    System.out.println("The passwords aren't equal. Please enter a new password and confirm it.");
                    continue;
                } else {
                    break;
                }
            }
        }
        this.password = Password;
    }

    void setAge() {
        Scanner scanner = new Scanner(System.in);
        int Age;
        while (true) {
            try {
                System.out.print("Enter your age: ");
                Age = scanner.nextInt();
                if (Age < 0 || Age > 100) {
                    System.out.println("Age must be between 0 and 100.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Age must be an integer.");
                scanner.next();
            }
        }
        this.age = Age;
    }
    
    public String getUserData() {
        return String.format("%s %s %s %s %d %s %s",
                             this.username,
                             this.password,
                             this.name,
                             this.email,
                             this.age,
                             this.address,
                             this.phoneNo);
    }
}
