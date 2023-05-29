/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.security;

import com.mottita.portfolio.security.jwt.JwtEntryPoint;
import com.mottita.portfolio.security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Indica al contenedor de spring que esta es una clase de seguridd al momento de arrancar la api
@EnableWebSecurity //Indicamos que se activa la seguridad web en nuestra aplicacion y ademas esta sera una clase que contiene toda la config. de seguridad
public class SecurityConfig {
    
    private JwtEntryPoint jwtEntryPoint;
    
    @Autowired
    public SecurityConfig(JwtEntryPoint jwtEntryPoint) {
        this.jwtEntryPoint = jwtEntryPoint;
    }
    
    //Este bean se encarga de verificar la informacion de los usuarios que se loguean en nuestra api
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    //Este Bean encripta todas nuestras contraseñas
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    //Este Bean incorporara el filtro de seguridad de JWT que creamos en nuestra clase anterior
    @Bean
    JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    
    //Este Bean establece una cadena de filtros de seguridad en nuestra api y 
    //determina los permisos segun los roles de usuarios para el acceso
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .exceptionHandling() // permitimos el manejo de excepciones
                .authenticationEntryPoint(jwtEntryPoint) //Nos establece un punto de entrada personaliado de autenticacion para el manejo de autenticaciones no autorizado
                .and() //concatena otro filtro
                .sessionManagement()// permite la gestion de sesiones
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and() //concatena otro filtro
                .authorizeHttpRequests()//toda peticion http debe ser autorizada
                .requestMatchers("/api/auth/**").permitAll() //se permite el acceso a todo a esa ruta
                .requestMatchers(HttpMethod.POST, "/api/crud/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/crud/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/api/crud/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/crud/**").hasAuthority("ADMIN")
                .anyRequest().authenticated() //cualquier solicitud que no esta arriba debe ser autenticado
                .and() //concatena otro filtro
                .cors()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); //agregamos nuestro filtro primero.
        return http.build(); //retornamos la construccion de este filtro
    }
}
