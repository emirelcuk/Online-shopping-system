package SrcCode;

/**
 *
 * @author Habiba
 */
public interface Payment {
    void pay(double amount);
    boolean isValid();
    String getErrorMessage();
}
