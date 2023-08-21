package com.hqguestposting.controller;

import com.hqguestposting.service.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    private final MailSenderService mailSenderService;

    @GetMapping
    public String showMainPage() {
        return "Ok";
    }

    @PostMapping("mail")
    public ModelAndView sendMail(@RequestParam String nameFirst,
                                 @RequestParam String nameLast,
                                 @RequestParam String email,
                                 @RequestParam Long budget,
                                 @RequestParam String website,
                                 @RequestParam String mailMessage) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        mailSenderService.sendMail(nameFirst, nameLast, email, budget, website, mailMessage);
        return modelAndView;
    }
}
