/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SrcCode;

/**
 *
 * @author Habiba
 */
public class CashOnDelivery implements Payment {
    private double cashAmount;

    public CashOnDelivery(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Well, the shipping representative will bill you");
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getErrorMessage() {
        return "";
    }
}
