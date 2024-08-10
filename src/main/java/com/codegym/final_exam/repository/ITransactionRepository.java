package com.codegym.final_exam.repository;

import com.codegym.final_exam.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAll();

    Transaction save(Transaction transaction);
}
