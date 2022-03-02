package com.example.springsecurity.service;

import com.example.springsecurity.model.CustomUserDetail;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        System.out.println(user.getUsername() + " " + user.getPassword());
        if(user == null)
        {
            throw new UsernameNotFoundException("No user found");
        }
        return new CustomUserDetail(user);
    }
}
