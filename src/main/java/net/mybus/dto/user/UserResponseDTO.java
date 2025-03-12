package net.mybus.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.mybus.domain.User;

@Getter
@Builder
@ToString
public class UserResponseDTO {
    private String userId;
    private String userName;
}
