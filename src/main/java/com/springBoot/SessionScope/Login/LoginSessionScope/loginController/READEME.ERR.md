### Erro de Parameter 2 of constructor in com.loginSemSecurityDoSpringBoot.demo.loginController.LoginController required a bean of type 'com.loginSemSecurityDoSpringBoot.demo.model.UserLogin' that could not be found. o que é e como resolver

- O erro "Parameter 2 of constructor in com.loginSemSecurityDoSpringBoot.demo.loginController.LoginController required a bean of type 'com.loginSemSecurityDoSpringBoot.demo.model.UserLogin' that could not be found" indica que o Spring não conseguiu encontrar uma instância do tipo UserLogin para injetar no construtor do LoginController.

Para resolver esse erro, você pode seguir os seguintes passos:

### Verifique se a classe UserLogin está corretamente definida:
- Certifique-se de que a classe UserLogin está presente no seu código e anotada corretamente, se necessário. Verifique se não há erros de compilação ou problemas de importação.

### Verifique as configurações de component scan:
- Verifique se a classe UserLogin está sendo corretamente escaneada pelo Spring. Você pode fazer isso adicionando a anotação @Component ou outra anotação de escaneamento adequada à classe UserLogin, ou garantir que o pacote onde a classe está localizada esteja sendo escaneado no seu arquivo de configuração do Spring (geralmente @SpringBootApplication).

### Verifique as dependências e injeções de dependência:
- Certifique-se de que você possui as dependências necessárias no seu projeto e que a injeção de dependência está sendo feita corretamente. Verifique se você tem um bean do tipo UserLogin disponível no contexto do Spring ou se está fornecendo uma implementação adequada dessa classe.

### Verifique se você está usando a anotação @Autowired corretamente:
- Certifique-se de que a anotação @Autowired está sendo usada no construtor correto e que a dependência está sendo injetada corretamente. Verifique se não há erros de digitação ou outros problemas na anotação.

Essas são algumas das possíveis causas e soluções para o erro mencionado. No entanto, sem mais informações sobre o seu código e configuração específicos, é difícil fornecer uma solução precisa. Recomendo revisar seu código, configuração e mensagens de erro com cuidado para identificar e corrigir qualquer problema relacionado à injeção de dependência e à classe UserLogin.