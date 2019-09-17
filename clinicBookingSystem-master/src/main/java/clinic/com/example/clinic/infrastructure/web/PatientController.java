package clinic.com.example.clinic.infrastructure.web;

import clinic.com.example.clinic.domain.PatientFinder;
import clinic.com.example.clinic.domain.PatientService;
import clinic.com.example.clinic.infrastructure.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientFinder patientFinder;
    private final PatientService patientService;

    @GetMapping("/get/all")
    ModelAndView getPatients() {
        ModelAndView modelAndView = new ModelAndView("patients.html");
        modelAndView.addObject("patients", patientFinder.findAll());
        return modelAndView;
    }


    @GetMapping("/get/{pesel}")
    ModelAndView getPatients(@PathVariable Long pesel) {
        ModelAndView modelAndView = new ModelAndView("patients.html");
        modelAndView.addObject("pesel", pesel);
        modelAndView.addObject("patients", patientFinder.findByPesel(pesel));
        return modelAndView;
    }


    @GetMapping("/create")
    ModelAndView createPatientView() {
        ModelAndView modelAndView = new ModelAndView("createPatient.html");
        modelAndView.addObject("patient", new PatientDto());
        return modelAndView;
    }

    @PostMapping("/create")
    String createPatient(@ModelAttribute PatientDto patientDto) {
        patientService.create(patientDto);
        return "redirect:/patient/get/all";
    }

    @GetMapping("/delete")
    String deletePatient(@RequestParam Long id) {
        patientService.delete(id);
        return "redirect:/patient/get/all";
    }

    @GetMapping("/edit")
    ModelAndView editPatient(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("createPatient.html");
        modelAndView.addObject("patient", patientFinder.findById(id));
        return modelAndView;
    }
}