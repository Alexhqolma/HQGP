package com.hqguestposting.controller;

import com.hqguestposting.model.User;
import com.hqguestposting.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
@AllArgsConstructor
public class InjectController {
    private final UserService userService;

    @GetMapping
    public String injection() {
        User user = new User();
        user.setEmail("admin@admin.com");
        user.setPassword("adminadmin");
        user.setRole("ADMIN");
        userService.create(user);

        User user2 = new User();
        user2.setEmail("user@user.com");
        user2.setPassword("useruser");
        userService.create(user2);

        return "Inject success!";
    }
}
