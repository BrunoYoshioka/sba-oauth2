package com.bruno.ws.config;

import com.bruno.ws.domain.User;
import com.bruno.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Optional;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        userRepository.deleteAll();

        User joao = new User("João", "Silva", "joao.silva@gmail.com");
        User maria = new User("Maria", "Paula", "maria.paula@gmail.com");

        createUserIfNotFound(joao);
        createUserIfNotFound(maria);
    }

    // método para verificar se o usuário existe
    private User createUserIfNotFound(final User user){
        Optional<User> obj = userRepository.findByEmail(user.getEmail());
        if (obj.isPresent()) {
            return obj.get();
        }
        return userRepository.save(user);
    }
}
