package com.codegym.final_exam.controller;

import com.codegym.final_exam.dto.TransactionDTO;
import com.codegym.final_exam.model.Customer;
import com.codegym.final_exam.model.Transaction;
import com.codegym.final_exam.service.impl.CustomerService;
import com.codegym.final_exam.service.impl.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class TransactionController {

    private final TransactionService transactionService;
    private final CustomerService customerService;

    @GetMapping
    public String getHomePage(Model model) {
        List<TransactionDTO> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);
        return "/index";
    }

    @GetMapping("/create")
    public String getCreateTransactionPage(Model model) {
        model.addAttribute("transaction", new Transaction());
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/create";
    }

    @PostMapping("/create")
    public String createTransaction(Model model,
                                    @Valid @ModelAttribute("transaction") Transaction transaction,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/home/create";
        }

        transactionService.save(transaction);

        return "redirect:/home";
    }

    @GetMapping("/detail/{id}")
    public String getTransactionDetail(Model model, @PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        model.addAttribute("transaction", transaction);

        return "/detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(Model model, @PathVariable Long id) {
        transactionService.delete(id);
        return "redirect:/home";
    }
}