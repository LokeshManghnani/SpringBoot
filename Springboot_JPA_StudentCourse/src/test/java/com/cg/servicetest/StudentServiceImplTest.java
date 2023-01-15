package com.cg.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import com.cg.dao.StudentRepository;
import com.cg.entities.Course;
import com.cg.entities.Student;

import com.cg.exception.NoStudentException;
import com.cg.services.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

	@Mock
	private StudentRepository repo;

	@InjectMocks
	private StudentServiceImpl service;

	private List<Student> sList;
	private Course course;
	private Student student;

	private StudentServiceImplTest() {
		service = new StudentServiceImpl();
		sList = new ArrayList<>();
	}

	@BeforeEach
	void init() {
		student = new Student(1, "name");
		course = new Course(11, "name", student);
		sList.add(student);
	}

	@Test
	void addStudentTest() {
		Mockito.when(service.addStudent(student)).thenReturn(student);
		assertEquals(student, service.addStudent(student));
	}

	@Test
	void findAllStudentTest() {
		Mockito.when(service.findAllStudent()).thenReturn(sList);
		assertEquals(sList, service.findAllStudent());
	}

	@Test
	void findByIdTest() {
		try {
			Mockito.when(repo.findById(11)).thenReturn(Optional.of(student));
			assertEquals(student, service.findById(11));
		} catch (NoStudentException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

	@Test
	void modifyStudentTest() {
		student.setName("updated");
		try {
			Mockito.when(repo.findById(11)).thenReturn(Optional.of(student));
			Mockito.when(service.modifyStudent(11, student)).thenReturn(student);
			assertEquals(student, service.modifyStudent(11, student));
		} catch (NoStudentException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

	@Test
	void deleteStudentTest() {
		try {
			sList.remove(0);
			Mockito.when(repo.findById(1)).thenReturn(Optional.of(student)).thenAnswer(i -> service.deleteStudent(11));
			assertEquals(true, service.deleteStudent(1));
			assertEquals(sList, service.findAllStudent());
		} catch (NoStudentException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}
}