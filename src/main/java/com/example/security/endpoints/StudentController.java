package com.example.security.endpoints;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.Student;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	
	// Data mockup
	private static final List<Student> STUDENTS = Arrays.asList(
				new Student(1, "ABC"),
				new Student(2, "QWE"),
				new Student(3, "ZXC")
			);

	@GetMapping(path="{studentId}")
	@PreAuthorize("hasAuthority('Administrador:Leer')") // Authorization over specific method
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS
				.stream()
				.filter(student -> studentId.equals(student.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalAccessError("No Student found"));
	}
}
