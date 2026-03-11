package com.moviebooking.pattern;

public interface PaymentStrategy {
    boolean processPayment(double amount);
    String getPaymentMethodName();
}