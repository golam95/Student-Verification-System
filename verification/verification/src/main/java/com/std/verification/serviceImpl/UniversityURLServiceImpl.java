package com.std.verification.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.std.verification.dto.CreateLinkDto;
import com.std.verification.dto.UniversityURLDto;
import com.std.verification.dto.UniversityURLDtoList;
import com.std.verification.helper.CommonMsg;
import com.std.verification.helper.Helper;
import com.std.verification.model.University;
import com.std.verification.model.UniversityURL;
import com.std.verification.repogitory.UniversityURLRepository;
import com.std.verification.service.UniversityURLService;

@Service
public class UniversityURLServiceImpl implements UniversityURLService {

	@Autowired
	private UniversityURLRepository universityUrlRepository;

	@Autowired
	private Helper helper;

	@Override
	public List<UniversityURLDtoList> listOfUniversityURL() {
		return universityUrlRepository.listOfUniversityURL();
	}

	@Override
	public CommonMsg saveUniversityURL(UniversityURLDto universityUrlDto) {
		CommonMsg commonMsg = new CommonMsg();
		UniversityURL universityUrl = new UniversityURL();
		if (universityUrlDto.getUpdateId().isEmpty()) {
			University checkName = universityUrlRepository.findByUrlName(universityUrlDto.getUrlName());
			if (checkName != null) {
				commonMsg.setMsgCode("201");
			} else {
				universityUrl.setUniId(universityUrlDto.getUniId());
				universityUrl.setUrlName(universityUrlDto.getUrlName());
				universityUrl.setDate(new Date());
				commonMsg.setMsgCode("200");
				universityUrlRepository.save(universityUrl);
			}
		} else {
			
			
			
			universityUrl.setUrlId(Long.parseLong(universityUrlDto.getUpdateId()));
			universityUrl.setUniId(universityUrlDto.getUniId());
			universityUrl.setUrlName(universityUrlDto.getUrlName());
			universityUrl.setDate(new Date());
			commonMsg.setMsgCode("200");
			universityUrlRepository.save(universityUrl);
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteUniversityURL(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		universityUrlRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public CommonMsg saveToBlobLogo(MultipartFile files, CreateLinkDto createLinkDto) throws IOException {
	
		CommonMsg commonMsg = new CommonMsg();
		
		UniversityURL universityURL = new UniversityURL();
		
		if(createLinkDto.getUpdateId().isEmpty()) {
			
			UniversityURL universityUrl=universityUrlRepository.findByUniId(createLinkDto.getUniId());
			
			if(universityUrl!=null) {
				commonMsg.setMsgCode("300");
			}else {
				universityURL.setUniId(createLinkDto.getUniId());
				universityURL.setUrlName(createLinkDto.getUrlName());
				universityURL.setUniLogoBlob(files.getBytes());
				universityURL.setDate(new Date());
				universityUrlRepository.save(universityURL);
				commonMsg.setMsgCode("200");
			}
	
		}else {
			
			UniversityURL universityUrl1=universityUrlRepository.findByUrlId(Long.parseLong(createLinkDto.getUpdateId()));
			
			universityUrl1.setUniId(createLinkDto.getUniId());
			universityUrl1.setUrlName(createLinkDto.getUrlName());
			if(createLinkDto.getHasNewImage().equals("true")&& !files.isEmpty()) {
				universityUrl1.setUniLogoBlob(files.getBytes());
			}
			universityUrl1.setDate(new Date());
			universityUrlRepository.save(universityUrl1);
			commonMsg.setMsgCode("200");
		}
		return commonMsg;
	}

	@Override
	public UniversityURL findByUrlId(Long id) {
		return universityUrlRepository.findByUrlId(id);
	}

	@Override
	public UniversityURL findByUniId(Long id) {
		return universityUrlRepository.findByUniId(id);
	}

	@Override
	public Long totalURL() {
		return universityUrlRepository.count();
	}


}
