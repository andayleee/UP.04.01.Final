package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.flight;
import com.example.upAksenovPrac2.models.seat;
import com.example.upAksenovPrac2.repo.contractRepository;
import com.example.upAksenovPrac2.repo.flightRepository;
import com.example.upAksenovPrac2.repo.seatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class flightSeatController {
    @Autowired
    private flightRepository FlightRepository;
    @Autowired
    private seatRepository SeatRepository;

    @GetMapping("/flight/seat/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','PURCHASES')")
    private String Main(Model model){
        Iterable<flight> flight = FlightRepository.findAll();
        model.addAttribute("flight", flight);
        Iterable<seat> Seat = SeatRepository.findAll();
        model.addAttribute("seat", Seat);
        return "flightSeatAdd";
    }

    @PostMapping("/flight/seat/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','PURCHASES')")
    public String blogPostAdd(@RequestParam Long flight, @RequestParam Long seat, Model model)
    {
        flight Flight = FlightRepository.findById(flight).orElseThrow();
        com.example.upAksenovPrac2.models.seat Seat = SeatRepository.findById(seat).orElseThrow();
        Flight.getSeats().add(Seat);
        FlightRepository.save(Flight);
        return "redirect:/";
    }
}
