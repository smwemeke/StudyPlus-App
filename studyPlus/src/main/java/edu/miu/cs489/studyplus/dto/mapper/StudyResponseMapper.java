package edu.miu.cs489.studyplus.dto.mapper;

import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.model.Study;
import org.springframework.stereotype.Service;

@Service
public class StudyResponseMapper {

    public StudyResponseDTO toStudyResponseDTO(Study study) {
        if(study == null){
            return null;
        }
        return new StudyResponseDTO(
                study.getStudyName(),
                study.getDescription(),
                study.getStartDate(),
                study.getEndDate(),
                study.getStudySponsor()
        );

    }
}
