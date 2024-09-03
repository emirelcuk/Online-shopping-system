/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SrcCode;

/**
 *
 * @author Habiba
 */
public class CreditCard implements Payment {
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private boolean isValid;
    private String errorMessage;

    public CreditCard(String cardNumber, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.isValid = false;
        this.errorMessage = "It seems to be a problem.Try another card or change the payment method";

        // Perform validation checks here
        if (cardNumber.length() != 16) {
            this.errorMessage = "Invalid credit card number";
        } else if (!expirationDate.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
            this.errorMessage = "Invalid expiration date";
        } else if (cvv.length() != 3) {
            this.errorMessage = "Invalid CVV";
        } else {
            this.isValid = true;
        }
    }

    @Override
    public void pay(double amount) {
        if (isValid) {
            System.out.println("Processing credit card payment for " + amount + " LE");
        } else {
            System.out.println("Payment is invalid: " + errorMessage);
        }
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
