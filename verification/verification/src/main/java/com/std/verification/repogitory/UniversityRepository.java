package com.std.verification.repogitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.std.verification.model.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long>{
	
	@Query(value="Select  *from university where name=:uniName",nativeQuery=true)
	University checkUniversityName(@Param("uniName") String uniName);
	
	University findByUniversityName(String universityName);
	
	long count();
	
}


