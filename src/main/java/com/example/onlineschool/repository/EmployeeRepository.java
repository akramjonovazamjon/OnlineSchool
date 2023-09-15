package com.example.onlineschool.repository;

import com.example.onlineschool.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Page<Employee> findAllByDepartment(String department, Pageable pageable);

}
