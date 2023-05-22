/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.security.dto;

//Esta clase va a devolver la informacion con el token y el tipo de este

import lombok.Data;

@Data
public class DtoAuthResponse {
    
    private String accessToken;
    private String tokenType = "Bearer ";

    public DtoAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    
    
}
