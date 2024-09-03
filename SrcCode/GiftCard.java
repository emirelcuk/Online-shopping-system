package SrcCode;

/**
 *
 * @author Habiba
 */
public class GiftCard implements Payment {

    private String cardNumber;
    private String pin;
    private double balance;

    public GiftCard(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    @Override
    public void pay(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance on gift card");
        }
        balance -= amount;
        System.out.println("Processing gift card payment of " + amount + " LE with card number " + cardNumber);
    }

    @Override
    public boolean isValid() {
        // Check if the card number and pin are valid
        return cardNumber != null && !cardNumber.isEmpty() && pin != null && !pin.isEmpty();
    }

    @Override
    public String getErrorMessage() {
        if (!isValid()) {
            return "Invalid gift card number or pin";
        } else if (balance < 0) {
            return "Not enough balance on gift card";
        } else {
            return "";
        }
    }
}
