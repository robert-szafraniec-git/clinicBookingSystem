package clinic.com.example.clinic.infrastructure.repository;

import clinic.com.example.clinic.infrastructure.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findById(String id);
    List<Patient> findByPesel(Long pesel);
}
