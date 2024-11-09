package edu.miu.cs489.studyplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "study")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Integer studyId;
    private String studyName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String studySponsor;

    @OneToMany(mappedBy = "study")
    private Notification notification;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "study_participant",
            joinColumns = @JoinColumn(name = "study_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private List<Participant> participant;

    @Override
    public String toString() {
        return "Study{" +
                "studyName='" + studyName + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", studySponsor='" + studySponsor + '\'' +
                '}';
    }
}
