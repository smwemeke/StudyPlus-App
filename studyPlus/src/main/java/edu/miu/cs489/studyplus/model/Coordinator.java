package edu.miu.cs489.studyplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Coordinator extends  User{
    @Column(name = "name",unique = true)
    private String coordinatorname;
    @OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    @JoinColumn(name= "studyid")
    private Study study;
}
