package com.quizapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quizapp.dto.ForgotPasswordDTO;
import com.quizapp.models.Admin;
import com.quizapp.models.Student;
import com.quizapp.models.Teacher;
import com.quizapp.repositories.AdminRepository;
import com.quizapp.repositories.StudentRepository;
import com.quizapp.services.TeacherService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private TeacherService teacherService;

 	
	
 	@GetMapping("/teacher")
	public String showTeacherLoginForm(Model model) {
 		Teacher teacher = new Teacher();
 		model.addAttribute("teacher", teacher);
		return "teacher-login";
	}
 	
 	@GetMapping("/student")
	public String showStudentLoginForm(Model model) {
 		Student student = new Student();
 		model.addAttribute("student", student);
		return "student-login";
	}
 	
 	@PostMapping("/student")
 	public String loginStudent(@ModelAttribute("student") Student student) {
 		Student savedStudent = studentRepository.findByusername(student.getUsername());
 		if(savedStudent != null) {
 			 ContextController.setStudent(savedStudent);
	 		 if(savedStudent.getPassword().equalsIgnoreCase(student.getPassword())) {
	 			return "redirect:/student/dashboard";
	 		 }
 		 }
 		System.out.println("Student Login failed");
		return "redirect:/login/student?error";
 	}
 	
 	@PostMapping("/admin")
 	public String loginAdmin(@ModelAttribute("admin") Admin admin) {
 		Admin savedAdmin = adminRepository.findByusername(admin.getUsername());
 		if(savedAdmin != null) {
 			ContextController.setAdmin(savedAdmin);
 			if(savedAdmin.getPassword().equalsIgnoreCase(admin.getPassword())) {
 				return "redirect:/admin/dashboard";
 			}
 		}
 		System.out.println("Admin Login failed");
		return "redirect:/login/admin?error";
 	}
 	
 	@PostMapping("/teacher")
 	public String loginTeacher(@ModelAttribute("teacher") Teacher teacher) {
		Teacher savedTeacher = teacherService.findTeacherByUsername(teacher.getUsername());
		if(savedTeacher != null) {
			ContextController.setTeacher(savedTeacher);
			if(savedTeacher.getPassword().equalsIgnoreCase(teacher.getPassword())) {
 				return "redirect:/teacher/dashboard";
 			}
		}
		System.out.println("Teacher Login failed");
		return "redirect:/login/teacher?error";
 	}
 	
 	
 	@GetMapping("/reset")
 	public String showforgotPasswordForm(Model model) {
 		ForgotPasswordDTO forgotPasswordDto = new ForgotPasswordDTO();
 		model.addAttribute("forgotPasswordDto", forgotPasswordDto);
		return "forgot-password";
 	}
 	
 	
 	@PostMapping("/reset")
 	public String resetStudentPassword(@ModelAttribute ForgotPasswordDTO forgotPasswordDTO) {
 		if(!forgotPasswordDTO.getNewPassword().equals(forgotPasswordDTO.getNewConfirmPassword())) {
 			return "redirect:/reset?mismatch";
 		}
 		Student student = studentRepository.findByusername(forgotPasswordDTO.getUsername());
 		if(student == null) return "redirect:/reset?errorusername";
 		student.setPassword(forgotPasswordDTO.getNewPassword());
 		studentRepository.save(student);
 		return "redirect:/login/student";
 	}

}
