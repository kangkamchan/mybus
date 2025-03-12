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
    /**
     * subject 로 jwt 생성
     * @param subject jwt 생성 주체(사용자 아이디)
     * @return jwt 문자열
     */
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

    /**
     * subject 와 userName 으로 토큰 생성
     * @param subject 생성 주체(사용자 아이디)
     * @param userName 사용자 이름
     * @return jwt 문자열
     */
    public String generateToken(String subject, String userName){
        Date NOW = new Date();
        final long EXPIRATION_TIME = 3600_000L;
        Date EXPIRY_DATE = new Date(NOW.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .subject(subject)
                .claim("userName", userName)
                .issuedAt(NOW)
                .expiration(EXPIRY_DATE)
                .signWith(key)
                .compact();
    }

    /**
     * jwt 파싱해서 클레임 정보를 반환함
     * @param token 문자열
     * @return Claims 객체
     */
    public Claims parseToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey)key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * jwt 의 유효성을 검사함
     * @param token jwt 문자열
     * @return 유효하면 true 유효하지않으면 false
     */
    public boolean validateToken(String token, String subject){
        try{
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date()) && claims.getSubject().equals(subject);
        }catch(Exception e){
            return false;
        }
    }
}
