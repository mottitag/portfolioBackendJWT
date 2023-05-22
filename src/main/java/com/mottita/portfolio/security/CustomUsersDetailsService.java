/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.security;

import com.mottita.portfolio.security.entity.Role;
import com.mottita.portfolio.security.entity.User;
import com.mottita.portfolio.security.repository.IUserRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUsersDetailsService implements UserDetailsService {
    
    private IUserRepository userRepo;
    
    @Autowired
    public CustomUsersDetailsService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    //Metodo para traer una lista de autoridades por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
    
    //Metodo para traer un usuario con todos sus datos por medio de su username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException("User not Found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapToAuthorities(user.getRoles()));
    }
    
}
