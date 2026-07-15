package com.csf.bms.Service;

import com.csf.bms.Dto.PaymentDto;
import com.csf.bms.Model.Payment;
import com.csf.bms.Repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    // Payment initiate karo — PENDING status
    public Payment initiatePayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setAmount(paymentDto.getAmount());
        payment.setStatus("PENDING");
        return paymentRepo.save(payment);
    }

    // Payment confirm karo — SUCCESS
    public Payment confirmPayment(Long paymentId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus("SUCCESS");
        return paymentRepo.save(payment);
    }

    // Payment fail karo — FAILED
    public Payment failPayment(Long paymentId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus("FAILED");
        return paymentRepo.save(payment);
    }

    // Payment status dekho
    public Payment getPaymentStatus(Long paymentId) {
        return paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}