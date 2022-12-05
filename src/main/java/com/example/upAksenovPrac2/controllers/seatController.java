package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.*;
import com.example.upAksenovPrac2.repo.chequeRepository;
import com.example.upAksenovPrac2.repo.seatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','PURCHASES')")
public class seatController {
    @Autowired
    private seatRepository Seatrepository;
    @GetMapping("/seat")
    public String contract(Model model)
    {
        Iterable<seat> seat = Seatrepository.findAll();
        model.addAttribute("seat", seat);
        return "seatMain";
    }
    @GetMapping("/seatAdd")
    public String seatAdd(Model model)
    {
        return "seatAdd";
    }

    @PostMapping("/seatAdd")
    public String chequeAddAdd(@ModelAttribute("seat") @Valid seat seat, BindingResult bindingResult,
            @RequestParam String place, Model model)
    {
        seat Seat = new seat(place);
        Seatrepository.save(Seat);
        return "redirect:/seat";
    }

    @GetMapping("/seat/{id}/edit")
    public String seatEdit(@PathVariable("id") long id, Model model)
    {
        seat res = Seatrepository.findById(id).orElseThrow();
        model.addAttribute("seat", res);
        return "seatEdit";
    }
    @PostMapping("/seat/{id}/edit")
    public String seatUpdate(@PathVariable("id")long id,
                               @Valid seat seat, BindingResult bindingResult)
    {
        Seatrepository.save(seat);
        return "redirect:/seat";
    }
    @GetMapping("/seat/{id}/remove")
    public String seatRemove(@PathVariable("id") long id, Model model)
    {
        seat Seat = Seatrepository.findById(id).orElseThrow();
        Seatrepository.delete(Seat);
        return "redirect:/seat";
    }
}
