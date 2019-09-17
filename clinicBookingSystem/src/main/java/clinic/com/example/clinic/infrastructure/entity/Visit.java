package clinic.com.example.clinic.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Visit")
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime visitDate = LocalDateTime.now();

    @Column(name = "visit_end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime endVisitDate = LocalDateTime.now();

    @Column(name = "plan_length")
    private Integer plannedLength;

    @Column(name = "created_by")
    private Long userId;

    @Column(name = "status")
    private VisitStatus status;


    public Visit(Doctor doctor, Patient patient, String specialization, LocalDateTime createdOn,
                 LocalDateTime visitDate, LocalDateTime endVisitDate, Integer plannedLength, Long userId,
                 VisitStatus status) {
        this.doctor = doctor;
        this.patient = patient;
        this.specialization = specialization;
        this.createdOn = createdOn;
        this.visitDate = visitDate;
        this.endVisitDate = endVisitDate;
        this.plannedLength = plannedLength;
        this.userId = userId;
        this.status = status;
    }
}


