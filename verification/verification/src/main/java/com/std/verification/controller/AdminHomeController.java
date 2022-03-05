 package com.std.verification.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.std.verification.model.User;
import com.std.verification.service.ContactService;
import com.std.verification.service.DepartmentService;
import com.std.verification.service.PostService;
import com.std.verification.service.StudentService;
import com.std.verification.service.UniversityService;
import com.std.verification.service.UniversityURLService;
import com.std.verification.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private UniversityURLService universityUrlService;

	@Autowired
	private PostService postService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String homeFrom(Model model) {
		model.addAttribute("totalUniversity", universityService.totalUniversity());
		model.addAttribute("totalContact", contactService.totalContact());
		model.addAttribute("totalPost", postService.totalPost());
		model.addAttribute("totalUrl", universityUrlService.totalURL());
		model.addAttribute("totalStudent", studentService.totalStudent());
		return "home";
	}
	
	@GetMapping("/university")
	public String universitySetupFrom() {
		return "university-setup";
	}
	
	@GetMapping("/department")
	public String departmentSetupFrom(Model model) {
		model.addAttribute("getUniversityMap", universityService.listOfUniversity());
		return "department-setup";
	}
	
	@GetMapping("/contact")
	public String contactFrom(Model model) {
		return "user-contact";
	}
	
	@GetMapping("/profile")
	public String adminProfile(Model model, Principal principal) {
		if (principal != null) {
			User getUser = userService.findByUserName(principal.getName());
			model.addAttribute("userProfile", getUser);
		} else {
			model.addAttribute("userProfile", new User());
			return "login";
		}
		return "profile-setup";
	}

	@GetMapping("/link")
	public String linkFrom(Model model) {
		model.addAttribute("getUniversityMap", universityService.listOfUniversity());
		return "link-setup";
	}
	
	@GetMapping("/blog-post")
	public String blogPostFrom() {
		return "blog-post-setup";
	}
	
	@GetMapping("/search-student")
	public String searchStudentDetails(Model model) {
		model.addAttribute("getUniversityMap", universityService.listOfUniversity());
		return "search-student-setup";
	}

	@GetMapping("/student")
	public String studentSetupFrom(Model model) {
		model.addAttribute("getUniversityMap", universityService.listOfUniversity());
		return "student-setup";
	}
	
	@GetMapping("/access-denied")
	public String bannerFrom() {
		return "access-denied";
	}

}
