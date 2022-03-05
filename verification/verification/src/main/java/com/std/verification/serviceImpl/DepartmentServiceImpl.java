package com.std.verification.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.std.verification.dto.DepartmentDto;
import com.std.verification.dto.DepartmentDtoList;
import com.std.verification.dto.StudentDto;
import com.std.verification.dto.StudentInfoDtoList;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.Department;
import com.std.verification.repogitory.DepartmentRepository;
import com.std.verification.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<DepartmentDtoList> listOfDepartment() {
		return departmentRepository.listOfDepartment();
	}

	@Override
	public CommonMsg saveDepartment(DepartmentDto departmentDto) {
		CommonMsg commonMsg = new CommonMsg();
		Department department = new Department();
		if (departmentDto.getUpdateId().isEmpty()) {
			Department checkName = departmentRepository.findByDeptNameAndUniId(departmentDto.getDeptName(),departmentDto.getUniId());
			if (checkName != null) {
				commonMsg.setMsgCode("201");
			} else {
				department.setUniId(departmentDto.getUniId());
				department.setDeptName(departmentDto.getDeptName().toUpperCase());
				department.setDate(new Date());
				commonMsg.setMsgCode("200");
				departmentRepository.save(department);
			}

		} else {
			department.setDeptId(Long.parseLong(departmentDto.getUpdateId()));
			department.setUniId(departmentDto.getUniId());
			department.setDeptName(departmentDto.getDeptName().toUpperCase());
			department.setDate(new Date());
			commonMsg.setMsgCode("200");
			departmentRepository.save(department);
		}
		
		return commonMsg;
	}

	@Override
	public CommonMsg deleteDepartment(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		departmentRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<Department> findByUniId(Long id) {
		return departmentRepository.findByUniId(id);
	}

	@Override
	public List<StudentInfoDtoList> listOfStudent(StudentDto studentdto) {
        return departmentRepository.listOfStudent(studentdto.getUniId(), studentdto.getDeptId(), studentdto.getBatch(), studentdto.getStatus());
	}

	@Override
	public List<Department> departmentList() {
		return departmentRepository.findAll();
	}
	
}
