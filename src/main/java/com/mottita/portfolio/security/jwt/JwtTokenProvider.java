/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;
    
    //Metodo para crear un token por medio de la autenticacion
    public String generateToken(Authentication auth){
        String username = auth.getName();
        Date actualTime = new Date();
        Date expirationTime = new Date(actualTime.getTime() + expiration);
        
        //Linea para generra el token
        String token = Jwts.builder() //Construimos el token
                .setSubject(username) //Establecemos nombre de usuario que inicia sesion
                .setIssuedAt(new Date())  //Establecemos la fecha de emision del token
                .setExpiration(expirationTime) //Establecemos la fecha de expiracion del token
                .signWith(SignatureAlgorithm.HS512, secret) /*Utilizamos este metodo para la firma
                del token y de esta manera evitr la manipulacion o modificacion de este*/
                .compact(); //Este metodo finaliza la construccion del token y lo convierte en cadena compacta
        return token;
    }
    
    //Metodo para extraere un username a partir de un token
    public String getUsernameFromJwt (String token){
        Claims claims = Jwts.parser() //Este metodo se utiliza para analizar el token
                .setSigningKey(secret) //Estblece la clave de la firma para verificar la misa
                .parseClaimsJws(token) //Se verifica la firma del token
                .getBody(); /*Obtenemos el Claims ya verificado del token el cual tendra la informacion
        de nombre de usuario, fecha de expiracion y firma del token*/
        return claims.getSubject(); //Devolvemos el nombre de usuario
    }
    
    //Metodo para validar el token
    public boolean validateToken (String token){
        try{
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        }catch(MalformedJwtException e){
            throw new AuthenticationCredentialsNotFoundException("Malformed token");
        }catch(UnsupportedJwtException e){
            throw new AuthenticationCredentialsNotFoundException("Unsupported Token");
        }catch(ExpiredJwtException e){
            throw new AuthenticationCredentialsNotFoundException("Expired Token");
        }catch(IllegalArgumentException e){
            throw new AuthenticationCredentialsNotFoundException("Illegal Token");
        }catch(SignatureException e){
            throw new AuthenticationCredentialsNotFoundException("Unvalid signature");
        }       
    }
}
