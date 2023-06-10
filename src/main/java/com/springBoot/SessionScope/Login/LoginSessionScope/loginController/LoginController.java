package com.springBoot.SessionScope.Login.LoginSessionScope.loginController;

import com.springBoot.SessionScope.Login.LoginSessionScope.authService.AuthUserService;
import com.springBoot.SessionScope.Login.LoginSessionScope.userSession.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    final private AuthUserService authService;
    final private UserSession userSession;
    public LoginController(AuthUserService authService, UserSession userSession) {
        this.authService = authService;
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (authService.authenticate(username, password)) {
            userSession.getUserLogin().setUsername(username);
            userSession.setLoggedIn(true);

            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        userSession.setLoggedIn(false);
        userSession.getUserLogin().setUsername(null);
        return "redirect:/login";
    }
}

