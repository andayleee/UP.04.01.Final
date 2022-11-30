package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.cheque;
import com.example.upAksenovPrac2.models.contract;
import com.example.upAksenovPrac2.models.flight;
import com.example.upAksenovPrac2.models.ticket;
import com.example.upAksenovPrac2.repo.chequeRepository;
import com.example.upAksenovPrac2.repo.ticketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class ticketController {
    @Autowired
    private ticketRepository TicketRepository;
    @Autowired
    private chequeRepository ChequeRepository;
//    @GetMapping("/ticket")
//    public String tickets(Model model)
//    {
//        Iterable<ticket> ticket = TicketRepository.findAll();
//        model.addAttribute("ticket", ticket);
//        return "flightMain";
//    }

    @GetMapping("/ticketAdd")
    public String ticketAdd(@ModelAttribute("ticket") ticket ticket, Model addr)
    {
        Iterable<cheque> cheques = ChequeRepository.findAll();
        addr.addAttribute("cheques",cheques);
        return "ticketAdd";
    }

    @PostMapping("/ticketAdd")
    public String ticketAddAdd(@ModelAttribute("ticket") @Valid ticket ticket, BindingResult bindingResult,
                               @RequestParam String employeeFIO, Model addr)
    {
        if (bindingResult.hasErrors()) {
            Iterable<cheque> cheques = ChequeRepository.findAll();
            addr.addAttribute("cheques",cheques);
            return "ticketAdd";
        }
        ticket.setCheck(ChequeRepository.findByEmployeeFIO(employeeFIO));
        TicketRepository.save(ticket);
        return "redirect:/";
    }

    @GetMapping("/ticketFilter")
    public String ticketFilter(Model model)
    {
        return "ticketFilter";
    }

    @PostMapping("/ticketFilter/result")
    public String ticketFilterResult(@RequestParam String fioClient, Model model)
    {
        List<ticket> result1 = TicketRepository.findByFioClientContains(fioClient);
        List<ticket> result2 = TicketRepository.findByFioClientEquals(fioClient);
        model.addAttribute("result2", result2);
        model.addAttribute("result", result1);
        return "ticketFilter";
    }

    @GetMapping("/ticket/{id}")
    public String ticketDetailis(@PathVariable(value = "id") long id, Model model)
    {
        Optional<ticket> ticket = TicketRepository.findById(id);
        ArrayList<ticket> res = new ArrayList<>();
        ticket.ifPresent(res::add);
        model.addAttribute("ticket", res);
        if(!TicketRepository.existsById(id)){
            return "redirect:/ticket";
        }
        return "ticketDetail";
    }

    @GetMapping("/ticket/{id}/edit")
    public String ticketEdit(@PathVariable("id") long id, Model model)
    {
        if(!TicketRepository.existsById(id)){
            return "redirect:/";
        }
        ticket res = TicketRepository.findById(id).orElseThrow();
        model.addAttribute("ticket", res);
        return "ticketEdit";
    }
    @PostMapping("/ticket/{id}/edit")
    public String ticketUpdate(@PathVariable("id")long id,
                               @Valid ticket ticket, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "ticketEdit";
        TicketRepository.save(ticket);
        return "redirect:/";
    }
    @GetMapping("/ticket/{id}/remove")
    public String ticketRemove(@PathVariable("id") long id, Model model)
    {
        ticket Ticket = TicketRepository.findById(id).orElseThrow();
        TicketRepository.delete(Ticket);
        return "redirect:/";
    }
}
