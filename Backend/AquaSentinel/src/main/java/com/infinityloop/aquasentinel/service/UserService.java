package com.infinityloop.aquasentinel.service;

import com.infinityloop.aquasentinel.entities.Authority;
import com.infinityloop.aquasentinel.entities.User;
import com.infinityloop.aquasentinel.enums.PreferredLanguage;
import com.infinityloop.aquasentinel.enums.UserStatus;
import com.infinityloop.aquasentinel.repositories.AuthorityRepository;
import com.infinityloop.aquasentinel.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authority prototypeAuthority = authorityRepository.findByAuthorityName("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalArgumentException("No ROLE_ADMIN authority found"));

        if (user.getAuthorities() == null) {
            user.setAuthorities(new java.util.HashSet<>());
        }
        user.getAuthorities().add(prototypeAuthority);
        user.setVerificationStatus(UserStatus.VERIFIED);
        user.setPreferredLanguage(PreferredLanguage.ENGLISH);
        return userRepository.save(user);
    }
}
