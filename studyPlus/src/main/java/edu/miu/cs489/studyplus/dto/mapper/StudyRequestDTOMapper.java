package edu.miu.cs489.studyplus.dto.mapper;

import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.model.Study;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudyRequestDTOMapper implements Function<StudyRequestDTO, Study> {
    @Override
    public Study apply(StudyRequestDTO studyRequest){
        if(studyRequest == null){
            return null;
        }
        return new Study(
                        studyRequest.studyName(),
                        studyRequest.description(),
                        studyRequest.startDate(),
                        studyRequest.endDate(),
                        studyRequest.studySponsor()
        );
    }
}
