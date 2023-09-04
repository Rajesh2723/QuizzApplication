package com.quizapp.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.quizapp.controllers.RegistrationController;
import com.quizapp.dto.UserDTO;
import com.quizapp.models.Student;
import com.quizapp.models.Teacher;
import com.quizapp.services.StudentService;
import com.quizapp.services.TeacherService;

public class RegistrationControllerTest {

    @Mock
    private StudentService studentService;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private RegistrationController registrationController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterNewUser_Student() {
        // Prepare mock data
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("student");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setUsername("johndoe");
        userDTO.setPassword("password123");

        // Test the method
        String viewName = registrationController.registerNewUser(userDTO);

        // Assert the view name
        assertEquals("redirect:/admin/dashboard", viewName);

        // Verify that the studentService.saveStudent method was called
        verify(studentService, times(1)).saveStudent(any(Student.class));
        verify(teacherService, never()).saveTeacher(any(Teacher.class));
    }

    @Test
    public void testRegisterNewUser_Teacher() {
        // Prepare mock data
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("teacher");
        userDTO.setFirstName("Jane");
        userDTO.setLastName("Smith");
        userDTO.setUsername("janesmith");
        userDTO.setPassword("password456");

        // Test the method
        String viewName = registrationController.registerNewUser(userDTO);

        // Assert the view name
        assertEquals("redirect:/admin/dashboard", viewName);

        // Verify that the teacherService.saveTeacher method was called
        verify(studentService, never()).saveStudent(any(Student.class));
        verify(teacherService, times(1)).saveTeacher(any(Teacher.class));
    }
}

