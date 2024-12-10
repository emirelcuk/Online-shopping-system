public class WishlistItem {
    private int id;
    private String name;

    public WishlistItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "WishlistItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
