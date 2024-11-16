package edu.miu.cs489.studyplus.dto.mapper;

import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.model.Study;
import org.springframework.stereotype.Service;

@Service
public class StudyRequestMapper {

    public Study toStudy(StudyRequestDTO studyRequest){
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
