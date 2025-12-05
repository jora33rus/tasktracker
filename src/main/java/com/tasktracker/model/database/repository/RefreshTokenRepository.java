package com.tasktracker.model.database.repository;

import com.tasktracker.model.database.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    RefreshToken findByOwnerUsername(String ownerUsername);

    RefreshToken findByValue(String value);
}
