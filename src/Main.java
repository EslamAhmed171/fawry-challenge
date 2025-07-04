import model.*;
import strategy.expiration.*;
import strategy.shipping.*;
import service.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== E-Commerce System Demo ===\n");

        // Create products using factory methods
        Product cheese = Product.createExpirableShippableProduct("Cheese", 100.0, 10,
                LocalDate.now().plusDays(7), 0.4);
        Product biscuits = Product.createExpirableShippableProduct("Biscuits", 150.0, 5,
                LocalDate.now().plusDays(30), 0.7);
        Product tv = Product.createNonExpirableShippableProduct("TV", 2000.0, 3, 5.0);
        Product scratchCard = Product.createNonExpirableNonShippableProduct("Mobile Scratch Card", 50.0, 20);

        // Create customer
        Customer customer = new Customer("John Doe", 1000.0);
        System.out.println("Customer: " + customer.getName() + ", Balance: $" + customer.getBalance());

        // Test Case 1: Normal checkout with mixed products
        System.out.println("\n--- Test Case 1: Normal Checkout ---");
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        try {
            ECommerceSystem.checkout(customer, cart);
            System.out.println("Customer balance after checkout: $" + customer.getBalance());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 2: Empty cart
        System.out.println("\n--- Test Case 2: Empty Cart ---");
        Cart emptyCart = new Cart();
        try {
            ECommerceSystem.checkout(customer, emptyCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 3: Insufficient balance
        System.out.println("\n--- Test Case 3: Insufficient Balance ---");
        Cart expensiveCart = new Cart();
        expensiveCart.add(tv, 2); // This will cost more than remaining balance
        try {
            ECommerceSystem.checkout(customer, expensiveCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 4: Out of stock
        System.out.println("\n--- Test Case 4: Out of Stock ---");
        Cart stockCart = new Cart();
        try {
            stockCart.add(cheese, 15); // More than available quantity
            try {
                ECommerceSystem.checkout(customer, stockCart);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 5: Expired product
        System.out.println("\n--- Test Case 5: Expired Product ---");
        Product expiredCheese = Product.createExpirableShippableProduct("Expired Cheese", 100.0, 5,
                LocalDate.now().minusDays(1), 0.4);
        Cart expiredCart = new Cart();
        try {
            expiredCart.add(expiredCheese, 1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 6: Only non-shippable items
        System.out.println("\n--- Test Case 6: Only Non-Shippable Items ---");
        Cart digitalCart = new Cart();
        digitalCart.add(scratchCard, 3);
        Customer customer2 = new Customer("Jane Smith", 500.0);
        try {
            ECommerceSystem.checkout(customer2, digitalCart);
            System.out.println("Customer2 balance after checkout: $" + customer2.getBalance());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}