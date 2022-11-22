package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.flight;
import com.example.upAksenovPrac2.models.ticket;
import com.example.upAksenovPrac2.repo.flightRepository;
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
public class flightController {
    @Autowired
    private flightRepository FlightRepository;
    @Autowired
    private ticketRepository TicketRepository;
    @GetMapping("/")
    public String flights(Model model)
    {
        Iterable<flight> flight = FlightRepository.findAll();
        model.addAttribute("flight", flight);
        Iterable<ticket> ticket = TicketRepository.findAll();
        model.addAttribute("ticket", ticket);
        return "flightMain";
    }

    @GetMapping("/flightAdd")
    public String flightAdd(Model model)
    {
        return "flightAdd";
    }

    @PostMapping("/flightAdd")
    public String flightAddAdd(@RequestParam (defaultValue = "02.02.2001")Date dateOfFlight,
                               @RequestParam (defaultValue = "00:00:00") Time timeOfDeparture,
                               @RequestParam String pointOfDeparture,
                               @RequestParam (defaultValue = "false")boolean soldOut,
                               @RequestParam (defaultValue = "0")int countOfSeats,Model model)
    {
        flight Flight = new flight(dateOfFlight, timeOfDeparture, pointOfDeparture, soldOut, countOfSeats);
        FlightRepository.save(Flight);
        return "redirect:/";
    }

    @GetMapping("/flightFilter")
    public String flightFilter(Model model)
    {
        return "flightFilter";
    }

    @PostMapping("/flightFilter/result")
    public String flightFilterResult(@RequestParam String pointOfDeparture, Model model)
    {
        List<flight> result1 = FlightRepository.findByPointOfDepartureContains(pointOfDeparture);
        List<flight> result2 = FlightRepository.findByPointOfDepartureEquals(pointOfDeparture);
        model.addAttribute("result2", result2);
        model.addAttribute("result", result1);
        return "flightFilter";
    }
}
