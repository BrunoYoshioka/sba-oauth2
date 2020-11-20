package com.bruno.ws.resources;

import com.bruno.ws.domain.User;
import com.bruno.ws.dto.UserDTO;
import com.bruno.ws.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> listDTO = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        return ResponseEntity.ok().body(new UserDTO(userService.create(user)));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {

        User user = userService.fromDTO(userDTO);
        user.setId(id);
        return ResponseEntity.ok().body(new UserDTO(userService.update(user)));
    }

}
