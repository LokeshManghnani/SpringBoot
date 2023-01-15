package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.entities.Student;
import com.cg.exception.NoStudentException;
import com.cg.services.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService sService;

	// http://localhost:8080/StudentCourseApp/createstudent
	@PostMapping("/createstudent")
	public ResponseEntity<Student> createStudent(@RequestBody Student s) {
		try {
			Student stu = sService.addStudent(s);
			return new ResponseEntity<>(stu, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/studentlist
	@GetMapping("/studentlist")
	public ResponseEntity<List<Student>> getAllStudent() {
		try {
			List<Student> eList = sService.findAllStudent();
			if (eList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(eList, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/findstudent/id
	@GetMapping("/findstudent/{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(sService.findById(id), HttpStatus.OK);
		} catch (NoStudentException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/findstudent/name
	@GetMapping("/findstudent/{name}")
	public ResponseEntity<List<Student>> getByName(@PathVariable String name) {
		try {
			return new ResponseEntity<>(sService.findStudentByName(name), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/modifystudent/id
	@PutMapping("modifystudent/{id}")
	public ResponseEntity<Student> modifyStudent(@PathVariable("id") int id, @RequestBody Student s) {
		try {
			return new ResponseEntity<>(sService.modifyStudent(id, s), HttpStatus.OK);
		} catch (NoStudentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/deletestudent/id
	@DeleteMapping("deletestudent/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") int id) {
		try {
			return new ResponseEntity(sService.deleteStudent(id) ? "deleted" : "not deleted", HttpStatus.OK);
		} catch (NoStudentException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}