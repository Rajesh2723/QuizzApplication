package com.quizapp.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.quizapp.controllers.StudentController;
import com.quizapp.models.Student;
import com.quizapp.services.StudentService;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowStudentRegistrationForm() {
        // Prepare mock data
        Model model = mock(Model.class);

        // Test the method
        String viewName = studentController.showStudentRegistrationForm(model);

        // Assert the view name
        assertEquals("register", viewName);
    }

    @Test
    public void testCreateStudent() {
        // Prepare mock data
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setUsername("johndoe");
        student.setPassword("password123");

        // Test the method
        String viewName = studentController.createStudent(student);

        // Assert the view name
        assertEquals("redirect:/login/student", viewName);

        // Verify that the studentService.saveStudent method was called
        verify(studentService, times(1)).saveStudent(any(Student.class));
    }

    @Test
    public void testShowStudentDashboard() {
        // Prepare mock data
        Model model = mock(Model.class);

        // Test the method
        String viewName = studentController.showStudentDashboard(model);

        // Assert the view name
        assertEquals("student-dashboard", viewName);
    }

    @Test
    public void testShowUpdateStudentPage() {
        // Prepare mock data
        Long studentId = 1L;
        Model model = mock(Model.class);
        Student mockStudent = new Student();
        mockStudent.setId(studentId);
        when(studentService.getStudentById(studentId)).thenReturn(mockStudent);

        // Test the method
        String viewName = studentController.showUpdateStudentPage(studentId, model);

        // Assert the view name
        assertEquals("admin-student-edit", viewName);

        // Verify that studentService.getStudentById was called with the correct ID
        verify(studentService, times(1)).getStudentById(studentId);

        // Verify that the student is added to the model
        verify(model, times(1)).addAttribute(eq("student"), any(Student.class));
    }

    

}
