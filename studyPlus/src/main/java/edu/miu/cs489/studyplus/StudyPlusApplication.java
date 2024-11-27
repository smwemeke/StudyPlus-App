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
}