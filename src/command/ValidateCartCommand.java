package command;

import model.Cart;

public class ValidateCartCommand implements CheckoutCommand {
    private final Cart cart;

    public ValidateCartCommand(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void execute() {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
    }
}