package clinic.com.example.clinic.infrastructure.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
}
