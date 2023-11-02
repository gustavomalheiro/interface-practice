package org.example.model.services;

public class PaypalService implements OnlinePaymentService {

    @Override
    public Double paymentFee(Double amount) {
        return amount * 1.02;
    }

    @Override
    public Double interest(Double amount, Integer months) {
        return amount * (1 + (0.01 * months));
    }
}
