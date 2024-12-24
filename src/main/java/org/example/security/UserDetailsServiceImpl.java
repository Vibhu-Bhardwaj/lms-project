package org.example.security;

import org.example.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.example.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserName(username);

        if(user != null) {
            Collection<? extends GrantedAuthority> grantedAuthorities = user.getUserRoles().stream().map(userRoles -> {
                return new SimpleGrantedAuthority(userRoles.getRoles().getRoleName());
            }).collect(Collectors.toList());

            return new CustomUser(username, user.getPassword(), grantedAuthorities);

        }else
            throw new UsernameNotFoundException("UserName not found");

    }
}
                    