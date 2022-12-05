package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.cheque;
import com.example.upAksenovPrac2.models.contract;
import com.example.upAksenovPrac2.models.flight;
import com.example.upAksenovPrac2.models.ticket;
import com.example.upAksenovPrac2.repo.chequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','SALES')")
public class chequeController {
    @Autowired
    private chequeRepository ChequeRepository;
    @GetMapping("/cheque")
    public String cheque(Model model)
    {
        Iterable<cheque> cheque = ChequeRepository.findAll();
        model.addAttribute("cheque", cheque);
        return "chequeMain";
    }
    @GetMapping("/chequeAdd")
    public String chequeAdd(Model model)
    {
        return "chequeAdd";
    }

    @PostMapping("/chequeAdd")
    public String chequeAddAdd(@ModelAttribute("cheque") @Valid cheque cheque, BindingResult bindingResult,
                               Model addr)
    {
        ChequeRepository.save(cheque);
        return "redirect:/cheque";
    }

    @GetMapping("/cheque/{id}/edit")
    public String chequeEdit(@PathVariable("id") long id, Model model)
    {
        if(!ChequeRepository.existsById(id)){
            return "redirect:/cheque";
        }
        cheque res = ChequeRepository.findById(id).orElseThrow();
        model.addAttribute("cheque", res);
        return "chequeEdit";
    }
    @PostMapping("/cheque/{id}/edit")
    public String chequeUpdate(@PathVariable("id")long id,
                               @Valid cheque cheque, BindingResult bindingResult)
    {
        ChequeRepository.save(cheque);
        return "redirect:/cheque";
    }
    @GetMapping("/cheque/{id}/remove")
    public String chequeRemove(@PathVariable("id") long id, Model model)
    {
        cheque Cheque = ChequeRepository.findById(id).orElseThrow();
        ChequeRepository.delete(Cheque);
        return "redirect:/cheque";
    }
}
