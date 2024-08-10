package com.codegym.final_exam.service;

import com.codegym.final_exam.dto.TransactionDTO;
import com.codegym.final_exam.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITransactionService {
    List<TransactionDTO> findAll();

    void save(Transaction transaction);

    Transaction findById(Long id);

    void delete(Long id);
}
