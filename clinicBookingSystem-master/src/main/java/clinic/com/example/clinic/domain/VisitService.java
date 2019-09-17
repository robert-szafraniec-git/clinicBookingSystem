package clinic.com.example.clinic.domain;

import clinic.com.example.clinic.infrastructure.dto.VisitDto;
import clinic.com.example.clinic.infrastructure.entity.Doctor;
import clinic.com.example.clinic.infrastructure.entity.Patient;
import clinic.com.example.clinic.infrastructure.entity.Visit;
import clinic.com.example.clinic.infrastructure.entity.VisitStatus;
import clinic.com.example.clinic.infrastructure.repository.DoctorRepository;
import clinic.com.example.clinic.infrastructure.repository.PatientRepository;
import clinic.com.example.clinic.infrastructure.repository.UserRepository;
import clinic.com.example.clinic.infrastructure.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;

    public void createOrUpdate(VisitDto dto) {

        Doctor foundDoctor = doctorRepository
                .findById(dto.getDoctorId())
                .orElseThrow(() -> new IllegalStateException("Nie ma takiego doktora"));

        Patient foundPtient = patientRepository
                .findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalStateException("Nie ma takiego doktora"));

        Visit visit = Visit.builder()
                .id(dto.getId())
                .doctor(foundDoctor)
                .patient(foundPtient)
                .plannedLength(VisitService.getStandardVisitLengthForSpecialization(dto.getSpecialization()))
                .visitDate(dto.getVisitDate())
                .endVisitDate(dto.getEndVisitDate())
                .status(VisitStatus.PLAN)
                .userId(userRepository.findByLogin(dto.getLogin()).map(user -> user.getId()).get())
                .createdOn(LocalDateTime.now())
                .specialization(dto.getSpecialization())
                .status(dto.getStatus())
                .build();
        visitRepository.save(visit);

    }

    public void delete(Long visitId) {
        visitRepository.deleteById(visitId);
    }


    public static Integer getStandardVisitLengthForSpecialization(String specialization) {

        //future feature
        return 15;
    }

}
