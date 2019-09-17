package clinic.com.example.clinic.domain;

import clinic.com.example.clinic.infrastructure.dto.DoctorDto;
import clinic.com.example.clinic.infrastructure.dto.VisitDto;
import clinic.com.example.clinic.infrastructure.entity.Doctor;
import clinic.com.example.clinic.infrastructure.entity.Visit;
import clinic.com.example.clinic.infrastructure.repository.DoctorRepository;
import clinic.com.example.clinic.infrastructure.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SpecializationFinder {
    private final DoctorRepository doctorRepository;

    public List<String> findAll() {
        return doctorRepository.findAll()
                .stream().map(doctor -> doctor.getSpecialization())
                .distinct()
                .collect(Collectors.toList());
    }
}
