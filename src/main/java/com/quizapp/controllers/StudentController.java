package com.quizapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizapp.models.Session;
import com.quizapp.models.Student;
import com.quizapp.repositories.StudentRepository;
import com.quizapp.services.StudentService;

@Controller
public class StudentController {

	
	private StudentService studentService;
	

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	// Only students can register
	@GetMapping("/register/new")
	public String showStudentRegistrationForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "register";
	}

	
	@PostMapping("/students")
	public String createStudent(@ModelAttribute("student") Student student) {
		student.setRole("student");
		studentService.saveStudent(student);
		return "redirect:/login/student";
	}


	@GetMapping("/student/dashboard")
	public String showStudentDashboard(Model model) {
		model.addAttribute("student", ContextController.getStudent());
		return "student-dashboard";
	}
	
	@GetMapping("/student/edit/{id}")
	public String showUpdateStudentPage(@PathVariable Long id, Model model) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "admin-student-edit";
	}
	
	
	//ADMIN ACTIONS ON STUDENTS ENITITY*****
	@GetMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable Long id, Model model) {
		studentService.deleteStudentById(id);
		return "redirect:/admin/dashboard";
	}
	
	//Update existing student
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setUsername(student.getUsername());
		existingStudent.setPassword(student.getPassword());
		studentService.updateStudent(existingStudent);
		return "redirect:/admin/dashboard";		
	}
	
	

}
