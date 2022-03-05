package com.std.verification.service;

import com.std.verification.model.Role;

public interface RoleService {
	Role findByname(String roleName);
	void createRole(Role role);
}
