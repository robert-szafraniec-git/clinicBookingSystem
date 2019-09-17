package clinic.com.example.clinic.infrastructure.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class MainViewController {

    @RequestMapping
    ModelAndView mainView() {

        ModelAndView modelAndView = new ModelAndView("index.html");
        modelAndView.addObject("todayDate", LocalDate.now());
        return modelAndView;
    }
}
