package com.codegym.final_exam.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.codegym.final_exam.dto.TransactionDTO;
import com.codegym.final_exam.model.Transaction;
import com.codegym.final_exam.repository.ITransactionRepository;
import com.codegym.final_exam.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    @Override
    public List<TransactionDTO> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(transaction ->
                TransactionDTO.builder()
                        .id(transaction.getId())
                        .transactionCode(transaction.getTransactionCode())
                        .name(transaction.getCustomer().getName())
                        .transactionDate(transaction.getTransactionDate())
                        .typeService(transaction.getTypeService())
                        .price(transaction.getPrice())
                        .area(transaction.getArea())
                        .build()).toList();
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
