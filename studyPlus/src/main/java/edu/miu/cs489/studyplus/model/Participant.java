package edu.miu.cs489.studyplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity (name = "participant")
@NoArgsConstructor
@AllArgsConstructor
public class Participant extends User{
   @Id
   @Column(name = "participant_id")
    private Integer participantId;
    private LocalDate dob;
    @OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    @JoinColumn(name= "address_id")
    private Address primaryAddress;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participant")
    private List<Study> study;
    private LocalDate joinDate;

}
