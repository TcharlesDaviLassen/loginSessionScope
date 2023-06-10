package com.springBoot.SessionScope.Login.LoginSessionScope.loginController;

import com.springBoot.SessionScope.Login.LoginSessionScope.userSession.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UserSession userSession;

    //    @Autowired
    //    private AuthUserService authService;

    //    @GetMapping("/home")
    //    public String profile() {
    //        if (authService.isAuthenticated()) {
    //            return "home";
    //        } else {
    //            return "redirect:/login";
    //        }
    //    }

    @GetMapping("/home")
    public String home(Model model) {
        if (userSession.isLoggedIn()) {
            String username = userSession.getUserLogin().getUsername();
            var user = username.toUpperCase();

            model.addAttribute("username", user);

            return "home";
        } else {
            return "redirect:/login";
        }
    }
}