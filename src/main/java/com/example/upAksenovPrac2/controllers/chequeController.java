package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.cheque;
import com.example.upAksenovPrac2.repo.chequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class chequeController {
    @Autowired
    private chequeRepository ChequeRepository;
    @GetMapping("/chequeAdd")
    public String chequeAdd(Model model)
    {
        return "chequeAdd";
    }

    @PostMapping("/chequeAdd")
    public String chequeAddAdd(@RequestParam String employeeFIO, Model model)
    {
        cheque Cheque = new cheque(employeeFIO);
        ChequeRepository.save(Cheque);
        return "redirect:/";
    }
}
