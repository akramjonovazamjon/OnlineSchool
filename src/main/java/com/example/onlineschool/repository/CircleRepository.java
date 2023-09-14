package com.example.onlineschool.repository;

import com.example.onlineschool.entity.Circle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircleRepository extends JpaRepository<Circle, Long> {
    boolean existsByName(String name);
}
