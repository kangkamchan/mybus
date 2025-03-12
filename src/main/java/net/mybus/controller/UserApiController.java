package net.mybus.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.mybus.domain.User;
import net.mybus.dto.user.UserRegisterRequestDTO;
import net.mybus.dto.user.UserResponseDTO;
import net.mybus.dto.user.UserUpdateRequestDTO;
import net.mybus.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class UserApiController {
    private final UserService userService;
    @GetMapping("/{user-id}")
    public ResponseEntity<?> getUser(@PathVariable("user-id") String userId) {
        try{
            UserResponseDTO userResponseDTO = userService.read(userId);
            return ResponseEntity.ok(userResponseDTO);
        }catch(RuntimeException e){
            log.error(e);
            return ResponseEntity.badRequest().body("해당하는 정보가 없습니다.");
        }catch(Exception e){
            log.error(e);
            return ResponseEntity.internalServerError().body("일시적 오류가 발생했습니다.");
        }
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        try{
            UserResponseDTO userResponseDTO = userService.register(userRegisterRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        }catch(Exception e){
            log.error(e);
            return ResponseEntity.internalServerError().body("일시적 오류가 발생했습니다.");
        }
    }
    @PutMapping("/{user-id}")
    public ResponseEntity<?> updateUser(@PathVariable("user-id") String userId,
                                        @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        try{
            UserResponseDTO userResponseDTO = userService.update(userId, userUpdateRequestDTO);
            return ResponseEntity.ok(userResponseDTO);
        }catch(RuntimeException e){
            log.error(e);
            return ResponseEntity.badRequest().body("잘못된 정보입니다.");
        }catch(Exception e){
            log.error(e);
            return ResponseEntity.internalServerError().body("일시적 오류가 발생했습니다.");
        }
    }
    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") String userId) {
        try{
            userService.delete(userId);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            log.error(e);
            return ResponseEntity.internalServerError().body("일시적 오류가 발생했습니다.");
        }
    }
}
