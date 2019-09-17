package clinic.com.example.clinic.infrastructure.dto;

import clinic.com.example.clinic.infrastructure.entity.VisitStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {

    private Long id;
    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private String specialization;
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private Integer patientAge;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime visitDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime endVisitDate;
    private String login;
    private Integer plannedLength;
    private VisitStatus status;
    private String statusDescription;
}
