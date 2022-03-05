package com.std.verification.service;

import java.util.List;
import com.std.verification.dto.UniversityDto;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.University;

public interface UniversityService {
	List<University> listOfUniversity();
	CommonMsg saveUniversity(UniversityDto university);
	CommonMsg deleteUniversity(Long id);
	University findByUniversityName(String universityName);
	University checkUniversityName(String uniName);
	Long totalUniversity();
}
