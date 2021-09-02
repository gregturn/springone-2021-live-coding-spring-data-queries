package com.greglturnquist.springbootlearning;

import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private EmployeeRepository employeeRepository;
    private ManagerRepository managerRepository;

    public ApiController(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    @GetMapping("/api/employees")
    List<Employee> all() {
        return employeeRepository.findAll();
    }

    @GetMapping("/api/employees/{id}")
    Employee one(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find anyone at '" + id + "'"));
    }

    @GetMapping("/api/employees/search/{partialMatch}")
    List<Employee> searchBasedOnName(@PathVariable String partialMatch) {
        return employeeRepository.findByNameContainingIgnoreCase(partialMatch);
    }

    @GetMapping("/api/employees/manager/{managerName}")
    List<Employee> searchBasedOnManager(@PathVariable String managerName) {
        return employeeRepository.findByManagerName(managerName);
    }

    @GetMapping("/api/employees/search")
    List<Employee> searchOnAnything(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "managerName", required = false) String managerName) {
        Employee example = new Employee();
        example.setName(name);
        example.setManager(new Manager(managerName));

        Example<Employee> probe = Example.of(example);

        return employeeRepository.findAll(probe);
    }
}
