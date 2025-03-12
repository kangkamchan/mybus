package net.mybus.service.user;

import lombok.RequiredArgsConstructor;
import net.mybus.domain.User;
import net.mybus.dto.user.UserLoginRequestDTO;
import net.mybus.dto.user.UserRegisterRequestDTO;
import net.mybus.dto.user.UserResponseDTO;
import net.mybus.dto.user.UserUpdateRequestDTO;
import net.mybus.repository.UserRepository;
import net.mybus.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    /**
     * 회원가입
     * @param userRegisterRequestDTO 회원가입을 위한 request DTO
     * @return UserResponseDTO 회원가입 정보를 반영한 UserResponseDTO
     */
    public UserResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO) {
        if(isDuplicated(userRegisterRequestDTO.getUserId())){
            throw new RuntimeException("중복된 아이디입니다.");
        }
        User user = User.builder()
                .userId(userRegisterRequestDTO.getUserId())
                .password(passwordEncoder.encode(userRegisterRequestDTO.getPassword()))
                .userName(userRegisterRequestDTO.getUserName())
                .build();
        User sevedUser = userRepository.save(user);
        return UserResponseDTO.builder()
                .userId(sevedUser.getUserId())
                .userName(sevedUser.getUserName())
                .build();
    }

    public boolean isDuplicated(String userId){
        try{
            read(userId);
            return true;
        }catch(RuntimeException e){
            return false;
        }
    }
    public String login(UserLoginRequestDTO userLoginRequestDTO) {
        User user = userRepository.findByUserId(userLoginRequestDTO.getUserId()).orElseThrow(()->new RuntimeException("잘못된 정보입니다."));
        if(!passwordEncoder.matches(userLoginRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("잘못된 정보입니다.");
        }
        return jwtUtil.generateToken(user.getUserId(), user.getUserName());
    }

    public UserResponseDTO read(String userId){
        User user = userRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("잘못된 정보입니다."));
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }

    public UserResponseDTO update(String userId, UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("잘못된 정보입니다."));
        if(userUpdateRequestDTO.getUserName() != null && !userUpdateRequestDTO.getUserName().isEmpty()){
            user.setUserName(userUpdateRequestDTO.getUserName());
        }
        if(userUpdateRequestDTO.getPassword() != null && !userUpdateRequestDTO.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(userUpdateRequestDTO.getPassword()));
        }
        User sevedUser = userRepository.save(user);
        return UserResponseDTO.builder()
                .userId(sevedUser.getUserId())
                .userName(sevedUser.getUserName())
                .build();
    }

    public void delete(String userId) {
        userRepository.deleteById(userId);
    }
}
