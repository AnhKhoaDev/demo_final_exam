package com.codegym.final_exam.service;

import com.codegym.final_exam.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
}
