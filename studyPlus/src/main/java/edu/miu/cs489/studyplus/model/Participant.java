package edu.miu.cs489.studyplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity (name = "participant")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Participant extends User{

   @Column(name = "participant_id",unique = true)
    private Long participantId;

    private LocalDate dob;

    @OneToOne(cascade = CascadeType.MERGE, fetch =  FetchType.EAGER)
    @JoinColumn(name= "address_id")
    private Address primaryaddress;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participant")
    private List<Study> study;
    private LocalDate joinDate;

}
