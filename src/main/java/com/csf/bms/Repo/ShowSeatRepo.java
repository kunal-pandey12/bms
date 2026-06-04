package com.csf.bms.Repo;

import com.csf.bms.Model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepo extends JpaRepository<ShowSeat,Long> {

    List<ShowSeat> findByShowId(Long movieId);

    List<ShowSeat> findByShowIdAndStatus (Long showId,String status);
}
