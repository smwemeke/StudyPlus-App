package edu.miu.cs489.studyplus;

import edu.miu.cs489.studyplus.model.Address;
import edu.miu.cs489.studyplus.model.Participant;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.AddressRepository;
import edu.miu.cs489.studyplus.repository.ParticipantRepository;
import edu.miu.cs489.studyplus.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

public class StudyPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyPlusApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner initializeData(
//			AddressRepository addressRepository,
//			StudyRepository studyRepository,
//			ParticipantRepository participantRepository) {
//		return args -> {
//			// Create studies
//			Study study1 = new Study("Study One", "Description of Study One", LocalDate.now().minusMonths(2), LocalDate.now().plusMonths(3), "Sponsor One");
//			Study study2 = new Study("Study Two", "Description of Study Two", LocalDate.now().minusMonths(4), LocalDate.now().plusMonths(1), "Sponsor Two");
//
//			List<Study> savedStudies = studyRepository.saveAll(List.of(study1, study2));
//
//			// Assign both studies to a list
//			List<Study> studies = new ArrayList<>(savedStudies);
//
//			// Create addresses
//			Address address1 = new Address("Street 1", "City A", "State A", 67851);
//			Address address2 = new Address("Street 2", "City B", "State B", 84020);
//			Address address3 = new Address("Street 3", "City C", "State C", 12346);
//			Address address4 = new Address("Street 4", "City D", "State D", 98765);
//
//			Address savedAddress1 = addressRepository.save(address1);
//			Address savedAddress2 = addressRepository.save(address2);
//			Address savedAddress3 = addressRepository.save(address3);
//			Address savedAddress4 = addressRepository.save(address4);
//
//			// Create participants
//			// Assuming participantId should be of type Long
//			Participant participant1 = new Participant(1L, LocalDate.of(1985, 5, 20), savedAddress1, studies, LocalDate.now().minusYears(2));
//			Participant participant2 = new Participant(2L, LocalDate.of(1990, 8, 15), savedAddress2, studies, LocalDate.now().minusYears(1));
//			Participant participant3 = new Participant(3L, LocalDate.of(1992, 3, 10), savedAddress3, studies, LocalDate.now().minusYears(3));
//			Participant participant4 = new Participant(4L, LocalDate.of(1988, 12, 25),savedAddress4, studies, LocalDate.now().minusYears(4));
//
//			// Save participants to the repository
//			Participant savedParticipant1 = participantRepository.save(participant1);
//			Participant savedParticipant2 = participantRepository.save(participant2);
//			Participant savedParticipant3 = participantRepository.save(participant3);
//			Participant savedParticipant4 = participantRepository.save(participant4);
//
//			// Confirm initialization in the console
//			System.out.println("Database initialized with 4 participants, 4 addresses, and 2 studies.");
//		};
//	}

}
