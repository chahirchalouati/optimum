package com.crcl.authenticationservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/idp")
public class IdpController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String loginFailed() {
        return "redirect:/authenticate?error=invalid username or password";
    }
//    @GetMapping("/auth/login")
//    public String oauth2LoginPage(Model model,
//                                  @CurrentSecurityContext(expression = "authentication") Authentication authentication,
//                                  @Value("${spring.security.oauth2.server.login.captcha.enabled:true}") boolean enableCaptchaLogin,
//                                  @RequestAttribute(name = "org.springframework.security.web.csrf.CsrfToken", required = false) CsrfToken csrfToken) {
//
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            return "redirect:/";
//        }
//        if (csrfToken != null) {
//            model.addAttribute("_csrfToken", csrfToken);
//        }
//        return "login";
//    }

}