package net.mybus.util;

import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class JwtUtilTest {
    private JwtUtil jwtUtil;
    private static final String SECRET = Base64.getEncoder().encodeToString("mySuperSecretKeymySuperSecretKey".getBytes());
    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }
    @Test
    public void testGenerateToken() {
        String subject = "thisthatthat";
        String token = jwtUtil.generateToken(subject);
        assertNotNull(token);
        log.info("Generated token : {}", token);
    }
    @Test
    public void testParseToken(){
        String subject = "thisthatthat";
        String token = jwtUtil.generateToken(subject);
        Claims claims = jwtUtil.parseToken(token);
        assertNotNull(claims);
        log.info("Claims : {}", claims);
    }
    @Test
    public void testValidateToken(){
        String subject = "thisthatthat";
        String token = jwtUtil.generateToken(subject);
        Claims claims = jwtUtil.parseToken(token);
        assertTrue(jwtUtil.validateToken(token));
    }
}
