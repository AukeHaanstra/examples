package nl.pancompany.unicorn.procedural;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public void restockProduct(ProductEntity product, int additionalQuantity) {
        if (additionalQuantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be positive");
        }
        product.setStockQuantity(product.getStockQuantity() + additionalQuantity);
    }

    public boolean isProductAvailable(ProductEntity product) {
        return product.getStockQuantity() > 0;
    }

    public void sellProduct(ProductEntity product, int quantity) {
        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException("Not enough stock to sell");
        }
        product.setStockQuantity(product.getStockQuantity() - quantity);
    }
}
