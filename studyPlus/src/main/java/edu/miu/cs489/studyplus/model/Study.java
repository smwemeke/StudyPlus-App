package edu.miu.cs489.studyplus.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
//import java.sql.Date;

@Entity(name = "study")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long studyId;
    private String studyName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String studySponsor;

    @OneToMany(mappedBy = "study")
    private List<Notification> notification;//Study -----<- Notification

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER, mappedBy = "study")
    private List<Participant> participant;

    @OneToOne(mappedBy = "study", fetch = FetchType.EAGER)
    private Coordinator coordinator;


    public Study(String studyName, String description, LocalDate startDate, LocalDate endDate, String studySponsor) {
        this.studyName = studyName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.studySponsor = studySponsor;
    }


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
