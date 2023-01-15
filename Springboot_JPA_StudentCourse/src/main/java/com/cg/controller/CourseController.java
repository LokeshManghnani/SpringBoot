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

import com.cg.entities.Course;
import com.cg.exception.NoCourseException;
import com.cg.services.CourseService;

@RestController
public class CourseController {

	@Autowired
	CourseService cService;

	// http://localhost:8080/StudentCourseApp/createcourse
	@PostMapping("/createcourse")
	public ResponseEntity<Course> createCourse(@RequestBody Course c) {
		try {
			Course cs = cService.addCourse(c);
			return new ResponseEntity<>(cs, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/courselist
	@GetMapping("/courselist")
	public ResponseEntity<List<Course>> getAllCourse() {
		try {
			List<Course> cList = cService.findAllCourse();
			if (cList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(cList, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/findcourse/id
	@GetMapping("/findcourse{id}")
	public ResponseEntity<Course> getById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(cService.findById(id), HttpStatus.OK);
		} catch (NoCourseException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/findcourse/name
	@GetMapping("/findcourse/{name}")
	public ResponseEntity<List<Course>> getByName(@PathVariable String cName) {
		try {
			return new ResponseEntity<>(cService.findCourseByName(cName), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/modifycourse/id
	@PutMapping("modifycourse/{id}")
	public ResponseEntity<Course> modifyStudent(@PathVariable("id") int id, @RequestBody Course c) {
		try {
			return new ResponseEntity<>(cService.modifyCourse(id, c), HttpStatus.OK);
		} catch (NoCourseException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/StudentCourseApp/deletecourse/id
	@DeleteMapping("deletecourse/{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable("id") int id) {
		try {
			return new ResponseEntity(cService.deleteCourse(id) ? "deleted" : "not deleted", HttpStatus.OK);
		} catch (NoCourseException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}