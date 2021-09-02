package com.greglturnquist.springbootlearning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootlearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootlearningApplication.class, args);
    }

    @Bean
    CommandLineRunner initializeDatabase(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        return args -> {

            Manager gandalf = managerRepository.save(new Manager("Gandalf"));

            Employee frodo = employeeRepository.save(new Employee("Frodo Baggins", "ring bearer"));
            Employee bilbo = employeeRepository.save(new Employee("Bilbo Baggins", "burglar"));

            gandalf.addEmployee(frodo);
            gandalf.addEmployee(bilbo);

            managerRepository.save(gandalf);
            employeeRepository.save(frodo);
            employeeRepository.save(bilbo);

            Manager saruman = managerRepository.save(new Manager("Saruman"));

            Employee sam = employeeRepository.save(new Employee("Samwise Gamgee", "gardener"));

            saruman.addEmployee(sam);
            managerRepository.save(saruman);
            employeeRepository.save(sam);
        };
    }

}
