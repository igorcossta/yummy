package io.igorcossta.authentication;

import io.igorcossta.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping
    public String home(@ModelAttribute("userDto") LoginDTO loginDTO,
                       Model model) {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }

        return "login";
    }
}
