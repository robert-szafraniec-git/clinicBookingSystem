package clinic.com.example.clinic.infrastructure.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "doctor_availability")
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Instant availableFrom;
    private Instant availableTo;
}

