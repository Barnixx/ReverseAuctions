package pl.reverseAuctions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/signIn")
    public String signIn() {

        return "signIn";
    }
}
