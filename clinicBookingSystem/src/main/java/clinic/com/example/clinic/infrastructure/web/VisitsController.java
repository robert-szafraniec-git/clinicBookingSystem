package clinic.com.example.clinic.infrastructure.web;

import clinic.com.example.clinic.domain.*;
import clinic.com.example.clinic.infrastructure.dto.VisitDto;
import clinic.com.example.clinic.infrastructure.entity.VisitStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitsController {

    private final VisitFinder visitFinder;
    private final DoctorFinder doctorFinder;
    private final PatientFinder patientFinder;
    private final SpecializationFinder specializationFinder;
    private final VisitService visitService;
    private final UserFinder userFinder;

    @GetMapping("/get/doctor")
    ModelAndView getDoctorsVisits(@RequestParam Long doctorId) {
        ModelAndView modelAndView = new ModelAndView("visits.html");
        modelAndView.addObject("VisitDto", new VisitDto());
        modelAndView.addObject("visits", visitFinder.findByDoctorId(doctorId));
        modelAndView.addObject("specializations", specializationFinder.findAll());
        return modelAndView;
    }

    @GetMapping("/get/all")
    ModelAndView getAllVisits() {
        ModelAndView modelAndView = new ModelAndView("visits.html");
        modelAndView.addObject("visits", visitFinder.findAll());
        modelAndView.addObject("VisitDto", new VisitDto());
        modelAndView.addObject("doctors", doctorFinder.findAll());
        modelAndView.addObject("specializations", specializationFinder.findAll());
        return modelAndView;
    }

    @PostMapping("/get")
    public ModelAndView getDoctorsVisits(@ModelAttribute("VisitDto") VisitDto visitDto) {
        ModelAndView modelAndView = new ModelAndView("visits.html");
        modelAndView.addObject("VisitDto", new VisitDto());
        modelAndView.addObject("doctors", doctorFinder.findAll());
        modelAndView.addObject("specializations", specializationFinder.findAll());
        modelAndView.addObject("visits",
                visitFinder.findByDoctorIdAndSpecialization(visitDto.getDoctorId(), visitDto.getSpecialization()));
        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView createVisitView() {
        ModelAndView modelAndView = new ModelAndView("createVisit.html");
        modelAndView.addObject("doctors", doctorFinder.findAll());
        modelAndView.addObject("patients", patientFinder.findAll());
        modelAndView.addObject("patients", patientFinder.findAll());
        modelAndView.addObject("visitStatuses", Arrays.asList(VisitStatus.values()));

        LocalDate visitStartDate = LocalDate.now();
        modelAndView.addObject("visitStartDate", visitStartDate);
        LocalTime visitTime = LocalTime.now();
        modelAndView.addObject("visitTime", visitTime);

        VisitDto visitDto = new VisitDto();
        visitDto.setPlannedLength(VisitService.getStandardVisitLengthForSpecialization(visitDto.getSpecialization()));
        modelAndView.addObject("VisitDto", visitDto);
        return modelAndView;
    }

    @PostMapping("/create")
    String createVisit(@ModelAttribute VisitDto visitDto,
                       @RequestParam("visitStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate visitStartDate,
                       @RequestParam("visitTime") @DateTimeFormat(pattern = "HH:mm") LocalTime visitTime

    ) {
        ModelAndView modelAndView = new ModelAndView("createVisit.html");
        modelAndView.addObject("doctors", doctorFinder.findAll());
        modelAndView.addObject("patients", patientFinder.findAll());
        modelAndView.addObject("visitTime", visitTime);
        modelAndView.addObject("visitStartDate", visitStartDate);

        if (visitDto.getDoctorId() == null || visitDto.getPatientId() == null) {
            return "redirect:/visits/create";
        } else {


            visitDto.setSpecialization(doctorFinder.findById(visitDto.getDoctorId()).getSpecialization());
            visitDto.setVisitDate(visitStartDate.atTime(visitTime));
            visitTime = visitTime.plusMinutes(visitDto.getPlannedLength());
            LocalDateTime endVisitDate = visitStartDate.atTime(visitTime.getHour(), visitTime.getMinute());
            visitDto.setEndVisitDate(endVisitDate);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            String login = securityContext.getAuthentication().getPrincipal().toString();

            if (login == "anonymousUser") {
                visitDto.setLogin(login);
            } else {
                UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
                visitDto.setLogin(userDetails.getUsername());
            }
            visitService.createOrUpdate(visitDto);
            return "redirect:/visits/get/all";
        }
    }

    @GetMapping("/delete")
    String deleteVisit(@RequestParam Long id) {
        visitService.delete(id);
        return "redirect:/visits/get/all";
    }

    @GetMapping("/edit")
    ModelAndView editVisit(@ModelAttribute VisitDto visitDto) {

        visitDto = visitFinder.findById(visitDto.getId());

        ModelAndView modelAndView = new ModelAndView("createVisit.html");
        modelAndView.addObject("doctors", doctorFinder.findAll());
        modelAndView.addObject("patients", patientFinder.findAll());
        modelAndView.addObject("VisitDto", visitFinder.findById(visitDto.getId()));

        modelAndView.addObject("visitStatuses", Arrays.asList(VisitStatus.values()));

        LocalDate visitStartDate = visitDto.getVisitDate().toLocalDate();
        LocalTime visitTime = visitDto.getVisitDate().toLocalTime();

        modelAndView.addObject("visitTime", visitTime);
        modelAndView.addObject("visitStartDate", visitStartDate);
        return modelAndView;
    }
}
