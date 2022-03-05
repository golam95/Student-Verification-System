package com.std.verification.serviceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.std.verification.dto.ProfileDto;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.Role;
import com.std.verification.model.User;
import com.std.verification.repogitory.UserRepository;
import com.std.verification.repogitory.RoleRepository;
import com.std.verification.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public CommonMsg updateProfileInfo(ProfileDto profileDtom) {
		CommonMsg commonMsg = new CommonMsg();
		User user = userRepository.findByUserName(profileDtom.getUserName());
		user.setFirstname(profileDtom.getFirstname());
		user.setEmail(profileDtom.getEmail());
		if (!profileDtom.getNewPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(profileDtom.getNewPassword()));
		}
		userRepository.save(user);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public void createUser() {
		User user = new User();
		user.setUserName("admin");
		user.setFirstname("admin");
		user.setEmail("admin@gmail.com");
		user.setRolename("ROLE_ADMIN");
		user.setRoles(Arrays.asList(roleRepository.findByname("ROLE_ADMIN")));
		user.setDate(new Date());
		user.setPassword(passwordEncoder.encode("123456"));
		userRepository.save(user);
	}

}
