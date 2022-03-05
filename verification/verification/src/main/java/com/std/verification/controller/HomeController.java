package com.std.verification.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.std.verification.model.Post;
import com.std.verification.model.University;
import com.std.verification.model.UniversityURL;
import com.std.verification.service.ContactService;
import com.std.verification.service.DepartmentService;
import com.std.verification.service.PostService;
import com.std.verification.service.StudentService;
import com.std.verification.service.UniversityService;
import com.std.verification.service.UniversityURLService;

@Controller
public class HomeController {

	@Autowired
	private UniversityService universityService;

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

	@GetMapping("/")
	public String indexFrom() {
		// model.addAttribute("totalUniversity", universityService.totalUniversity());
		return "index";
	}
	
	
	@GetMapping("/userSearch")
	public String userSearchFrm(@RequestParam("email") String email, Model model) {
		University university = universityService.checkUniversityName(email.toUpperCase());
		String urlMap = "";
		if (university != null) {
			UniversityURL universityUrl = universityUrlService.findByUniId(university.getUnID());
			if (universityUrl != null) {
				model.addAttribute("getUniversityMap", departmentService.findByUniId(university.getUnID()));
				model.addAttribute("uniName", university.getUniversityName());
				model.addAttribute("uniId", university.getUnID());
				urlMap = "search";
			} else {
				urlMap = "empty";
			}
		} else {
			urlMap = "empty";
		}
		return urlMap;
	}

	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String listBooks(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);
		Page<Post> bookPage = postService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("bookPage", bookPage);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "user-post";
	}

	@GetMapping("/login")
	public String loginFrom() {
		return "login";
	}
	
	
	
	

}
