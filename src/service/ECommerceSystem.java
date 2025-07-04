package service;

import model.*;
import command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ECommerceSystem {
    public static void checkout(Customer customer, Cart cart) {
        // Collect shippable items
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            if (product.requiresShipping()) {
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add(product.createShippableItem());
                }
            }
        }

        // Calculate totals
        double subtotal = cart.getSubtotal();
        double shippingFee = ShippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        // Execute validation commands
        List<CheckoutCommand> validationCommands = Arrays.asList(
                new ValidateCartCommand(cart),
                new ValidateProductsCommand(cart),
                new ValidateBalanceCommand(customer, totalAmount)
        );

        for (CheckoutCommand command : validationCommands) {
            command.execute();
        }

        // Process shipment if needed
        ShippingService.processShipment(shippableItems);

        // Deduct inventory and customer balance
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        customer.deductBalance(totalAmount);

        // Print checkout receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f%n", item.getQuantity(),
                    item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFee);
        System.out.printf("Amount %.0f%n", totalAmount);
        System.out.println("END.");
    }
}