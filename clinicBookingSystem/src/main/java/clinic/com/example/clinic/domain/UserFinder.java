package clinic.com.example.clinic.domain;

import clinic.com.example.clinic.infrastructure.dto.UserDto;
import clinic.com.example.clinic.infrastructure.entity.User;
import clinic.com.example.clinic.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFinder {

    private final UserRepository userRepository;

    public UserDto findByLogin(String login) {

        return userRepository
                .findByLogin(login)
                .map(User::toDto)
                .orElseThrow(() -> new IllegalStateException("Nie ma tekiego usera"));
    }

    public Optional<UserDto> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        return userRepository
                .findById(id)
                .map(User::toDto);
    }

}
