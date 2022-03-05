package com.std.verification.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.std.verification.dto.ProfileDto;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.User;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);
	
	CommonMsg updateProfileInfo(ProfileDto profileDtom);
	
	void createUser();
}
