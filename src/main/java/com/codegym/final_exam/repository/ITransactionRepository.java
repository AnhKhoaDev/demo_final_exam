package com.codegym.final_exam.repository;

import com.codegym.final_exam.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t " +
            "INNER JOIN Customer c ON c.id = t.customer.id " +
            "WHERE t.typeService LIKE :searchTypeService " +
            "OR c.name LIKE %:searchNameCustomer% ")
    List<Transaction> findAllByTypeServiceOrNameCustomer(
            @Param("searchTypeService") String searchTypeService,
            @Param("searchNameCustomer") String searchNameCustomer);

    @Query("SELECT t FROM Transaction t " +
            "INNER JOIN Customer c ON c.id = t.customer.id " +
            "WHERE t.typeService LIKE :searchTypeService " +
            "AND c.name LIKE %:searchNameCustomer% ")
    List<Transaction> findAllByTypeServiceAndNameCustomer(
            @Param("searchTypeService") String searchTypeService,
            @Param("searchNameCustomer") String searchNameCustomer);
}
