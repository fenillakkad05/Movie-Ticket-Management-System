package com.moviebooking.pattern;

public class CreditCardPayment implements PaymentStrategy {
    
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    
    public CreditCardPayment(String cardNumber, String cardHolderName, 
                             String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment of Rs. " + amount);
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Credit Card";
    }
}