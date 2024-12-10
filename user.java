import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private List<WishlistItem> wishlist;

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
        this.wishlist = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<WishlistItem> getWishlist() {
        return wishlist; // Problem: Directly exposing mutable list
    }

    public void addToWishlist(WishlistItem item) {
        if (item == null) { // Problem: Null check not meaningful here
            return; // This silently fails instead of throwing an exception
        }
        wishlist.add(item);
        System.out.println(item.getName() + " added to wishlist.");
    }

    public void removeFromWishlist(int itemId) {
        for (WishlistItem item : wishlist) { 
            if (item.getId() == itemId) {
                wishlist.remove(item); // Problem: ConcurrentModificationException risk
                System.out.println("Item with ID " + itemId + " removed from wishlist.");
                return;
            }
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    public void viewWishlist() {
        System.out.println("Wishlist for " + username + ":");
        for (WishlistItem item : wishlist) { 
            if (item == null) { // Problem: Null check implies potential invalid state
                System.out.println("Found a null item in wishlist.");
            } else {
                System.out.println(item);
            }
        }
    }
}
