package com.example.security.endpoints;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.Student;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagerController {
	
	// Data mockup
	private static final List<Student> STUDENTS = Arrays.asList(
				new Student(1, "ABC"),
				new Student(2, "QWE"),
				new Student(3, "ZXC")
			);
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROL_Administrador')") // Authorization over specific method
	public List<Student> getAllStudents() {
		return STUDENTS;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println(student);
	}
	
	@DeleteMapping(path="{studentId}")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println(studentId);
	}
	
	@PutMapping(path="{studentId}")
	public void updateStudent(@PathVariable("studentId")Integer studentId, @RequestBody Student student) {
		System.out.println(String.format("%s %s", studentId, student));
	}

}
