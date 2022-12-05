package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.contract;
import com.example.upAksenovPrac2.models.requisites;
import com.example.upAksenovPrac2.repo.requisitesRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN','BUHGALTER')")
public class requisitesController {
    @Autowired
    private requisitesRepository RequisitesRepository;
    @GetMapping("/requisites")
    public String requisites(Model model)
    {
        Iterable<requisites> requisites = RequisitesRepository.findAll();
        model.addAttribute("requisites", requisites);
        return "requisitesMain";
    }
    @GetMapping("/requisitesAdd")
    public String requisitesAdd(Model model)
    {
        return "requisitesAdd";
    }

    @PostMapping("/requisitesAdd")
    public String requisitesAddAdd(@RequestParam String INN,
                                   @RequestParam String KPP, Model model)
    {
        requisites Requisites = new requisites(INN, KPP);
        RequisitesRepository.save(Requisites);
        return "redirect:/requisites";
    }

    @GetMapping("/requisites/{id}/edit")
    public String requisitesEdit(@PathVariable("id") long id, Model model)
    {
        if(!RequisitesRepository.existsById(id)){
            return "redirect:/requisites";
        }
        requisites res = RequisitesRepository.findById(id).orElseThrow();
        model.addAttribute("requisites", res);
        return "requisitesEdit";
    }
    @PostMapping("/requisites/{id}/edit")
    public String requisitesUpdate(@PathVariable("id")long id,
                                 @Valid requisites requisites, BindingResult bindingResult)
    {
        RequisitesRepository.save(requisites);
        return "redirect:/requisites";
    }
    @GetMapping("/requisites/{id}/remove")
    public String requisitesRemove(@PathVariable("id") long id, Model model)
    {
        requisites Requisites = RequisitesRepository.findById(id).orElseThrow();
        RequisitesRepository.delete(Requisites);
        return "redirect:/requisites";
    }
}
