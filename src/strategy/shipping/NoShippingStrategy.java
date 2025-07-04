package strategy.shipping;

import model.Shippable;

public class NoShippingStrategy implements ShippingStrategy {
    @Override
    public boolean requiresShipping() {
        return false;
    }

    @Override
    public double getWeight() {
        return 0.0;
    }

    @Override
    public Shippable createShippableItem(String name) {
        return null; // No shipping required
    }
}