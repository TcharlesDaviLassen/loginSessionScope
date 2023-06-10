package com.springBoot.SessionScope.Login.LoginSessionScope.userSession;

import com.springBoot.SessionScope.Login.LoginSessionScope.model.UserLogin;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {

    private boolean loggedIn;

    //    private String username;

    private UserLogin userLogin;

    public UserSession() {
    }

    public UserSession(boolean loggedIn, UserLogin userLogin) {
        this.loggedIn = loggedIn;
        this.userLogin = userLogin;
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}