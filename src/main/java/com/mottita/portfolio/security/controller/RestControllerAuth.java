/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.security.controller;

import com.mottita.portfolio.security.dto.DtoAuthResponse;
import com.mottita.portfolio.security.dto.DtoLogin;
import com.mottita.portfolio.security.dto.DtoRegister;
import com.mottita.portfolio.security.entity.Role;
import com.mottita.portfolio.security.entity.User;
import com.mottita.portfolio.security.jwt.JwtTokenProvider;
import com.mottita.portfolio.security.repository.IRoleRepository;
import com.mottita.portfolio.security.repository.IUserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/auth/")
public class RestControllerAuth {
    
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IRoleRepository iRoleRepository;
    private IUserRepository iUserRepository;
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRoleRepository iRoleRepository, IUserRepository iUserRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.iRoleRepository = iRoleRepository;
        this.iUserRepository = iUserRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    //Metodo para agregar un rol
    @PostMapping("role")
    public ResponseEntity<String> role(@RequestBody Role aRole){
        if (this.iRoleRepository.existsByName(aRole.getName())){
            return new ResponseEntity<>("Role already exist", HttpStatus.BAD_REQUEST);
        }
        Role rol = new Role();
        rol.setName(aRole.getName());
        this.iRoleRepository.save(rol);
        return new ResponseEntity<>("Role created", HttpStatus.OK);
    }
    
    //Metodo para poder registrar usuarios con rol user
    @PostMapping("Register")
    public ResponseEntity<String> register(@RequestBody DtoRegister dtoRegister){
        if (this.iUserRepository.existsByUsername(dtoRegister.getUsername())){
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(dtoRegister.getUsername());
        user.setPassword(this.passwordEncoder.encode(dtoRegister.getPassword()));
        Role roles = this.iRoleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        this.iUserRepository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }
    
    //Metodo para poder registrar usuarios admin
    @PostMapping("RegisterAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody DtoRegister dtoRegister){
        if (this.iUserRepository.existsByUsername(dtoRegister.getUsername())){
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(dtoRegister.getUsername());
        user.setPassword(this.passwordEncoder.encode(dtoRegister.getPassword()));
        Role roles = this.iRoleRepository.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(roles));
        this.iUserRepository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }
    
    //Metodo para poder loguear un usurio y obtener un token
    @PostMapping("login")
    public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoLogin dtoLogin){
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = this.jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>(new DtoAuthResponse(token, dtoLogin.getUsername(),
                authentication.getAuthorities()), HttpStatus.OK);
    }
}
