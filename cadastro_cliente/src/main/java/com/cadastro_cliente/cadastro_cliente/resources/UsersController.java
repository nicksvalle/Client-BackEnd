package com.cadastro_cliente.cadastro_cliente.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadastro_cliente.cadastro_cliente.entities.Users;
import com.cadastro_cliente.cadastro_cliente.services.UsersService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("usuario")
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        try {
            Users registeredUser = usersService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<String> getUsuario() {
        return ResponseEntity.ok("Método GET para /usuario");
    }

    @GetMapping("/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
        logger.info("Email received by backend: {}", email);
        Users user = usersService.getUserByEmail(email);

        if (user != null) {
            logger.info("User found: {}", user);
            return ResponseEntity.ok(user);
        } else {
            logger.warn("User not found for email: {}", email);
            return ResponseEntity.notFound().build();
        }
    }

    
}