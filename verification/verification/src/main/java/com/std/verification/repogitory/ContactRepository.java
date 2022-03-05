package com.std.verification.repogitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.std.verification.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	Contact findByContactId(Long id);
	
}
