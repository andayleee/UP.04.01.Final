package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.ticket;
import com.example.upAksenovPrac2.repo.ticketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
public class ticketController {
    @Autowired
    private ticketRepository TicketRepository;
//    @GetMapping("/ticket")
//    public String tickets(Model model)
//    {
//        Iterable<ticket> ticket = TicketRepository.findAll();
//        model.addAttribute("ticket", ticket);
//        return "flightMain";
//    }

    @GetMapping("/ticketAdd")
    public String ticketAdd(Model model)
    {
        return "ticketAdd";
    }

    @PostMapping("/ticketAdd")
    public String ticketAddAdd(@RequestParam String fioClient,
                               @RequestParam (defaultValue = "02.02.2001") Date dateOfDelivery,
                               @RequestParam (defaultValue = "0")double coast,
                               @RequestParam (defaultValue = "0")int countClientsSeats,
                               @RequestParam (defaultValue = "false")boolean isPaid,Model model)
    {
        ticket Ticket = new ticket(fioClient, dateOfDelivery, coast, countClientsSeats, isPaid);
        TicketRepository.save(Ticket);
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
}
