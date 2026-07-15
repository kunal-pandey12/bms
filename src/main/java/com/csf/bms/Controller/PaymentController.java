package com.csf.bms.Controller;

import com.csf.bms.Dto.PaymentDto;
import com.csf.bms.Model.Payment;
import com.csf.bms.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Payment initiate karo
    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(
            @RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(
                paymentService.initiatePayment(paymentDto),
                HttpStatus.CREATED);
    }

    // Payment confirm karo
    @PutMapping("/confirm/{paymentId}")
    public ResponseEntity<Payment> confirmPayment(
            @PathVariable Long paymentId) {
        return ResponseEntity.ok(
                paymentService.confirmPayment(paymentId));
    }

    // Payment fail karo
    @PutMapping("/fail/{paymentId}")
    public ResponseEntity<Payment> failPayment(
            @PathVariable Long paymentId) {
        return ResponseEntity.ok(
                paymentService.failPayment(paymentId));
    }

    // Payment status dekho
    @GetMapping("/status/{paymentId}")
    public ResponseEntity<Payment> getPaymentStatus(
            @PathVariable Long paymentId) {
        return ResponseEntity.ok(
                paymentService.getPaymentStatus(paymentId));
    }
}