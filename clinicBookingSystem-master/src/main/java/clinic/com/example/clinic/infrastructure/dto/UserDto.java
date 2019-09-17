package clinic.com.example.clinic.infrastructure.dto;

import clinic.com.example.clinic.infrastructure.entity.UserRole;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private Long id;
    private String login;
    private String password;
    private Set<UserRole> roles;


    public static UserDto NoPassword(UserDto userDto) {
        return UserDto.builder()
                .id(userDto.id)
                .login(userDto.login)
                .roles(userDto.roles)
                .build();
    }

}
