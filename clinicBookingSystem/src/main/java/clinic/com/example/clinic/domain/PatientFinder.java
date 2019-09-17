package clinic.com.example.clinic.domain;

import clinic.com.example.clinic.infrastructure.dto.PatientDto;
import clinic.com.example.clinic.infrastructure.entity.Patient;
import clinic.com.example.clinic.infrastructure.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientFinder {
    private final PatientRepository patientRepository;

    public PatientDto findById(Long id) {
        return patientRepository
                .findById(id)
                .map(Patient::toDto)
                .orElseThrow(() -> new IllegalStateException("Nie ma takiego pacjenta"));
    }

    public List<PatientDto> findByPesel(Long pesel) {
        return patientRepository.findByPesel(pesel).stream()
                .map(Patient::toDto)
                .collect(Collectors.toList());
    }

    public List<PatientDto> findAll() {
        return patientRepository.findAll()
                .stream().map(Patient::toDto)
                .collect(Collectors.toList());
    }
}
