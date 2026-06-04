package com.csf.bms.Repo;

import com.csf.bms.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment,Long> {

    Optional<Payment> findByTransactionId(String transaction);
}
