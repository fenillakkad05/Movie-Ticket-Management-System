package com.moviebooking.pattern;

public class UPIPayment implements PaymentStrategy {
    
    private String upiId;
    
    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of Rs. " + amount + " via " + upiId);
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "UPI";
    }
}