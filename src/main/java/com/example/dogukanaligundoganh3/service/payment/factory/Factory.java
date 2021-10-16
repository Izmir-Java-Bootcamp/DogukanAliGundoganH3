package com.example.dogukanaligundoganh3.service.payment.factory;


import com.example.dogukanaligundoganh3.model.interfaces.Payment;
import com.example.dogukanaligundoganh3.type.PaymentType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Factory {
    private final Map<PaymentType, Payment> paymentMap;

    public Factory(List<Payment> payments) {
        this.paymentMap = payments.stream()
                .collect(Collectors.toMap(Payment::getType, payment -> payment));
    }

    public Payment getPaymentMethod(PaymentType type) {
        return paymentMap.get(type);
    }

}
