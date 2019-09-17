package clinic.com.example.clinic.security;

import clinic.com.example.clinic.infrastructure.entity.User;
import clinic.com.example.clinic.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository
                .findByLogin(login)
                .orElseThrow(()->new IllegalStateException("Nie ma takiego usera"));

        if (user == null) {
            throw new UsernameNotFoundException("Username with login " + login + " not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), mapRoles(user));

    }

    private List<GrantedAuthority> mapRoles(User user) {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

}
