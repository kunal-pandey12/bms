package com.csf.bms.Repo;
import com.csf.bms.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User>findByEmail(String email);
}
