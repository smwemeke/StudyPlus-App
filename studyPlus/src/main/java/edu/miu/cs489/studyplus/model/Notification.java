package edu.miu.cs489.studyplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity(name = "notification")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationid")
    private Integer notficationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studyid")
    private Study study;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Participant participant;

    private String subject;
    private String message;
    private Timestamp sentAt;

}
