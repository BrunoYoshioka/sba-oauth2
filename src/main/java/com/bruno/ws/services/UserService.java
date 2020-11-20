package com.bruno.ws.services;

import com.bruno.ws.domain.User;
import com.bruno.ws.dto.UserDTO;
import com.bruno.ws.repository.UserRepository;
import com.bruno.ws.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public User fromDTO (UserDTO userDTO) {
        return new User(userDTO);
    }
}
