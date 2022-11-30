package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.contract;
import com.example.upAksenovPrac2.repo.contractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class contractController {
    @Autowired
    private contractRepository ContractRepository;
    @GetMapping("/contractAdd")
    public String contractAdd(Model model)
    {
        return "contractAdd";
    }

    @PostMapping("/contractAdd")
    public String contractAddAdd(@RequestParam String carrierCoName, Model model)
    {
        contract Contract = new contract(carrierCoName);
        ContractRepository.save(Contract);
        return "redirect:/";
    }
}
