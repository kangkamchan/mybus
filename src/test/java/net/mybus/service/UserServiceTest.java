package net.mybus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.mybus.dto.user.UserLoginRequestDTO;
import net.mybus.dto.user.UserRegisterRequestDTO;
import net.mybus.dto.user.UserResponseDTO;
import net.mybus.dto.user.UserUpdateRequestDTO;
import net.mybus.service.user.UserService;
import net.mybus.util.JwtUtil;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log4j2
@SpringBootTest(properties = "jwt.secret=poOV313OkrSJsJfa/SvuRTZJLYhgSOH/u0AWbZvV0Ac=" )
//@TestPropertySource(properties = "jwt.secret=poOV313OkrSJsJfa/SvuRTZJLYhgSOH/u0AWbZvV0Ac=")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Test
    public void userRegisterTest(){
        UserRegisterRequestDTO dto = new UserRegisterRequestDTO();
        dto.setUserId("testId");
        dto.setUserName("test");
        dto.setPassword("password");
        UserResponseDTO userResponseDTO = userService.register(dto);
        assertNotNull(userResponseDTO);
        log.info(userResponseDTO);
    }
    @Test
    public void userLoginTest(){
        UserLoginRequestDTO dto = new UserLoginRequestDTO();
        dto.setUserId("testId");
        dto.setPassword("password");
        String token = userService.login(dto);
        assertNotNull(token);
        log.info("token : {}",token);
        log.info("parsed token : {}",jwtUtil.parseToken(token));
    }
    @Test
    public void userUpdateTest(){
        UserUpdateRequestDTO dto = new UserUpdateRequestDTO();
        dto.setUserName("updated name");
        dto.setPassword("updated password");
        UserResponseDTO responseDTO = userService.update("testId",dto);
        assertNotNull(responseDTO);
        log.info("updated user : {}",responseDTO);
        UserLoginRequestDTO loginDTO = new UserLoginRequestDTO();
        loginDTO.setUserId(responseDTO.getUserId());
        loginDTO.setPassword("password");
        assertThrows(RuntimeException.class,()->{userService.login(loginDTO);});
        loginDTO.setPassword("updated password");
        String token = userService.login(loginDTO);
        assertNotNull(token);
        log.info("parsed token : {}",jwtUtil.parseToken(token));
    }
    @Test
    public void userGetTest(){
        UserResponseDTO responseDTO = userService.read("testId");
        assertNotNull(responseDTO);
        log.info("user : {}",responseDTO);
    }
    @Test
    public void userDeleteTest(){
        userService.delete("testId");
        UserResponseDTO responseDTO = userService.read("testId");
    }
}
