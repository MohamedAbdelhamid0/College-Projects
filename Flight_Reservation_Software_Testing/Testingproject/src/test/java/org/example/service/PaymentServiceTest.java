package org.example.service;

import org.example.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private PaymentService paymentService;
    private Client testClient;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService();
        testClient = new Client(1L, "Test Client", "client@gmail.com","Test2004!",1000.0);
        testClient.setBalance(1000.00); // Assuming Client has setBalance method
    }

    @Test
    void processPayment_SufficientFunds_ReturnsTrue() {
        boolean result = paymentService.processPayment(testClient, 500.00);
        assertTrue(result);
        assertEquals(500.00, testClient.getBalance(), 0.001);
    }

    @Test
    void processPayment_InsufficientFunds_ReturnsFalse() {
        boolean result = paymentService.processPayment(testClient, -1500.00);
        assertFalse(result);
        assertEquals(1000.00, testClient.getBalance(), 0.001);
    }

    @Test
    void refundPayment_IncreasesBalanceCorrectly() {
        paymentService.refundPayment(testClient, 200.00);
        assertEquals(1200.00, testClient.getBalance(), 0.001);
    }

    @Test
    void processPayment_ExactAmount_Succeeds() {
        testClient.setBalance(500.00);
        boolean result = paymentService.processPayment(testClient, 500.00);
        assertTrue(result);
        assertEquals(0.00, testClient.getBalance(), 0.001);
    }

    @Test
    void processPayment_ZeroAmount_ReturnsTrue() {
        boolean result = paymentService.processPayment(testClient, 0);
        assertTrue(result);
        assertEquals(1000.00, testClient.getBalance(), 0.001);
    }

    @Test
    void refundPayment_NegativeAmount_AddsNegativeValue() {

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.refundPayment(testClient, -100.00);
        });
    }
}