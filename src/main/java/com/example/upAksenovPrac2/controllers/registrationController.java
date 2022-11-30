package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.role;
import com.example.upAksenovPrac2.models.user;
import com.example.upAksenovPrac2.repo.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class registrationController {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    private String RegView()
    {
        return "registration";
    }

    @PostMapping("/registration")
    private String Reg(user user, Model model)
    {
        user user_from_db = userRepository.findUserByUsername(user.getUsername());
        if(user_from_db != null)
        {
            model.addAttribute("message","Пользователь с таким логином уже существует");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }
}
