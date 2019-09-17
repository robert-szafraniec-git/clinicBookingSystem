package clinic.com.example.clinic.infrastructure.entity;

import clinic.com.example.clinic.Utilities.Scheduler;
import clinic.com.example.clinic.infrastructure.dto.DoctorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String specialization;

//    @OneToOne
//    private Scheduler scheduler;

    @OneToMany
    private Set<DoctorAvailability> doctorAvailabilities;

    public DoctorDto toDto() {
        return DoctorDto.builder().id(id)
                .firstName(firstName)
                .lastName(lastName)
                .specialization(specialization).build();
    }
}
