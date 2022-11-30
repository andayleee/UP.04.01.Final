package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.cheque;
import com.example.upAksenovPrac2.models.seat;
import com.example.upAksenovPrac2.repo.chequeRepository;
import com.example.upAksenovPrac2.repo.seatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class seatController {
    @Autowired
    private seatRepository Seatrepository;
    @GetMapping("/seatAdd")
    public String seatAdd(Model model)
    {
        return "seatAdd";
    }

    @PostMapping("/seatAdd")
    public String seatAddAdd(@RequestParam String place, Model model)
    {
        seat Seat = new seat(place);
        Seatrepository.save(Seat);
        return "redirect:/";
    }
}
