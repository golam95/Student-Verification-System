package com.std.verification.serviceImpl;


import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.std.verification.dto.UniversityDto;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.University;
import com.std.verification.repogitory.RoleRepository;
import com.std.verification.repogitory.UniversityRepository;
import com.std.verification.repogitory.UserRepository;
import com.std.verification.service.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {

	@Autowired
	private UniversityRepository universityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	

	@Override
	public List<University> listOfUniversity() {
		return universityRepository.findAll();
	}

	@Override
	public CommonMsg saveUniversity(UniversityDto universityDto) {
		CommonMsg commonMsg = new CommonMsg();
		University university = new University();
		if (universityDto.getUpdateId().isEmpty()) {
			University checkName = universityRepository.findByUniversityName(universityDto.getUniversityName());
			if (checkName != null) {
				commonMsg.setMsgCode("201");
			} else {
				university.setUniversityName(universityDto.getUniversityName().toUpperCase());
				university.setUniShortName(universityDto.getShortUniversityName().toUpperCase());
				university.setDate(new Date());
				commonMsg.setMsgCode("200");
				universityRepository.save(university);
			}
		} else {
			university.setUnID(Long.parseLong(universityDto.getUpdateId()));
			university.setUniversityName(universityDto.getUniversityName().toUpperCase());
			university.setUniShortName(universityDto.getShortUniversityName().toUpperCase());
			university.setDate(new Date());
			commonMsg.setMsgCode("200");
			universityRepository.save(university);
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteUniversity(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		universityRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public University findByUniversityName(String universityName) {
		return universityRepository.findByUniversityName(universityName);
	}

	@Override
	public University checkUniversityName(String uniName) {
		return universityRepository.checkUniversityName(uniName);
	}

	@Override
	public Long totalUniversity() {
//		User user=new User();
//		user.setDate(new Date());
//		user.setPassword(passwordEncoder.encode("1234"));
//		user.setRolename("ROLE_ADMIN");
//		user.setRoles(Arrays.asList(roleRepository.findByname("ROLE_ADMIN")));
//		userRepository.save(user);
		return universityRepository.count();
	}

}
