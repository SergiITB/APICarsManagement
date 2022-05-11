package com.example.carsmanagment.seguretat.jwt;

import com.example.carsmanagment.model.entitats.UsuariConsultaDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariJwt extends UsuariConsultaDTO {
    private String token;

    @Builder(builderMethodName = "jwtUsuariJwtBuilder")
    public UsuariJwt(String username, String avatar, String rol, String token) {
        super(username, avatar, rol);
        this.token = token;
    }
}