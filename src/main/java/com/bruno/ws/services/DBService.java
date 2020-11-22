package com.bruno.ws.services;

import com.bruno.ws.domain.User;
import com.bruno.ws.domain.enums.Role;
import com.bruno.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    public void instantiateDatabase() throws ParseException {
        User user1 = new User("João", "Silva", "joao.silva@gmail.com");
        user1.addRole(Role.ROLE_ADMIN);

        User user2 = new User("Maria", "Paula", "maria.paula@gmail.com");
        user2.addRole(Role.ROLE_USER);

        userRepository.saveAll(Arrays.asList(user1, user2));
    }
}
