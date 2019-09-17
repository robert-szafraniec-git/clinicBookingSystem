package clinic.com.example.clinic.infrastructure.repository;

import clinic.com.example.clinic.infrastructure.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialization(String specialization);

}
