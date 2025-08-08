package com.example.orders.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Serviço simples de usuário para testes.
 * Em produção você buscaria usuários do banco (UserRepository).
 *
 * - Retorna um usuário "admin" com senha codificada pelo PasswordEncoder.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            // senha = "password" codificada com BCrypt
            return User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .authorities(Collections.emptyList())
                    .build();
        }
        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}
