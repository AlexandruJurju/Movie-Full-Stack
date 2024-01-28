package com.example.backend.repositories;

import com.example.backend.model.Credits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditsRepository extends JpaRepository<Credits, Long> {
}
