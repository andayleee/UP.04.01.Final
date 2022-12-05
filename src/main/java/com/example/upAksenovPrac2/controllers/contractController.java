package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.cheque;
import com.example.upAksenovPrac2.models.contract;
import com.example.upAksenovPrac2.models.financialReport;
import com.example.upAksenovPrac2.repo.contractRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN','PURCHASES')")
public class contractController {
    @Autowired
    private contractRepository ContractRepository;
    @GetMapping("/contract")
    public String contract(Model model)
    {
        Iterable<contract> contract = ContractRepository.findAll();
        model.addAttribute("contract", contract);
        return "contractMain";
    }
    @GetMapping("/contractAdd")
    public String contractAdd(Model model)
    {
        return "contractAdd";
    }

    @PostMapping("/contractAdd")
    public String contractAddAdd(@RequestParam Date dateOfForm, @RequestParam String carrierCoName, Model model)
    {
        contract Contract = new contract(dateOfForm, carrierCoName);
        ContractRepository.save(Contract);
        return "redirect:/contract";
    }

    @GetMapping("/contract/{id}/edit")
    public String contractEdit(@PathVariable("id") long id, Model model)
    {
        if(!ContractRepository.existsById(id)){
            return "redirect:/contract";
        }
        contract res = ContractRepository.findById(id).orElseThrow();
        model.addAttribute("contract", res);
        return "contractEdit";
    }
    @PostMapping("/contract/{id}/edit")
    public String contractUpdate(@PathVariable("id")long id,
                               @Valid contract contract, BindingResult bindingResult)
    {
        ContractRepository.save(contract);
        return "redirect:/contract";
    }
    @GetMapping("/contract/{id}/remove")
    public String contractRemove(@PathVariable("id") long id, Model model)
    {
        contract Contract = ContractRepository.findById(id).orElseThrow();
        ContractRepository.delete(Contract);
        return "redirect:/contract";
    }
}
