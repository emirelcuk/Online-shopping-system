package SrcCode;

public abstract class User {

    private String name, email, username, password, address;
    private long phoneNo;
    private int age;

    User(String Username,String Name, String email, String age, String address, String phoneNo) {
        this.username = Username;
        this.name = Name;
        this.email = email;
        this.address = address;
        this.phoneNo = Long.valueOf(phoneNo).longValue();
        this.age = Integer.valueOf(age);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public int getAge() {
        return age;
    }
    
    public void setName(String Name) {
        this.name = Name;
    }

    public void setEmail(String Email) {
        this.email = email;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

//    public void setPassword() {
//        return password;
//    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = Long.valueOf(phoneNo).longValue();
    }

    public void setAge(String age) {
        this.age = Integer.valueOf(age);
    }
    
//    public void setPassword() {
//        System.out.print("Set a new password: ");
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Password updated successfully");
//        this.password = scanner.next();
//    }
    
    public abstract void ProfileInterface();
    
    public abstract void updateUserData();
 
}
