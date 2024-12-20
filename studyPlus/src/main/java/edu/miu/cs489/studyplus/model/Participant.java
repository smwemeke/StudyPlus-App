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

    private LocalDate dob;

    @OneToOne(cascade = CascadeType.PERSIST, fetch =  FetchType.EAGER)
    @JoinColumn(name= "addressid")
    private Address address;

 @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
 @JoinTable(
         name = "study_participant",
         joinColumns = @JoinColumn(name = "studyid"),
         inverseJoinColumns = @JoinColumn(name = "userid")
 )
    private List<Study> study;
    private LocalDate joinDate;

}
