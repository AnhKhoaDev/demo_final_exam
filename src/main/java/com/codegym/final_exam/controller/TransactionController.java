package com.codegym.final_exam.controller;

import com.codegym.final_exam.dto.TransactionDTO;
import com.codegym.final_exam.model.Customer;
import com.codegym.final_exam.model.Transaction;
import com.codegym.final_exam.service.ICustomerService;
import com.codegym.final_exam.service.ITransactionService;
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

    private final ITransactionService transactionService;
    private final ICustomerService customerService;

    @ModelAttribute("customers")
    public List<Customer> getAllCustomers()  {
        return customerService.findAll();
    }

    @GetMapping
    public String getHomePage(Model model) {
        List<TransactionDTO> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);
        return "/index";
    }

    @GetMapping("/create")
    public String getCreateTransactionPage(Model model) {
        model.addAttribute("transaction", new Transaction());
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

    @GetMapping("/update/{id}")
    public String getUpdateTransaction(Model model, @PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        model.addAttribute("transaction", transaction);

        return "/update";
    }

    @PostMapping("/update/{id}")
    public String updateTransaction(@PathVariable Long id,
                                    @ModelAttribute("transaction") Transaction transaction){
        Transaction transactions = transactionService.findById(id);
        if (transactions != null) {
            transactions.setTransactionDate(transaction.getTransactionDate());
            transactions.getCustomer().setName(transaction.getCustomer().getName());
            transactions.setTypeService(transaction.getTypeService());
            transactions.setTransactionDate(transaction.getTransactionDate());
            transactions.setPrice(transaction.getPrice());
            transactions.setArea(transaction.getArea());
            transactionService.save(transaction);
        }
        return "redirect:/home";
    }

    @PostMapping("/search")
    public String searchTransaction(Model model,
                                    @RequestParam(required = false) String searchTypeService,
                                    @RequestParam(required = false) String searchNameCustomer) {
        searchTypeService = (searchTypeService != null && searchTypeService.isEmpty()) ? null : searchTypeService;
        searchNameCustomer = (searchNameCustomer != null && searchNameCustomer.isEmpty()) ? null : searchNameCustomer;

        if (searchTypeService == null && searchNameCustomer == null){
            model.addAttribute("message", "Không có kết quả nào phù hợp!");
        } else if(searchTypeService == null || searchNameCustomer == null) {
            List<TransactionDTO> transactions = transactionService.findAllByTypeServiceOrNameCustomer(searchTypeService, searchNameCustomer);
            model.addAttribute("transactions", transactions);
        } else {
            List<TransactionDTO> transactions = transactionService.findAllByTypeServiceAndNameCustomer(searchTypeService, searchNameCustomer);
            model.addAttribute("transactions", transactions);
        }
        return "/index";
    }
}