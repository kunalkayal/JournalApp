package me.kunalkayal.journalApp.utills;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtill {
    private static final String SECRET_KEY = "HcgOSNtP7wQ8EcRgJg0aUkg7Q5oZJu0EEAB3SeNhVw4=";
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Token validity: e.g., 10 hours
    private static final long TOKEN_VALIDITY = 10 * 60 * 60 * 1000;


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public  String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims) // Add custom claims
                .subject(subject) // Add subject (e.g., username)
                .header().empty().add("type","Jwt")
                .and()
                .issuedAt(new Date(System.currentTimeMillis())) // Current time as issue date
                .expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) // Expiration time
                .signWith(key, SignatureAlgorithm.HS256) // Sign the token with the secret key
                .compact(); // Build the token
    }

    public String extractUsername(String jwt) {
        Claims claims = extractAllClaims(jwt);
        return claims.getSubject();

    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }


}

