package com.std.verification.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import com.std.verification.dto.ContactDto;
import com.std.verification.dto.CreateLinkDto;
import com.std.verification.dto.DepartmentDto;
import com.std.verification.dto.DepartmentDtoList;
import com.std.verification.dto.PostDto;
import com.std.verification.dto.ProfileDto;
import com.std.verification.dto.StudentDto;
import com.std.verification.dto.StudentInfoDtoList;
import com.std.verification.dto.StudentSearchDto;
import com.std.verification.dto.StudentSearchDtoList;
import com.std.verification.dto.UniversityDto;
import com.std.verification.dto.UniversityURLDtoList;
import com.std.verification.helper.CommonMsg;
import com.std.verification.helper.Helper;
import com.std.verification.model.Contact;
import com.std.verification.model.Department;
import com.std.verification.model.Post;
import com.std.verification.model.University;
import com.std.verification.model.UniversityURL;
import com.std.verification.model.User;
import com.std.verification.service.ContactService;
import com.std.verification.service.DepartmentService;
import com.std.verification.service.PostService;
import com.std.verification.service.StudentService;
import com.std.verification.service.UniversityService;
import com.std.verification.service.UniversityURLService;
import com.std.verification.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/ajax")
public class HomeControllerAjax {

	@Autowired
	private UniversityService universityService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private PostService postService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private UniversityURLService universityURLService;

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private Helper helper;

	@Autowired
	ApplicationContext contextPolicyScheduleReport;

	private ObjectMapper objectMapper = new ObjectMapper();

	// university panel

	@PostMapping("/saveUniversity")
	@ResponseBody
	public CommonMsg saveUniversity(@RequestBody UniversityDto universityDto) {
		return universityService.saveUniversity(universityDto);
	}

	@GetMapping(value = "/get-university-list")
	@ResponseBody
	public List<University> listOfUniversity() {
		return universityService.listOfUniversity();
	}

	@GetMapping(value = "/get-contact-list")
	@ResponseBody
	public List<Contact> listOfContact() {
		return contactService.contactList();
	}

	@GetMapping(value = "/deleteUniversity")
	@ResponseBody
	public CommonMsg deleteUniversity(@RequestParam("uniId") Long uniId) {
		return universityService.deleteUniversity(uniId);
	}

	// department panel

	@PostMapping("/saveDepartment")
	@ResponseBody
	public CommonMsg saveDepartment(@RequestBody DepartmentDto departmentDto) {
		return departmentService.saveDepartment(departmentDto);
	}

	@GetMapping(value = "/get-department-list")
	@ResponseBody
	public List<DepartmentDtoList> listOfDepartment() {
		return departmentService.listOfDepartment();
	}

	@GetMapping(value = "/deleteDepartment")
	@ResponseBody
	public CommonMsg deleteDepartment(@RequestParam("deptId") Long deptId) {
		return departmentService.deleteDepartment(deptId);
	}

	// post panel

	@PostMapping("/savePost")
	@ResponseBody
	public CommonMsg savePost(@RequestBody PostDto postDto) {
		return postService.saveUPost(postDto);
	}

	@GetMapping(value = "/get-post-list")
	@ResponseBody
	public List<Post> listOfPost() {
		return postService.listOfPost();
	}

	@GetMapping(value = "/deleteContact")
	@ResponseBody
	public CommonMsg deleteContact(@RequestParam("contactId") Long contactId) {
		return contactService.deleteContact(contactId);
	}

	@RequestMapping(value = "/excell-upload-file", method = RequestMethod.POST)
	@ResponseBody
	public CommonMsg uploadFile(@RequestParam("file") MultipartFile files,
			@RequestParam(value = "studentDto", required = true) String studentDto) throws IOException {
		StudentDto strApplicationNo = objectMapper.readValue(studentDto, StudentDto.class);
		return studentService.saveStudent(files, strApplicationNo);
	}

	@GetMapping(value = "/get-dept-list")
	@ResponseBody
	public List<Department> getDepartmentList(@RequestParam("uniId") Long uniId) {
		return departmentService.findByUniId(uniId);
	}

	@PostMapping("/filteringStudentGroup")
	@ResponseBody
	public List<StudentInfoDtoList> filteringStudentGroup(@RequestBody StudentDto studentDto) {
		return departmentService.listOfStudent(studentDto);
	}

	@GetMapping(value = "/deleteStudent")
	@ResponseBody
	public CommonMsg deleteStudent(@RequestParam("stdtId") Long stdtId) {
		return studentService.deleteStudent(stdtId);
	}

	@PostMapping("/updateStudent")
	@ResponseBody
	public CommonMsg updateStudent(@RequestBody StudentDto studentDto) {
		return studentService.updateStudent(studentDto);
	}

	@RequestMapping(value = "/create-link-logo", method = RequestMethod.POST)
	@ResponseBody
	public CommonMsg uploadLogo(@RequestParam("file") MultipartFile files,
			@RequestParam(value = "createLinkDto", required = true) String createLinkDto) throws IOException {
		CreateLinkDto createLinkDtos = objectMapper.readValue(createLinkDto, CreateLinkDto.class);
		return universityURLService.saveToBlobLogo(files, createLinkDtos);
	}

	@GetMapping(value = "/get-create-link-logo-list")
	@ResponseBody
	public List<UniversityURLDtoList> listCreateLinkLogoList() {
		return universityURLService.listOfUniversityURL();
	}

	@GetMapping(value = "/deleteLink")
	@ResponseBody
	public CommonMsg deleteLink(@RequestParam("urlId") Long urlId) {
		return universityURLService.deleteUniversityURL(urlId);
	}
	
	@GetMapping(value = "/deletePost")
	@ResponseBody
	public CommonMsg deletePost(@RequestParam("postId") Long postId) {
		return postService.deletePost(postId);
	}
	
	@GetMapping(value = "/get-link-data-byId")
	@ResponseBody
	public UniversityURL findByUrlId(@RequestParam("urlId") Long urlId) {
		return universityURLService.findByUrlId(urlId);
	}

	@PostMapping("/searchStudent")
	@ResponseBody
	public List<StudentSearchDtoList> searchStudent(@RequestBody StudentSearchDto studentSearchDto) {
		return studentService.listOfSearchStudent(studentSearchDto);
	}

	@PostMapping("/saveContact")
	@ResponseBody
	public CommonMsg saveContact(@RequestBody ContactDto contactDto) {
		return contactService.saveContact(contactDto);
	}
	
	@GetMapping("/updateContact")
	@ResponseBody
	public CommonMsg upateContact(@RequestParam("contactId") Long contactId) {
		return contactService.upateContact(contactId);
	}

	@GetMapping(value = "/generate-report")
	public String getReport(@RequestParam("uniId") String uniId, @RequestParam("deptId") String deptId,
			@RequestParam("stdId") String stdId, Model model) throws IOException, JRException {

		List<StudentSearchDtoList> getReportData = studentService.listOfSearchStudentUserPanel(Long.parseLong(uniId),Long.parseLong(deptId), stdId);
		model.addAttribute("reportList", getReportData);
		for (StudentSearchDtoList studentDtoList : studentService.listOfSearchStudentUserPanel(Long.parseLong(uniId),
				Long.parseLong(deptId), stdId)) {
			model.addAttribute("imgList", helper.getImgByBLob(studentDtoList.getLogo()));
		}
		return "report";
	}
	
	@PostMapping("/updateProfile")
	@ResponseBody
	public CommonMsg updateProfile(@RequestBody ProfileDto profileDtom) {
		return userService.updateProfileInfo(profileDtom);
	}

}
