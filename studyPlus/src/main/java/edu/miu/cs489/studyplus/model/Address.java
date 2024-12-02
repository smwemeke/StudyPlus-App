package edu.miu.cs489.studyplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "addressid")
    private Integer addressId;
    private String street;
    private String city;
    private String state;
    private Integer zip;
//    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
//    private Participant participant;  // Foriegn Key is participantId

    public Address(String street, String city, String state, Integer zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                '}';
    }
}
