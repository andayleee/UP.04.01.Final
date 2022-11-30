package com.example.upAksenovPrac2.controllers;

import com.example.upAksenovPrac2.models.role;
import com.example.upAksenovPrac2.models.user;
import com.example.upAksenovPrac2.repo.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('PILOT')")
public class userController {
    @Autowired
    private com.example.upAksenovPrac2.repo.userRepository userRepository;


    @GetMapping("/a")
    public String userView(Model model)
    {
        model.addAttribute("user_list", userRepository.findAll());
        return "userMain";
    }

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model)
    {
        model.addAttribute("user_object",userRepository.findById(id).orElseThrow());
        return "userDetail";
    }

    @GetMapping("/{id}/update")
    public String updView(@PathVariable Long id, Model model)
    {
        model.addAttribute("user_object",userRepository.findById(id).orElseThrow());
        model.addAttribute("roles", role.values());
        return "userEdit";
    }


    @PostMapping("/{id}/update")
    public String update_user(@RequestParam String username,
                              @RequestParam(name="roles[]", required = false) String[] roles,
                              @PathVariable Long id)
    {
        user user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);

        user.getRoles().clear();
        if(roles != null)
        {
            for(String Role: roles)
            {
                user.getRoles().add(role.valueOf(Role));
            }
        }

        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "redirect:/admin/a";
    }

}
