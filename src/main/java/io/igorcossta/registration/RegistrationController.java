package io.igorcossta.registration;

import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String registrationForm(
            @ModelAttribute("userRegistration") UserRegistrationDto request) {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }

        return "registration";
    }

    @PostMapping
    public String registerNewUser(UserRegistrationDto request) {
        return "redirect:/signup?success";
    }
}
