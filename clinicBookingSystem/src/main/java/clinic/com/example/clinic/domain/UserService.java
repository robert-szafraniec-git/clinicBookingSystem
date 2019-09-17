package clinic.com.example.clinic.domain;

import clinic.com.example.clinic.infrastructure.dto.UserDto;
import clinic.com.example.clinic.infrastructure.entity.User;
import clinic.com.example.clinic.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createOrUpdate(UserDto dto) {

        User user = User.builder()
                .id(dto.getId())
                .login(dto.getLogin())
                .password(User.hashPassword(dto.getPassword()))
                .roles(dto.getRoles())
                .build();
        userRepository.save(user);

    }
}
