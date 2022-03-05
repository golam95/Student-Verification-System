package com.std.verification.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.std.verification.model.Role;
import com.std.verification.repogitory.RoleRepository;
import com.std.verification.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByname(String roleName) {
		return roleRepository.findByname(roleName);
	}

	@Override
	public void createRole(Role role) {
		roleRepository.save(role);
	}

}
