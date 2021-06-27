package com.example.demo.authService.jwt;

import com.example.demo.authService.exception.BadRequestException;
import com.example.demo.authService.model.JwtUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log
public class JwtProvider {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(JwtUser jwtUser) {
        String jsonSubject = null;
        try {
            jsonSubject = objectMapper.writeValueAsString(jwtUser);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Exception when generate jwtToken");
        }
        return Jwts.builder()
                .setSubject(jsonSubject)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

    public JwtUser getUserFromToken(String jwtToken) throws JsonProcessingException {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
        String subject = claims.getSubject();
        return objectMapper.readValue(subject, JwtUser.class);
    }
}
