package com.crcl.authentication.controller;

import com.crcl.authentication.dto.CreateUserRequest;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/idp")
@AllArgsConstructor
public class IdpController {
    private final UserService userService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String loginFailed() {
        return "redirect:/authenticate?error=invalid username or password";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("formData", new CreateUserRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@ModelAttribute @Valid CreateUserRequest request, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "redirect:/register";
        }
        UserDto save = userService.save(request);
        return "redirect:/login";
    }

    @PostMapping("/register/rest")
    public @ResponseBody ResponseEntity restRegisterPage(@ModelAttribute @Valid CreateUserRequest request) {
        UserDto save = userService.save(request);
        return ResponseEntity.ok(save);
    }
}