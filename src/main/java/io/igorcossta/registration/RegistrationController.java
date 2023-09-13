package io.igorcossta.registration;

import io.igorcossta.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String registrationForm(Model model) {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("registration", new UserRegistrationDTO());

        return "registration";
    }

    @PostMapping
    public String registerNewUser(@Valid @ModelAttribute("registration") UserRegistrationDTO registration,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        return "redirect:/login?success";
    }
}
