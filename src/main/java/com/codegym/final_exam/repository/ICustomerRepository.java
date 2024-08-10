package com.codegym.final_exam.repository;

import com.codegym.final_exam.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
