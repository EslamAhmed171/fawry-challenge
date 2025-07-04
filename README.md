# 🛒 Fawry E-Commerce System – Internship Challenge

This repository contains a full-featured e-commerce system designed as part of the **Fawry Quantum Internship Challenge 3**. The system supports products with expiration and shipping behavior using the **Strategy Pattern**, and uses the **Command Pattern** for robust checkout validation.

---

## 🚀 Features

- 🛍️ **Add products to cart** with quantity validation
- 📦 **Handle multiple product types:**
  - Products that expire (e.g., Cheese, Biscuits)
  - Products that require shipping (e.g., TV)
  - Virtual products (e.g., Mobile scratch cards)
- 🧠 **Design Patterns:**
  - **Strategy Pattern** for Expiration and Shipping behaviors
  - **Command Pattern** for checkout validation
- 💳 **Customer checkout system** with:
  - Subtotal calculation
  - Dynamic shipping fee calculation
  - Balance deduction
- 📦 **Shipment notice** with total weight and item breakdown
- 🧪 **Comprehensive test cases** covering:
  - Empty cart scenarios
  - Expired products
  - Insufficient balance
  - Custom product strategies

---

## 🏗️ Project Structure

```
src/
├── model/
│   ├── Product.java
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Customer.java
|   ├── Shippable.java
│   └── ShippableItem.java
├── strategy/
│   ├── expiration/
│   │   ├── ExpirationStrategy.java
│   │   ├── NeverExpiresStrategy.java
│   │   └── DateExpirationStrategy.java
│   └── shipping/
│       ├── ShippingStrategy.java
│       ├── NoShippingStrategy.java
│       └── PhysicalShippingStrategy.java
├── service/
│   ├── ShippingService.java
│   └── ECommerceSystem.java
├── command/
│   ├── CheckoutCommand.java
│   ├── ValidateCartCommand.java
│   ├── ValidateProductsCommand.java
│   └── ValidateBalanceCommand.java
└── Main.java
```

---

## 🛠️ How to Run

1. **Open the project** in IntelliJ IDEA (or any Java IDE)
2. **Run `Main.java`** to execute demo test cases
3. **Review the console output** for shipment and checkout receipts

---

## 🧪 Example Output

### Shipment Notice
```
** Shipment notice **
2x Cheese 400g
1x Biscuits 700g
Total package weight 1.5kg
```

### Checkout Receipt
```
** Checkout receipt **
2x Cheese 200
1x Biscuits 150
1x Mobile Scratch Card 50

Subtotal 400
Shipping 45
Amount 445
```

---

## 📦 Technologies

- **Java 17+**
- **IntelliJ IDEA**
- **OOP Design Principles**
- **Design Patterns** (Strategy, Command)

---

## 📬 Author

**Eslam Ahmed**  
Faculty of Computers and Artificial Intelligence  
Cairo University  

**Contact:** [LinkedIn](https://www.linkedin.com/in/eslam-ahmed-3149362b4/) | [GitHub](https://github.com/EslamAhmed171)

---

## 📁 License

This project is part of a technical challenge and is intended for educational purposes.
