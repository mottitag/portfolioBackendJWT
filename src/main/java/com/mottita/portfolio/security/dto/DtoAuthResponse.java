/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.security.dto;

//Esta clase va a devolver la informacion con el token y el tipo de este

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class DtoAuthResponse {
    
    private String accessToken;
    private String tokenType = "Bearer ";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public DtoAuthResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.username = username;
        this.authorities = authorities;
    }
    
    
}
