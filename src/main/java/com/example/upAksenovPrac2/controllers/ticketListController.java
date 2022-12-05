package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.*;
import com.example.upAksenovPrac2.repo.ticketListRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN','SALES')")
public class ticketListController {
    @Autowired
    private ticketListRepository TicketListRepository;
    @GetMapping("/ticketList")
    public String taxReturn(Model model)
    {
        Iterable<ticketList> ticketList = TicketListRepository.findAll();
        model.addAttribute("ticketList", ticketList);
        return "ticketListMain";
    }
    @GetMapping("/ticketListAdd")
    public String ticketListAdd(Model model)
    {
        return "ticketListAdd";
    }

    @PostMapping("/ticketListAdd")
    public String ticketListAddAdd(@RequestParam Date dateOfForm, Model model)
    {
        ticketList TicketList = new ticketList(dateOfForm);
        TicketListRepository.save(TicketList);
        return "redirect:/ticketList";
    }

    @GetMapping("/ticketList/{id}/edit")
    public String ticketListEdit(@PathVariable("id") long id, Model model)
    {
        if(!TicketListRepository.existsById(id)){
            return "redirect:/ticketList";
        }
        ticketList res = TicketListRepository.findById(id).orElseThrow();
        model.addAttribute("ticketList", res);
        return "ticketListEdit";
    }
    @PostMapping("/ticketList/{id}/edit")
    public String ticketListUpdate(@PathVariable("id")long id,
                                  @Valid ticketList ticketList, BindingResult bindingResult)
    {
        TicketListRepository.save(ticketList);
        return "redirect:/ticketList";
    }
    @GetMapping("/ticketList/{id}/remove")
    public String ticketListRemove(@PathVariable("id") long id, Model model)
    {
        ticketList TicketList = TicketListRepository.findById(id).orElseThrow();
        TicketListRepository.delete(TicketList);
        return "redirect:/ticketList";
    }
}
