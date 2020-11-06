package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Profile;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProfileService;
import com.geekbrains.geek.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public Profile getProfile(Principal principal) {
        return userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User profile not found")).getProfile();
    }

    @PostMapping
    public Profile update(Principal principal, @RequestParam String firstname,
                                            @RequestParam String lastname,
                                            @RequestParam String phone,
                                            @RequestParam String email,
                                            @RequestParam Integer birthyear,
                                            @RequestParam String gender,
                                            @RequestParam String city,
                                            @RequestParam String password) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User profile not found"));
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            Profile p = getProfile(principal);
            p.setFirstname(firstname);
            p.setLastname(lastname);
            p.setPhone(phone);
            p.setEmail(email);
            p.setBirthyear(birthyear);
            p.setGender(gender);
            p.setCity(city);
            return profileService.update(p);
        }
        throw new ResourceNotFoundException("Wrong password");
    }
}
