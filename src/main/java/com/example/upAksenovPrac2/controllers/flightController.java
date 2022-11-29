package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.contract;
import com.example.upAksenovPrac2.models.flight;
import com.example.upAksenovPrac2.models.seat;
import com.example.upAksenovPrac2.models.ticket;
import com.example.upAksenovPrac2.repo.contractRepository;
import com.example.upAksenovPrac2.repo.flightRepository;
import com.example.upAksenovPrac2.repo.seatRepository;
import com.example.upAksenovPrac2.repo.ticketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class flightController {
    @Autowired
    private flightRepository FlightRepository;
    @Autowired
    private ticketRepository TicketRepository;
    @Autowired
    private contractRepository ContractRepository;
    @Autowired
    private seatRepository SeatRepository;
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
    public String flightAdd(@ModelAttribute("flight") flight flight, Model addr)
    {
        Iterable<contract> contracts = ContractRepository.findAll();
        addr.addAttribute("contracts",contracts);
        return "flightAdd";
    }

    @PostMapping("/flightAdd")
    public String flightAddAdd(@ModelAttribute("flight") @Valid flight flight, BindingResult bindingResult,
                               @RequestParam String carrierCoName, Model addr)
    {
        if (bindingResult.hasErrors()) {
            Iterable<contract> contracts = ContractRepository.findAll();
            addr.addAttribute("contracts",contracts);
            return "flightAdd";
        }
        flight.setCarrierCo(ContractRepository.findByCarrierCoName(carrierCoName));
        FlightRepository.save(flight);
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

    @GetMapping("/flight/{id}")
    public String flightDetailis(@PathVariable(value = "id") long id, Model model)
    {
        Optional<flight> flight = FlightRepository.findById(id);
        ArrayList<flight> res = new ArrayList<>();
        flight.ifPresent(res::add);
        model.addAttribute("flight", res);
        if(!FlightRepository.existsById(id)){
            return "redirect:/flight";
        }
        return "flightDetail";
    }

    @GetMapping("/flight/{id}/edit")
    public String flightEdit(@PathVariable("id") long id, Model model)
    {
        if(!FlightRepository.existsById(id)){
            return "redirect:/";
        }
        flight res = FlightRepository.findById(id).orElseThrow();
        model.addAttribute("flight", res);
        return "flightEdit";
    }
    @PostMapping("/flight/{id}/edit")
    public String flightUpdate(@PathVariable("id")long id,
                               @Valid flight flight, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "flightEdit";
        FlightRepository.save(flight);
        return "redirect:/";
    }
    @GetMapping("/flight/{id}/remove")
    public String flightRemove(@PathVariable("id") long id, Model model)
    {
        flight Flight = FlightRepository.findById(id).orElseThrow();
        FlightRepository.delete(Flight);
        return "redirect:/";
    }
//    @PostMapping("/flight/{id}/remove")
//    public String flightDelete(@PathVariable("id") long id, Model model)
//    {
//        flight Flight = FlightRepository.findById(id).orElseThrow();
//        FlightRepository.delete(Flight);
//        return "redirect:/";
//    }

    @GetMapping("/flight/seat/add")
    private String Main(Model model){
        Iterable<flight> Flight = FlightRepository.findAll();
        model.addAttribute("flight", Flight);
        Iterable<seat> Seat = SeatRepository.findAll();
        model.addAttribute("seat", Seat);
        return "flightSeatAdd";
    }

    @PostMapping("/flight/seat/add")
    public String blogPostAdd(@RequestParam Long flight, @RequestParam Long seat, Model model)
    {
        flight Flight = FlightRepository.findById(flight).orElseThrow();
        seat Seat = SeatRepository.findById(seat).orElseThrow();
        Flight.getSeats().add(Seat);
        FlightRepository.save(Flight);
        return "redirect:/";
    }
}
