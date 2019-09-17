package clinic.com.example.clinic.domain;

import clinic.com.example.clinic.infrastructure.dto.VisitDto;
import clinic.com.example.clinic.infrastructure.entity.Visit;
import clinic.com.example.clinic.infrastructure.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitFinder {

    private final VisitRepository visitRepository;
    private final UserFinder userFinder;

    public VisitDto findById(Long visitId) {
        return visitRepository.findById(visitId)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalStateException("Nie ma takiej wizyty"));

    }

    public List<VisitDto> findByDoctorId(Long doctorId) {
        return visitRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public List<VisitDto> findBySpecialization(String specialization) {
        return visitRepository.findBySpecialization(specialization)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public List<VisitDto> findByDoctorIdAndSpecialization(Long doctorId, String specialization) {
        return visitRepository.findByCriteria(doctorId, specialization)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public List<VisitDto> findAll() {
        return visitRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public VisitDto toDto(Visit visit) {

        return VisitDto.builder()
                .id(visit.getId())
                .doctorId(visit.getDoctor().getId())
                .doctorFirstName(visit.getDoctor().getFirstName())
                .doctorLastName(visit.getDoctor().getLastName())
                .specialization(visit.getDoctor().getSpecialization())
                .patientId(visit.getPatient().getId())
                .patientFirstName(visit.getPatient().getFirstName())
                .patientLastName(visit.getPatient().getLastName())
                .patientAge(visit.getPatient().getAge(visit.getPatient().getPesel()))
                .visitDate(visit.getVisitDate())
                .login(userFinder
                        .findById(visit.getUserId())
                        .map(userDto -> userDto.getLogin())
                        .orElseThrow(()->new IllegalStateException("Użytkownik nie istnieje")))
                .plannedLength(visit.getPlannedLength())
                .status(visit.getStatus())
                .statusDescription(visit.getStatus().getDescription_PL())
                .build();

    }
}
