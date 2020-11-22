package com.bruno.ws.services;

import com.bruno.ws.domain.Role;
import com.bruno.ws.domain.User;
import com.bruno.ws.repository.RoleRepository;
import com.bruno.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void instantiateDatabase() throws ParseException {

        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
        Role roleUser = createRoleIfNotFound("ROLE_USER");

        User joao = new User("João", "Silva", "joao.silva@gmail.com");
        joao.setRoles(Arrays.asList(roleAdmin));
        joao.setPassword(passwordEncoder.encode("123"));
        joao.setEnabled(true);

        User maria = new User("Maria", "Paula", "maria.paula@gmail.com");
        maria.setRoles(Arrays.asList(roleUser));
        maria.setPassword(passwordEncoder.encode("123"));
        maria.setEnabled(true);



        userRepository.saveAll(Arrays.asList(joao, maria));
    }

    private User createUserIfNotFound(final User user) {
        Optional<User> obj = userRepository.findByEmail(user.getEmail());
        if(obj.isPresent()) {
            return obj.get();
        }
        return userRepository.save(user);
    }

    private Role createRoleIfNotFound(String name){
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()){
            return role.get();
        }
        return roleRepository.save(new Role(name));
    }
}
