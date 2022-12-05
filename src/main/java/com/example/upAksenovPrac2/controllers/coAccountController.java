package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.coAccount;
import com.example.upAksenovPrac2.models.contract;
import com.example.upAksenovPrac2.models.seat;
import com.example.upAksenovPrac2.repo.coAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Date;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','BUHGALTER')")
public class coAccountController {
    @Autowired
    private coAccountRepository CoAccountRepository;
    @GetMapping("/coAccount")
    public String coAccount(Model model)
    {
        Iterable<coAccount> coAccount = CoAccountRepository.findAll();
        model.addAttribute("coAccount", coAccount);
        return "coAccountMain";
    }
    @GetMapping("/coAccountAdd")
    public String coAccountAdd(Model model)
    {
        return "coAccountAdd";
    }

    @PostMapping("/coAccountAdd")
    public String chequeAddAdd(@RequestParam String nameOfBank, @RequestParam String account, Model model)
    {
        coAccount CoAccount = new coAccount(nameOfBank, account);
        CoAccountRepository.save(CoAccount);
        return "redirect:/coAccount";
    }

    @GetMapping("/coAccount/{id}/edit")
    public String coAccountEdit(@PathVariable("id") long id, Model model)
    {
        if(!CoAccountRepository.existsById(id)){
            return "redirect:/coAccount";
        }
        coAccount res = CoAccountRepository.findById(id).orElseThrow();
        model.addAttribute("coAccount", res);
        return "coAccountEdit";
    }
    @PostMapping("/coAccount/{id}/edit")
    public String coAccountUpdate(@PathVariable("id")long id,
                             @Valid coAccount coAccount, BindingResult bindingResult)
    {
        CoAccountRepository.save(coAccount);
        return "redirect:/coAccount";
    }
    @GetMapping("/coAccount/{id}/remove")
    public String coAccountRemove(@PathVariable("id") long id, Model model)
    {
        coAccount CoAccount = CoAccountRepository.findById(id).orElseThrow();
        CoAccountRepository.delete(CoAccount);
        return "redirect:/coAccount";
    }
}
