package clinic.com.example.clinic.infrastructure.entity;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void hashPasswordTest() {
        //given
        String admin = "admin";
        String admin1 = "admin1";
        String _1 = "1";


        //when
        String adminHash = User.hashPassword(admin);
        String admin1Hash = User.hashPassword(admin1);
        String _1Hash = User.hashPassword(_1);

        //then
        System.out.println(adminHash);
        System.out.println(admin1Hash);
        System.out.println(_1Hash);

    }


    @Test
    public void encodePassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPass = bCryptPasswordEncoder.encode("admin");
        System.out.println(encryptedPass);
    }
}