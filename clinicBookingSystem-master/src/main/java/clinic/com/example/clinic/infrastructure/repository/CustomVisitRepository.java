package clinic.com.example.clinic.infrastructure.repository;

import clinic.com.example.clinic.infrastructure.entity.Visit;

import java.util.List;

public interface CustomVisitRepository {

    List<Visit> findByCriteria(Long doctorId, String specialization);
}
