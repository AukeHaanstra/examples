package nl.pancompany.unicorn.objectoriented;

public class Product {

    private String name;
    private int stockQuantity;

    public Product(String name, int stockQuantity) {
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public void restock(int additionalQuantity) {
        if (additionalQuantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be positive");
        }
        this.stockQuantity += additionalQuantity;
    }

    public boolean isAvailable() {
        return this.stockQuantity > 0;
    }

    public void sell(int quantity) {
        if (quantity > stockQuantity) {
            throw new IllegalArgumentException("Not enough stock to sell");
        }
        this.stockQuantity -= quantity;
    }
}
