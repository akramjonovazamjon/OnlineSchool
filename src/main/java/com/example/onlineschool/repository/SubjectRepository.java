package com.example.onlineschool.repository;

import com.example.onlineschool.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByIdIn(Collection<Long> id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
