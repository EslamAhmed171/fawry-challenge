package model;

import strategy.expiration.*;
import strategy.shipping.*;

import java.time.LocalDate;

public class Product {
    private final String name;
    private final double price;
    private int quantity;
    private final ExpirationStrategy expirationStrategy;
    private final ShippingStrategy shippingStrategy;

    public Product(String name, double price, int quantity,
                   ExpirationStrategy expirationStrategy,
                   ShippingStrategy shippingStrategy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationStrategy = expirationStrategy;
        this.shippingStrategy = shippingStrategy;
    }

    // Factory methods for common product types
    public static Product createExpirableShippableProduct(String name, double price, int quantity,
                                                          LocalDate expirationDate, double weight) {
        return new Product(name, price, quantity,
                new DateExpirationStrategy(expirationDate),
                new PhysicalShippingStrategy(weight));
    }

    public static Product createNonExpirableShippableProduct(String name, double price, int quantity,
                                                             double weight) {
        return new Product(name, price, quantity,
                new NeverExpiresStrategy(),
                new PhysicalShippingStrategy(weight));
    }

    public static Product createExpirableNonShippableProduct(String name, double price, int quantity,
                                                             LocalDate expirationDate) {
        return new Product(name, price, quantity,
                new DateExpirationStrategy(expirationDate),
                new NoShippingStrategy());
    }

    public static Product createNonExpirableNonShippableProduct(String name, double price, int quantity) {
        return new Product(name, price, quantity,
                new NeverExpiresStrategy(),
                new NoShippingStrategy());
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Delegation to strategies
    public boolean isExpired() {
        return expirationStrategy.isExpired();
    }

    public boolean requiresShipping() {
        return shippingStrategy.requiresShipping();
    }

    public double getWeight() {
        return shippingStrategy.getWeight();
    }

    public Shippable createShippableItem() {
        return shippingStrategy.createShippableItem(name);
    }

    // Business logic
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }

    public void reduceQuantity(int amount) {
        if (amount <= quantity) {
            quantity -= amount;
        } else {
            throw new IllegalArgumentException("Not enough stock available");
        }
    }
}