package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.*;
import com.example.upAksenovPrac2.repo.chequeRepository;
import com.example.upAksenovPrac2.repo.ticketListRepository;
import com.example.upAksenovPrac2.repo.ticketRepository;
import com.example.upAksenovPrac2.repo.userRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN','SALES')")
public class ticketController {
    @Autowired
    private ticketRepository TicketRepository;
    @Autowired
    private chequeRepository ChequeRepository;
    @Autowired
    private userRepository UserRepository;
    @Autowired
    private ticketListRepository TicketListRepository;
    @GetMapping("/ticket")
    public String tickets(Model model)
    {
        Iterable<ticket> ticket = TicketRepository.findAll();
        model.addAttribute("ticket", ticket);
        return "ticketMain";
    }

    @GetMapping("/ticketAdd")
    public String ticketAdd(@ModelAttribute("ticket") ticket ticket, Model addr)
    {
        Iterable<ticketList> ticketLists = TicketListRepository.findAll();
        addr.addAttribute("ticketLists",ticketLists);
        Iterable<user> users = UserRepository.findAll();
        addr.addAttribute("users",users);
        Iterable<cheque> cheques = ChequeRepository.findAll();
        addr.addAttribute("cheques",cheques);
        return "ticketAdd";
    }

    @PostMapping("/ticketAdd")
    public String ticketAddAdd(@ModelAttribute("ticket") @Valid ticket ticket, BindingResult bindingResult,
                               @RequestParam String link,@RequestParam Date ticketNu,@RequestParam String userNa, Model addr)
    {
//        if (bindingResult.hasErrors()) {
//            Iterable<ticketList> ticketLists = TicketListRepository.findAll();
//            addr.addAttribute("ticketLists",ticketLists);
//            Iterable<user> users = UserRepository.findAll();
//            addr.addAttribute("users",users);
//            Iterable<cheque> cheques = ChequeRepository.findAll();
//            addr.addAttribute("cheques",cheques);
//            return "ticketAdd";
//        }
        ticket.setTicketLi(TicketListRepository.findByDateOfForm(ticketNu));
        ticket.setUserNa(UserRepository.findUserByUsername(userNa));
        ticket.setCheck(ChequeRepository.findByLink(link));
        TicketRepository.save(ticket);
        return "redirect:/ticket";
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
            return "redirect:/ticket";
        }
        ticket res = TicketRepository.findById(id).orElseThrow();
        model.addAttribute("ticket", res);
        Iterable<ticketList> ticketLists = TicketListRepository.findAll();
        model.addAttribute("ticketLists",ticketLists);
        Iterable<user> users = UserRepository.findAll();
        model.addAttribute("users",users);
        Iterable<cheque> cheques = ChequeRepository.findAll();
        model.addAttribute("cheques",cheques);
        return "ticketEdit";
    }
    @PostMapping("/ticket/{id}/edit")
    public String ticketUpdate(@PathVariable("id")long id,
                               @Valid ticket ticket, BindingResult bindingResult, @RequestParam String link,@RequestParam Date ticketNu,@RequestParam String userNa)
    {
        ticket.setTicketLi(TicketListRepository.findByDateOfForm(ticketNu));
        ticket.setUserNa(UserRepository.findUserByUsername(userNa));
        ticket.setCheck(ChequeRepository.findByLink(link));
        TicketRepository.save(ticket);
        return "redirect:/ticket";
    }
    @GetMapping("/ticket/{id}/remove")
    public String ticketRemove(@PathVariable("id") long id, Model model)
    {
        ticket Ticket = TicketRepository.findById(id).orElseThrow();
        TicketRepository.delete(Ticket);
        return "redirect:/";
    }
}
