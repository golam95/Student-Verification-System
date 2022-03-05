package com.std.verification.service;

import java.util.List;
import com.std.verification.dto.DepartmentDto;
import com.std.verification.dto.DepartmentDtoList;
import com.std.verification.dto.StudentDto;
import com.std.verification.dto.StudentInfoDtoList;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.Department;


public interface DepartmentService {
	List<DepartmentDtoList> listOfDepartment();
	CommonMsg saveDepartment(DepartmentDto department);
	CommonMsg deleteDepartment(Long id);
	List<Department> findByUniId(Long id);
	List<Department> departmentList();
	List<StudentInfoDtoList> listOfStudent(StudentDto studentDto);
}
