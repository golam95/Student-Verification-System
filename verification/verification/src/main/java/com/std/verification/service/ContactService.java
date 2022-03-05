package com.std.verification.service;

import java.util.List;
import com.std.verification.dto.ContactDto;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.Contact;



public interface ContactService {
	
	CommonMsg saveContact(ContactDto contactDto);
	
	CommonMsg upateContact(Long contactId);
	
	List<Contact> contactList();
	
	CommonMsg deleteContact(Long id);
	
	long totalContact();
	
}
