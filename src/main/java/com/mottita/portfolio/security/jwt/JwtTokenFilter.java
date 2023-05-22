/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.security.jwt;

import com.mottita.portfolio.security.CustomUsersDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * La funcion de esta clase es validar la informacion del token y si esto es exitoso
 * establecera la autenticacion de un usuario en la solucitud o en el contexto
 * de seguridad de nuestra aplicacion
 */
public class JwtTokenFilter extends OncePerRequestFilter {
    
    @Autowired
    private CustomUsersDetailsService customUserDetailsService;
    @Autowired
    private JwtTokenProvider jwtTokenProvier;
    
    /*
    La funcion de esta clase es validar la informacion del token y si es exitoso,
    establecer la autenticacion de un usuario en la solicitud o en el contexto
    de seguridad de nuestr aplicacion.
    */
    private String getToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")){
            //Si se encuentra el token, se devuelve una subcadena del token, quitando la palabra bearer.
            return bearer.substring(7, bearer.length());
        }
        return null;
    }

    @Override                       //solicitud entrante
    protected void doFilterInternal(HttpServletRequest request,
            //respuesta saliente
            HttpServletResponse response,
            //Mecanismo para invocar el filtro en la siguiente cadena de filtro
            FilterChain filterChain) throws ServletException, IOException {
        //Obtenemos los datos del token
        String token = getToken(request);
        //validamos la informacion del token
        if (StringUtils.hasText(token) && this.jwtTokenProvier.validateToken(token)){
            //Asignamos el nombre de usuario del objeto token
            String username = this.jwtTokenProvier.getUsernameFromJwt(token);
            //Creamos el objeto userDetail que tendra todos los detalles de nuestro usuario
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
            //Caramos la lista con los roles del usuario
            List<String> userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            //Comprobamos que el usuario contiene algunos de los roles
            if (userRoles.contains("USER") || userRoles.contains("ADMIN")){
                //Creamos el siguiente objeto que tendra los detalles de autenticacion del usuario
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, 
                null, userDetails.getAuthorities());
                //Establecemos informacion adicional de la autenticacion, ej. ip, 
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //estableemos el objeto anterior en el contexto de seguridad.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        //permite que la solicitud continue al siguiente filtro
        filterChain.doFilter(request, response);
    }
    
}
