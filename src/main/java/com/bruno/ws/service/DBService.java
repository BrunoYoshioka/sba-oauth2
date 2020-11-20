package com.bruno.ws.service;

import com.bruno.ws.domain.User;
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
        User user1 = new User(null, "João", "Silva", "joao.silva@gmail.com");
        User user2 = new User(null, "Maria", "Paula", "maria.paula@gmail.com");

        userRepository.saveAll(Arrays.asList(user1, user2));
    }
}
