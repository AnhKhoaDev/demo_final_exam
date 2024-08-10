package com.codegym.final_exam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^MGD-\\d{4}$", message = "Mã giao dịch phải đúng định dạng (MGD-XXXX), trong đó XXXX là các chữ số (0-9)")
    private String transactionCode;

    @NotNull
    private LocalDate transactionDate;

    @NotNull
    private String typeService;

    @NotNull
    private double price;

    @NotNull
    private double area;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
