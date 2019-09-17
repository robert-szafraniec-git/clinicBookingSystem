package clinic.com.example.clinic.domain;

import clinic.com.example.clinic.infrastructure.dto.DoctorDto;
import clinic.com.example.clinic.infrastructure.entity.Doctor;
import clinic.com.example.clinic.infrastructure.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public void createOrUpdate(DoctorDto dto) {

        Doctor doctor = Doctor.builder().firstName(dto.getFirstName())
                .id(dto.getId())
                .lastName(dto.getLastName())
                .specialization(dto.getSpecialization())
                .build();
        doctorRepository.save(doctor);

    }

    public void delete(Long doctorId) {
        doctorRepository.deleteById(doctorId);

    }

}
