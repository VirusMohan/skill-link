package com.app.java.skill_link.src.service;

import com.app.java.skill_link.src.entity.Creds;
import com.app.java.skill_link.src.repository.CredsRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CredsService implements UserDetailsService {


    private final CredsRepo repo;

    private final PasswordEncoder passwordEncoder;


    public CredsService(CredsRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public String updateCredentials(String username, String password){

        var existingCreds = repo.findByUsername(username);

        if (existingCreds == null || existingCreds.getUsername().isEmpty()) {
            // If no record exists, save a new entry
            Creds newCreds = Creds.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .build();
            repo.saveAndFlush(newCreds);
            return "Credentials Saved!";
        } else {
            // If a record exists, update the password
            existingCreds.setPassword(passwordEncoder.encode(password));
            repo.saveAndFlush(existingCreds); // Save updated entity
            return "Credentials Updated!";
        }
    }

    public boolean validateUser(String username, String password) {
        return passwordEncoder.matches(password,repo.findByUsername(username).getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Creds user = repo.findByUsername(username); // Fetch user from the database

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username); // Handle case where user is not found
        }

        return User.builder()
                .username(user.getUsername()) // Set the username from the database
                .password(user.getPassword()) // Set the encoded password from the database
                .roles("USER","ADMIN","ANY") // You can also fetch roles from the database if needed
                .build();
    }
}
