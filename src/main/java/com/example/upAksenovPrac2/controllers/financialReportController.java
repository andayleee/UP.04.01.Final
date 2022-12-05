package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.*;
import com.example.upAksenovPrac2.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','BUHGALTER')")
public class financialReportController {
    @Autowired
    private financialReportRepository FinancialReportRepository;
    @Autowired
    private userRepository UserRepository;
    @Autowired
    private coAccountRepository CoAccountRepository;

    @GetMapping("/financialReport")
    public String financialReport(Model model)
    {
        Iterable<financialReport> financialReport = FinancialReportRepository.findAll();
        model.addAttribute("financialReport", financialReport);
        return "financialReportMain";
    }
    @GetMapping("/financialReportAdd")
    public String financialReportAdd(@ModelAttribute("financialReport") financialReport financialReport, Model addr)
    {
        Iterable<user> users = UserRepository.findAll();
        addr.addAttribute("users",users);
        Iterable<coAccount> coAccounts = CoAccountRepository.findAll();
        addr.addAttribute("coAccounts",coAccounts);
        return "financialReportAdd";
    }

    @PostMapping("/financialReportAdd")
    public String financialReportAddAdd(@ModelAttribute("financialReport") @Valid financialReport financialReport, BindingResult bindingResult,
                                  @RequestParam String username,
                                  @RequestParam String nameOfBank, Model addr)
    {
        financialReport.setUserNa(UserRepository.findUserByUsername(username));
        financialReport.setNameOfBa(CoAccountRepository.findByNameOfBank(nameOfBank));
        FinancialReportRepository.save(financialReport);
        return "redirect:/financialReport";
    }

    @GetMapping("/financialReport/{id}/edit")
    public String financialReportEdit(@PathVariable("id") long id, Model model)
    {
        if(!FinancialReportRepository.existsById(id)){
            return "redirect:/financialReport";
        }
        financialReport res = FinancialReportRepository.findById(id).orElseThrow();
        model.addAttribute("financialReport", res);
        Iterable<user> users = UserRepository.findAll();
        model.addAttribute("users",users);
        Iterable<coAccount> coAccounts = CoAccountRepository.findAll();
        model.addAttribute("coAccounts",coAccounts);
        return "financialReportEdit";
    }
    @PostMapping("/financialReport/{id}/edit")
    public String financialReportUpdate(@PathVariable("id")long id,
                                  @Valid financialReport financialReport, BindingResult bindingResult,
                                  @RequestParam String username,
                                  @RequestParam String nameOfBank)
    {
        financialReport.setUserNa(UserRepository.findUserByUsername(username));
        financialReport.setNameOfBa(CoAccountRepository.findByNameOfBank(nameOfBank));
        FinancialReportRepository.save(financialReport);
        return "redirect:/financialReport";
    }

    @GetMapping("/financialReport/{id}/remove")
    public String financialReportRemove(@PathVariable("id") long id, Model model)
    {
        financialReport FinancialReport = FinancialReportRepository.findById(id).orElseThrow();
        FinancialReportRepository.delete(FinancialReport);
        return "redirect:/financialReport";
    }
}
