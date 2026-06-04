package com.csf.bms.Repo;

import com.csf.bms.Model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepo extends JpaRepository<Screen,Long> {

    List<Screen> findByTheaterId(Long theaterId);
}
