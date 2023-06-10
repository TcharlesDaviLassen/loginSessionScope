package com.springBoot.SessionScope.Login.LoginSessionScope.userRepository;

import com.springBoot.SessionScope.Login.LoginSessionScope.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserLogin, Long> {

    UserLogin findByUsernameAndPassword(String username, String password);
    
    UserLogin findByUsername(String username);

}