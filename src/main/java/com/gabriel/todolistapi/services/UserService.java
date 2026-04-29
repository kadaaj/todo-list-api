package com.gabriel.todolistapi.services;

import com.gabriel.todolistapi.entities.User;
import com.gabriel.todolistapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public User registerUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.generateToken(user);
    }
}