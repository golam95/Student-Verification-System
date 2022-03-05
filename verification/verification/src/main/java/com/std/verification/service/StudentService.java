package com.std.verification.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.std.verification.dto.StudentDto;
import com.std.verification.dto.StudentSearchDto;
import com.std.verification.dto.StudentSearchDtoList;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.Student;

public interface StudentService {
	CommonMsg saveStudent(MultipartFile files,StudentDto studentDto) throws IOException;
	List<Student> findByUniIdAndDeptId(Long uniId,Long deptid);
	CommonMsg updateStudent(StudentDto studentDto);
	CommonMsg deleteStudent(Long id);
	List<StudentSearchDtoList> listOfSearchStudent(StudentSearchDto studentSearchDto);
	List<StudentSearchDtoList> listOfSearchStudentUserPanel(Long uniId,Long deptId,String stdId);
	Long totalStudent();
}
