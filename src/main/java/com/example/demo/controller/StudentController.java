package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {
	// get all the students
	// localhost:8081/students

	@Autowired
	StudentRepository repo;

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		List<Student> students = repo.findAll();
		return students;
	}

	// get any student
	// localhost:8081/students/1
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		return student;
	}

	// add any student
	// localhost:8081/student/add
	@PostMapping("/student/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addStudent(@RequestBody Student student) {
		repo.save(student);
	}

	// update any student
	// localhost:8081/student/update/2
	@PutMapping("/student/update/{id}")
	public void updateStudent(@RequestBody Student stud, @PathVariable int id) {
		Student student = repo.findById(id).get();
		student.setBranch(stud.getBranch());
		student.setName(stud.getName());
		student.setPercentage(stud.getPercentage());
		repo.save(student);
	}

	// delete any student
	// localhost:8081/student/delete/2
	@DeleteMapping("/student/delete/{id}")
	public void deleteStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		repo.delete(student);
	}

}
