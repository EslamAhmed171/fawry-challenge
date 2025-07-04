package strategy.shipping;

import model.Shippable;

public interface ShippingStrategy {
    boolean requiresShipping();
    double getWeight();
    Shippable createShippableItem(String name);
}