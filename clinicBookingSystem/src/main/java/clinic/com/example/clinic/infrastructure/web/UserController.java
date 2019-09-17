package clinic.com.example.clinic.infrastructure.web;

import clinic.com.example.clinic.domain.UserFinder;
import clinic.com.example.clinic.domain.UserService;
import clinic.com.example.clinic.infrastructure.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserFinder userFinder;

    @GetMapping("/{login}/edit")
    ModelAndView editUser(@RequestParam(required = false) String changePasswordMessage) {
        ModelAndView modelAndView = new ModelAndView("userEdit.html");

        return modelAndView;
    }

    @PostMapping("/user/updatePassword")
    ModelAndView changePassword(@RequestParam String passOld,
                                @RequestParam String passNew,
                                @RequestParam String passRepeat,
                                @RequestParam(required = false) String changePasswordMessage
    ) {
        ModelAndView modelAndView = new ModelAndView("userEdit.html");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


        if (securityContext.getAuthentication().getPrincipal().toString() == "anonymousUser") {
            login = "anonymousUser";
        } else {
            UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
            login = userDetails.getUsername();
        }

        UserDto userDto = userFinder.findByLogin(login);

        String errorMessageOldPassword = "Twoje stare hasło jest inne, Wpisz ponownie.";
        String errorMessageNewPassword = "Wpisz 2 razy takie samo nowe hasło.";
        String successMessege = "Hasło zostało zmienione.";

        if (login != "anonymousUser") {
            if (!bCryptPasswordEncoder.matches(passOld, userDto.getPassword())) {
                modelAndView.addObject("changePasswordMessage", errorMessageOldPassword);
//            modelAndView.addObject(passOld);
                modelAndView.addObject("passNew", passNew);
                modelAndView.addObject("passRepeat", passRepeat);
                return modelAndView;
            } else if (!passNew.equals(passRepeat)) {
                modelAndView.addObject("changePasswordMessage", errorMessageNewPassword);
                modelAndView.addObject("passOld", passOld);
                modelAndView.addObject("passNew", passNew);
                return modelAndView;
            } else {
                userDto.setPassword(passNew);
                modelAndView.addObject("changePasswordMessage", successMessege);
                userService.createOrUpdate(userDto);
                modelAndView.addObject("userDto", UserDto.NoPassword(userDto));
                return modelAndView;
            }
        } else {
            modelAndView.setViewName("index.html");
            return modelAndView;
        }

    }

}