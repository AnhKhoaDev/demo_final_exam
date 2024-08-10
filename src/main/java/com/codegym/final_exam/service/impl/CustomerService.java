package com.codegym.final_exam.service.impl;

import com.codegym.final_exam.model.Customer;
import com.codegym.final_exam.repository.ICustomerRepository;
import com.codegym.final_exam.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
