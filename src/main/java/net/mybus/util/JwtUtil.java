package net.mybus.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key;
    public JwtUtil(@Value("${jwt.secret}")String secret){
        byte[] decoded = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(decoded);
    }

    public String generateToken(String subject){
        Date NOW = new Date();
        final long EXPIRATION_TIME = 3600_000L;
        Date EXPIRY_DATE = new Date(NOW.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .subject(subject)
                .issuedAt(NOW)
                .expiration(EXPIRY_DATE)
                .signWith(key)
                .compact();
    }

    public Claims parseToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey)key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token){
        try{
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }
}
