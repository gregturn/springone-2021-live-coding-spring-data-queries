package com.greglturnquist.springbootlearning;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByNameContainingIgnoreCase(String partialMatch);

    List<Employee> findByManagerName(String name);

}
