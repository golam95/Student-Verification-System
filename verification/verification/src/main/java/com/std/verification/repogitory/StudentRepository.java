package com.std.verification.repogitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.std.verification.dto.StudentSearchDtoList;
import com.std.verification.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	List<Student> findByUniIdAndDeptId(Long uniId,Long deptid);
	
	Student findByStdId(Long id);
	
	@Query(value="SELECT s.name as stdName,S.std_id,S.email,S.contact_no,D.NAME as deptName,u.name as uniName,L.logo FROM STUDENT S \r\n" + 
			"LEFT JOIN UNIVERSITY U ON S.U_ID=U.ID\r\n" + 
			"LEFT JOIN DEPARTMENT D ON S.DEPT_ID=D.ID\r\n" + 
			"LEFT JOIN URL L ON S.U_ID = L.UNI_ID\r\n" + 
			"WHERE S.U_ID=?1\r\n" + 
			"AND S.DEPT_ID=?2\r\n" + 
			"AND (S.STD_ID=?3 OR S.NAME=?3)",nativeQuery=true)
	List<StudentSearchDtoList> listOfSearchStudent(Long uId,Long deptId,String stdIdNo);

}
