package com.springBoot.SessionScope.Login.LoginSessionScope.authService;

import com.springBoot.SessionScope.Login.LoginSessionScope.model.UserLogin;
import com.springBoot.SessionScope.Login.LoginSessionScope.userRepository.UserRepository;
import com.springBoot.SessionScope.Login.LoginSessionScope.userSession.UserSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class AuthUserService {
    final private UserRepository userRepository;
    final private UserSession userSession;
    public AuthUserService(UserRepository userRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean authenticate(String username, String password) {

        UserLogin userLogin = userRepository.findByUsername(username);
        if (userLogin != null && userLogin.getPassword().equals(password)) {
            userSession.setUserLogin(userLogin);

            return true;
        }
        return false;
    }

    public boolean isAuthenticated() {
        return userSession.getUserLogin() != null;
    }

}