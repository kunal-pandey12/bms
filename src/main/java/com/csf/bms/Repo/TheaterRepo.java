package com.csf.bms.Repo;

import com.csf.bms.Model.Theater;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepo extends JpaRepository<Theater,Long> {

    List<Theater> findByCity(String city);

        Optional<Theater> findFirstByCity(String city);
    }


