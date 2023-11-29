package com.cadastro_cliente.cadastro_cliente.services;

import java.nio.charset.StandardCharsets;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastro_cliente.cadastro_cliente.entities.Users;
import com.cadastro_cliente.cadastro_cliente.repositories.UsersRepository;

@Service
public class UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersRepository userRepository;


    public Users registerUser(Users user) {
        logger.info("Registering user: {}", user);

        Users existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            logger.warn("User with email {} already exists", user.getEmail());
            throw new RuntimeException("E-mail already registered");
        }

        byte[] salt = generateSalt();

        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id);
        builder.withVersion(Argon2Parameters.ARGON2_VERSION_13);
        builder.withSalt(salt);
        builder.withMemoryAsKB(65536);
        builder.withIterations(10); 

        Argon2Parameters parameters = builder.build();
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(parameters);

        String combinedPassword = user.getPassword() + new String(salt, StandardCharsets.UTF_8);

        byte[] hash = new byte[64]; 
        generator.generateBytes(combinedPassword.getBytes(StandardCharsets.UTF_8), hash, 0, hash.length);

        StringBuilder hexHash = new StringBuilder();
        for (byte b : hash) {
            hexHash.append(String.format("%02x", b));
        }

        user.setPassword(hexHash.toString());

        return userRepository.save(user);
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[16];
        return salt;
    }

    public Users getUserByEmail(String email) {
        logger.info("Fetching user by email: {}", email);
        Users user = userRepository.findByEmail(email);
        
        if (user == null) {
            logger.warn("User not found for email: {}", email);
        } else {
            logger.info("User found: {}", user);
        }

        return user;
    }
}
