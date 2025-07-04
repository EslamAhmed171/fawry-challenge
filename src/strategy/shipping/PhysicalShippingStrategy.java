package strategy.shipping;

import model.Shippable;
import model.ShippableItem;

public class PhysicalShippingStrategy implements ShippingStrategy {
    private final double weight;

    public PhysicalShippingStrategy(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public Shippable createShippableItem(String name) {
        return new ShippableItem(name, weight);
    }
}