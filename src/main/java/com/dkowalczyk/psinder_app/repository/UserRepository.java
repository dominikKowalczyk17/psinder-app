package com.dkowalczyk.psinder_app.repository;

import com.dkowalczyk.psinder_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    boolean existsByName(String name);
}
