package com.std.verification.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.std.verification.dto.CreateLinkDto;
import com.std.verification.dto.UniversityURLDto;
import com.std.verification.dto.UniversityURLDtoList;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.UniversityURL;

public interface UniversityURLService {
	List<UniversityURLDtoList> listOfUniversityURL();
	CommonMsg saveUniversityURL(UniversityURLDto universityUrlDto);
	CommonMsg deleteUniversityURL(Long id);
	CommonMsg saveToBlobLogo(MultipartFile files,CreateLinkDto createLinkDto)throws IOException;
	UniversityURL findByUrlId(Long id);
	UniversityURL findByUniId(Long id);
	Long totalURL();
}
