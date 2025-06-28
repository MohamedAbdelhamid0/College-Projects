package org.example.service;

import org.example.model.Client;
import java.math.BigDecimal;

public class PaymentService {

    // Process a payment from a client; returns true if successful.
    public boolean processPayment(Client client, double amount) {
        try {
            client.deductFunds(amount);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Refund a payment back to the client.
    public void refundPayment(Client client, double amount) {
        client.addFunds(amount);
    }
}
