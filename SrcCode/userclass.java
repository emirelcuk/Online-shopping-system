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
        return wishlist;
    }

    public void addToWishlist(WishlistItem item) {
        wishlist.add(item);
        System.out.println(item.getName() + " added to wishlist.");
    }

    public void removeFromWishlist(int itemId) {
        wishlist.removeIf(item -> item.getId() == itemId);
        System.out.println("Item with ID " + itemId + " removed from wishlist.");
    }

    public void viewWishlist() {
        System.out.println("Wishlist for " + username + ":");
        if (wishlist.isEmpty()) {
            System.out.println("Your wishlist is empty.");
        } else {
            for (WishlistItem item : wishlist) {
                System.out.println(item);
            }
        }
    }
}
