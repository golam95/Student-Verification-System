package com.std.verification.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.std.verification.dto.StudentDto;
import com.std.verification.dto.StudentSearchDto;
import com.std.verification.dto.StudentSearchDtoList;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.Student;
import com.std.verification.repogitory.StudentRepository;
import com.std.verification.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public CommonMsg saveStudent(MultipartFile files, StudentDto studentDto) throws IOException {
		CommonMsg commonMsg = new CommonMsg();
		List<Student> getBindingResult = bindStudent(files, studentDto);
		try {
			for (Student student : getBindingResult) {
				studentRepository.save(student);
			}
			commonMsg.setMsgCode("200");
		} catch (Exception ex) {
			commonMsg.setMsgCode("500");
		}
		return commonMsg;
	}

	@Override
	public List<Student> findByUniIdAndDeptId(Long uniId, Long deptid) {
		return studentRepository.findByUniIdAndDeptId(uniId, deptid);
	}

	@SuppressWarnings("deprecation")
	private List<Student> bindStudent(MultipartFile files, StudentDto studentDto) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		List<Student> productList = new ArrayList<>();
		String status = "";
		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {
				Student student = new Student();
				XSSFRow row = worksheet.getRow(index);

				Cell cellStdRollNo = worksheet.getRow(index).getCell(0);
				String string_StdRollNo = null;

				if (cellStdRollNo == null || cellStdRollNo.getCellType() == Cell.CELL_TYPE_BLANK) {
					string_StdRollNo = "";
					status = "1";
				} else {
					cellStdRollNo.setCellType(Cell.CELL_TYPE_STRING);
					string_StdRollNo = cellStdRollNo.getStringCellValue();
				}

				Cell cellName = worksheet.getRow(index).getCell(1);
				String string_StdName = null;

				if (cellName == null || cellName.getCellType() == Cell.CELL_TYPE_BLANK) {
					string_StdName = "";
					status = "1";
				} else {
					cellName.setCellType(Cell.CELL_TYPE_STRING);
					string_StdName = cellName.getStringCellValue();
				}

				Cell cellEmail = worksheet.getRow(index).getCell(2);
				String string_StdEmail = null;

				if (cellEmail == null || cellEmail.getCellType() == Cell.CELL_TYPE_BLANK) {
					string_StdEmail = "";
					status = "1";
				} else {
					cellEmail.setCellType(Cell.CELL_TYPE_STRING);
					string_StdEmail = cellEmail.getStringCellValue();
				}

				Cell cellContactNo = worksheet.getRow(index).getCell(3);
				String string_ContactNo = null;

				if (cellContactNo == null || cellContactNo.getCellType() == Cell.CELL_TYPE_BLANK) {
					string_ContactNo = "";
					status = "1";
				} else {
					cellContactNo.setCellType(Cell.CELL_TYPE_STRING);
					string_ContactNo = cellContactNo.getStringCellValue();
				}
				student.setUniId(studentDto.getUniId());
				student.setDeptId(studentDto.getDeptId());
				student.setBatch(studentDto.getBatch());
				student.setStatus(status);
				student.setStdRollNo(string_StdRollNo);
				student.setEmail(string_StdEmail);
				student.setName(string_StdName);
				student.setContactNo(string_ContactNo);
				student.setDate(new Date());
				productList.add(student);
			}
		}
		return productList;
	}

	@Override
	public CommonMsg updateStudent(StudentDto studentDto) {
		CommonMsg commonMsg = new CommonMsg();
		Student std = studentRepository.findByStdId(Long.parseLong(studentDto.getUpdateId()));
		if (std != null) {
			std.setStdRollNo(studentDto.getStdID());
			std.setContactNo(studentDto.getStdDepartment());
			std.setName(studentDto.getStdName());
			studentRepository.save(std);
			commonMsg.setMsgCode("200");
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteStudent(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		studentRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<StudentSearchDtoList> listOfSearchStudent(StudentSearchDto studentSearchDto) {
		return studentRepository.listOfSearchStudent(studentSearchDto.getUniId(), studentSearchDto.getDeptId(),
				studentSearchDto.getStdId());
	}

	@Override
	public List<StudentSearchDtoList> listOfSearchStudentUserPanel(Long uniId, Long deptId, String stdId) {
		return studentRepository.listOfSearchStudent(uniId, deptId, stdId);
	}

	@Override
	public Long totalStudent() {
		return studentRepository.count();
	}
}
