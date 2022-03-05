package com.std.verification.repogitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.std.verification.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByname(String theRoleName);
}
