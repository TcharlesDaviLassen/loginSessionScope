### Exemplo completo de um sistema de login e logout usando Spring Boot, Thymeleaf e banco de dados para armazenar informações do usuário:

#### Crie a `entidade` User que representa um usuário no banco de dados:
```java

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String username;
private String password;

    // getters and setters
}
```

#### Crie uma interface `UserRepository` que estende a interface `JpaRepository` para realizar operações no banco de dados:

```java

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername(String username);
}
```

#### Crie um serviço `UserService` para lidar com as operações relacionadas ao usuário:

```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
```

#### Crie um controlador `LoginController` para lidar com as operações de login e logout:

```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
@Autowired
private UserSession userSession;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (userService.authenticate(username, password)) {
            userSession.setLoggedIn(true);
            userSession.setUsername(username);
            return "redirect:/home";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        userSession.setLoggedIn(false);
        userSession.setUsername(null);
        return "redirect:/login";
    }
}
```

#### Crie um controlador `HomeController` para a página inicial após o login:

```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
@Autowired
private UserSession userSession;

    @GetMapping("/home")
    public String home(Model model) {
        if (userSession.isLoggedIn()) {
            String username = userSession.getUsername();
            model.addAttribute("username", username);
            return "home";
        } else {
            return "redirect:/login";
        }
    }
}
```

#### Crie um arquivo `login.html` em src/main/resources/templates para a página de login:

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="Username" required><br>
        <input type="password" name="password" placeholder="Password" required><br>
        <button type="submit">Login</button>
    </form>
    <p th:if="${param.error}">Invalid username or password</p>
</body>
</html>
```

#### Crie um arquivo `home.html` em src/main/resources/templates para a página inicial após o login:

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
    <h2>Welcome, <span th:text="${username}"></span>!</h2>
    <a href="/logout">Logout</a>
</body>
</html>
```

#### Crie uma classe `UserSession` para representar a sessão do usuário:

```java
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
private boolean loggedIn;
private String username;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
```

Certifique-se de que todas as dependências necessárias, como o Spring Boot Starter Web, o Spring Boot Starter Data JPA e o Spring Security, estejam presentes no arquivo pom.xml.

Esse exemplo cria um sistema de login e logout básico usando Spring Boot, Thymeleaf e banco de dados para armazenar informações do usuário. A anotação @SessionScope é usada na classe UserSession para gerenciar a sessão do usuário, garantindo que cada usuário tenha sua própria instância dessa classe durante a sessão.