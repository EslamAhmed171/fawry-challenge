package service;

import model.Shippable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 30.0; // 30 per kg

    public static double calculateShippingFee(List<Shippable> items) {
        if (items.isEmpty()) {
            return 0.0;
        }

        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        return totalWeight * SHIPPING_RATE_PER_KG;
    }

    public static void processShipment(List<Shippable> items) {
        if (items.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        Map<String, Integer> itemCounts = new HashMap<>();
        Map<String, Double> itemWeights = new HashMap<>();

        // Count quantities and collect weights
        for (Shippable item : items) {
            itemCounts.put(item.getName(), itemCounts.getOrDefault(item.getName(), 0) + 1);
            itemWeights.put(item.getName(), item.getWeight());
        }

        // Print shipment details
        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String itemName = entry.getKey();
            int count = entry.getValue();
            double weight = itemWeights.get(itemName);
            System.out.printf("%dx %s %.0fg%n", count, itemName, weight * 1000); // Convert to grams
        }

        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}