package io.igorcossta.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    @ResponseStatus(OK)
    public String home() {
        return "index";
    }
}
