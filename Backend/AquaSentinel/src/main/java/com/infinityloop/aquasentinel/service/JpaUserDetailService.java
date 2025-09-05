package com.infinityloop.aquasentinel.service;

import com.infinityloop.aquasentinel.repositories.UserRepository;
import com.infinityloop.aquasentinel.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JpaUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email){
        var u = userRepository.findByEmail(email);
        return u.map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("User with email "+email+" not found"));
    }
}
