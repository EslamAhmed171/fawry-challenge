package command;

import model.Customer;

public class ValidateBalanceCommand implements CheckoutCommand {
    private final Customer customer;
    private final double totalAmount;

    public ValidateBalanceCommand(Customer customer, double totalAmount) {
        this.customer = customer;
        this.totalAmount = totalAmount;
    }

    @Override
    public void execute() {
        if (customer.getBalance() < totalAmount) {
            throw new IllegalArgumentException("Customer's balance is insufficient");
        }
    }
}