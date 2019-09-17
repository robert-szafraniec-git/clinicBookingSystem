package clinic.com.example.clinic.domain;

import clinic.com.example.clinic.infrastructure.dto.PatientDto;
import clinic.com.example.clinic.infrastructure.entity.Patient;
import clinic.com.example.clinic.infrastructure.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public void create(PatientDto dto) {

        Patient patient = Patient.builder().firstName(dto.getFirstName())
                .id(dto.getId())
                .lastName(dto.getLastName())
                .pesel(dto.getPesel())
                .build();
        patientRepository.save(patient);

    }

    public void delete(Long patientId) {
        patientRepository.deleteById(patientId);

    }

}