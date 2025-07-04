package command;

import model.Cart;
import model.CartItem;
import model.Product;

public class ValidateProductsCommand implements CheckoutCommand {
    private final Cart cart;

    public ValidateProductsCommand(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void execute() {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            if (product.isExpired()) {
                throw new IllegalArgumentException("Product " + product.getName() + " is expired");
            }

            if (!product.isAvailable(quantity)) {
                throw new IllegalArgumentException("Product " + product.getName() + " is out of stock");
            }
        }
    }
}