package clinic.com.example.clinic.infrastructure.repository;

import clinic.com.example.clinic.infrastructure.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long>, CustomVisitRepository {

    List<Visit> findByDoctorId(Long doctorId);

    List<Visit> findBySpecialization(String specialization);

    @Query("select v from Visit v where (v.doctor.id = :doctorId or :doctorId is null) or " +
            "(v.doctor.specialization = :specialization or :specialization is null)")
    List<Visit> findByDoctorIdAndSpecialization(@Param("doctorId") Long doctorId,@Param("specialization") String specialization);
}