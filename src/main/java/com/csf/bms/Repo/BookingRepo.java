package com.csf.bms.Repo;

import com.csf.bms.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {

    List<Booking>findByUserId(Long userId);

    Optional<Booking> findByBookingNumber(String booking);

    List<Booking> findByShowId(Long id);
}
