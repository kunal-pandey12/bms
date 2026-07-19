package com.csf.bms.Repo;

import com.csf.bms.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat,Long> {
    List<Seat> findByScreen_Id(Long screenId);
}
