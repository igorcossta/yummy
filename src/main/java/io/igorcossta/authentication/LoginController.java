package io.igorcossta.authentication;

import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping
    public String initLoginForm(Model model) {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("authentication", new LoginDTO());
        return "login";
    }
}
