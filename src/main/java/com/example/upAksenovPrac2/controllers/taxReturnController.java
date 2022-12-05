package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.*;
import com.example.upAksenovPrac2.repo.coAccountRepository;
import com.example.upAksenovPrac2.repo.requisitesRepository;
import com.example.upAksenovPrac2.repo.taxReturnRepository;
import com.example.upAksenovPrac2.repo.userRepository;
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
public class taxReturnController {
    @Autowired
    private taxReturnRepository TaxReturnRepository;
    @Autowired
    private userRepository UserRepository;
    @Autowired
    private coAccountRepository CoAccountRepository;
    @Autowired
    private requisitesRepository RequisitesRepository;

    @GetMapping("/taxReturn")
    public String taxReturn(Model model)
    {
        Iterable<taxReturn> taxReturn = TaxReturnRepository.findAll();
        model.addAttribute("taxReturn", taxReturn);
        return "taxReturnMain";
    }
    @GetMapping("/taxReturnAdd")
    public String taxReturnAdd(@ModelAttribute("taxReturn") taxReturn taxReturn, Model addr)
    {
        Iterable<user> users = UserRepository.findAll();
        addr.addAttribute("users",users);
        Iterable<coAccount> coAccounts = CoAccountRepository.findAll();
        addr.addAttribute("coAccounts",coAccounts);
        Iterable<requisites> requisitess = RequisitesRepository.findAll();
        addr.addAttribute("requisitess",requisitess);
        return "taxReturnAdd";
    }

    @PostMapping("/taxReturnAdd")
    public String taxReturnAddAdd(@ModelAttribute("taxReturn") @Valid taxReturn taxReturn, BindingResult bindingResult,
                                  @RequestParam String username,
                                  @RequestParam String nameOfBank,
                                  @RequestParam String INN,Model addr)
    {
        if (bindingResult.hasErrors()) {
            Iterable<user> users = UserRepository.findAll();
            addr.addAttribute("users",users);
            Iterable<coAccount> coAccounts = CoAccountRepository.findAll();
            addr.addAttribute("coAccounts",coAccounts);
            Iterable<requisites> requisitess = RequisitesRepository.findAll();
            addr.addAttribute("requisitess",requisitess);
            return "taxReturnAdd";
        }
        taxReturn.setUserNa(UserRepository.findUserByUsername(username));
        taxReturn.setNameOfBa(CoAccountRepository.findByNameOfBank(nameOfBank));
        taxReturn.setReq(RequisitesRepository.findByINN(INN));
        TaxReturnRepository.save(taxReturn);
        return "redirect:/taxReturn";
    }

    @GetMapping("/taxReturn/{id}/edit")
    public String taxReturnEdit(@PathVariable("id") long id, Model model)
    {
        if(!TaxReturnRepository.existsById(id)){
            return "redirect:/taxReturn";
        }
        taxReturn res = TaxReturnRepository.findById(id).orElseThrow();
        model.addAttribute("taxReturn", res);
        Iterable<user> users = UserRepository.findAll();
        model.addAttribute("users",users);
        Iterable<coAccount> coAccounts = CoAccountRepository.findAll();
        model.addAttribute("coAccounts",coAccounts);
        Iterable<requisites> requisitess = RequisitesRepository.findAll();
        model.addAttribute("requisitess",requisitess);
        return "taxReturnEdit";
    }
    @PostMapping("/taxReturn/{id}/edit")
    public String taxReturnUpdate(@PathVariable("id")long id,
                                  @Valid taxReturn taxReturn, BindingResult bindingResult,
                                  @RequestParam String username,
                                  @RequestParam String nameOfBank,
                                  @RequestParam String INN)
    {
        if (bindingResult.hasErrors())
            return "taxReturnEdit";
        taxReturn.setUserNa(UserRepository.findUserByUsername(username));
        taxReturn.setNameOfBa(CoAccountRepository.findByNameOfBank(nameOfBank));
        taxReturn.setReq(RequisitesRepository.findByINN(INN));
        TaxReturnRepository.save(taxReturn);
        return "redirect:/taxReturn";
    }
    @GetMapping("/taxReturn/{id}/remove")
    public String taxReturnRemove(@PathVariable("id") long id, Model model)
    {
        taxReturn TaxReturn = TaxReturnRepository.findById(id).orElseThrow();
        TaxReturnRepository.delete(TaxReturn);
        return "redirect:/taxReturn";
    }
}
