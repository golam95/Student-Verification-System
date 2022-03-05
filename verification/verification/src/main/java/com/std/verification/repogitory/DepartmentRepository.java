package com.std.verification.repogitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.std.verification.dto.DepartmentDtoList;
import com.std.verification.dto.StudentInfoDtoList;
import com.std.verification.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	Department findByDeptNameAndUniId(String deptName,Long uniId);
	
	@Query(value="SELECT D.ID,U.ID as uniId,U.NAME,D.NAME as DepartmentName,D.DATE FROM DEPARTMENT D\r\n" + 
			"LEFT JOIN UNIVERSITY U ON D.U_ID=U.ID",nativeQuery=true)
	List<DepartmentDtoList> listOfDepartment();
	
	List<Department> findByUniId(Long id);
	
	@Query(value="select s.id,s.std_id as stdId,s.name as stdName,s.contact_no as phoneNo,s.status from student s\r\n" + 
			"			left join university u on s.u_id=u.id\r\n" + 
			"			left join department d on s.dept_id=d.id  \r\n" + 
			"			where s.u_id=?1 and s.dept_id=?2 and s.batch=?3 and s.status=?4",nativeQuery=true)
	List<StudentInfoDtoList> listOfStudent(Long uid,Long depid,String batch,String status);
	
}
