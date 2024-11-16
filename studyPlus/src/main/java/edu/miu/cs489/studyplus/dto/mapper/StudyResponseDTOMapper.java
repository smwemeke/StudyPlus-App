package edu.miu.cs489.studyplus.dto.mapper;

import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.model.Study;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudyResponseDTOMapper implements Function<Study, StudyResponseDTO> {
    @Override
    public StudyResponseDTO apply(Study study) {
        return new StudyResponseDTO(
                study.getStudyName(),
                study.getDescription(),
                study.getStartDate(),
                study.getEndDate(),
                study.getStudySponsor()
        );

    }
}
