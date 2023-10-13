package io.igorcossta.registration;

import io.igorcossta.token.TokenService;
import io.igorcossta.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/auth/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final RegistrationService registrationService;
    private final TokenService tokenService;

    @GetMapping
    @ResponseStatus(OK)
    public String initRegistrationForm(Model model) {
        if (userService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("registration", new UserRegistrationDTO());
        return "registration";
    }

    @PostMapping
    public ModelAndView processRegistrationForm(@Valid @ModelAttribute("registration") UserRegistrationDTO registration,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration", BAD_REQUEST);
        }
        registrationService.registerNewUser(registration);
        return new ModelAndView("redirect:/auth/login?success");
    }

    @GetMapping("/activate")
    public String processActivationToken(@RequestParam String token) {
        tokenService.activateToken(token);
        return "redirect:/auth/login?accountVerified=true";
    }
}
