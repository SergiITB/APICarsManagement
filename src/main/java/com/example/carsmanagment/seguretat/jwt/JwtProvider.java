package com.example.carsmanagment.seguretat.jwt;

import com.example.carsmanagment.model.entitats.Usuari;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;
import java.util.stream.Collectors;
@Log
@Component
public class JwtProvider {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    @Value("${jwt.secret:EnUnLugarDeLaManchaDeCuyoNombreNoQuieroAcordarmeNoHaMuchoTiempoQueViviaUnHidalgo}")
    private String jwtSecreto;

    @Value("${jwt.token-expiration:86400}")
    private int jwtDuracionTokenEnSegundos;

    public String generateToken(Authentication authentication) {
        Usuari user = (Usuari) authentication.getPrincipal();
        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDuracionTokenEnSegundos * 1000));
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .claim("fullname", user.getUsername())
                .claim("roles", user.getRol()
                )
                .compact();
    }


    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject()); //aconsegueix l'id d'usuari, que és long
    }


    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecreto.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.info("Token malformado: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.info("El token ha expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Token JWT no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims vacío");
        }
        return false;
    }
}