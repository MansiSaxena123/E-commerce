package Shoppera.utils;

import Shoppera.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey getSecret(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecret())
                .compact();

    }

    public String getUserNameFromToken(String token) {
         Claims claims =  Jwts.parser()
                .verifyWith(getSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload();
         return claims.getSubject();

    }
}
