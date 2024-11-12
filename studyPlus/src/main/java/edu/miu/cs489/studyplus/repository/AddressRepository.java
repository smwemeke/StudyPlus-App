package edu.miu.cs489.studyplus.repository;

import edu.miu.cs489.studyplus.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
