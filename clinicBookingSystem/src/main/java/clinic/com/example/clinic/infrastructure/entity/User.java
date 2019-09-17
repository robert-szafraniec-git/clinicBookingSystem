package clinic.com.example.clinic.infrastructure.entity;

import clinic.com.example.clinic.infrastructure.dto.DoctorDto;
import clinic.com.example.clinic.infrastructure.dto.UserDto;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String login;
    private String password;

//    public void setPassword(String password) {
//        this.password = hashPassword(password);
//    }

    public static String hashPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPass = bCryptPasswordEncoder.encode(password);
        return encryptedPass;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles;


    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .login(login)
                .roles(roles)
                .password(password)
                .build();
    }


}
