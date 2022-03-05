package com.std.verification.repogitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.std.verification.dto.UniversityURLDtoList;
import com.std.verification.model.University;
import com.std.verification.model.UniversityURL;

@Repository
public interface UniversityURLRepository  extends JpaRepository<UniversityURL, Long>{
	
	University findByUrlName(String universityUrlName);
	
	UniversityURL findByUrlId(Long id);
	
	UniversityURL findByUniId(Long id);

	@Query(value="SELECT L.id AS urlId,U.id as uniId,U.name,L.url_name AS urlName,L.date,L.logo FROM URL L\r\n" + 
			"INNER JOIN UNIVERSITY U ON L.uni_id=U.id",nativeQuery=true)
	List<UniversityURLDtoList> listOfUniversityURL();
	
	

}
