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

import com.cg.dao.CourseRepository;
import com.cg.entities.Course;
import com.cg.entities.Student;
import com.cg.exception.NoCourseException;
import com.cg.services.CourseServiceImpl;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

	@Mock
	private CourseRepository repo;

	@InjectMocks
	private CourseServiceImpl service;

	private List<Course> cList;
	private Course course;
	private Student student;

	private CourseServiceImplTest() {
		service = new CourseServiceImpl();
		cList = new ArrayList<>();
	}

	@BeforeEach
	void init() {
		student = new Student(1, "name");
		course = new Course(11, "name", student);
		cList.add(course);
	}

	@Test
	void addCourseTest() {
		Mockito.when(service.addCourse(course)).thenReturn(course);
		assertEquals(course, service.addCourse(course));
	}

	@Test
	void findAllCourseTest() {
		Mockito.when(service.findAllCourse()).thenReturn(cList);
		assertEquals(cList, service.findAllCourse());
	}

	@Test
	void findByIdTest() {
		try {
			Mockito.when(repo.findById(11)).thenReturn(Optional.of(course));
			assertEquals(course, service.findById(11));
		} catch (NoCourseException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

	@Test
	void modifyCourseTest() {
		course.setcName("updated");
		try {
			Mockito.when(repo.findById(11)).thenReturn(Optional.of(course));
			Mockito.when(service.modifyCourse(11, course)).thenReturn(course);
			assertEquals(course, service.modifyCourse(11, course));
		} catch (NoCourseException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

	@Test
	void deleteCourseTest() {
		try {
			cList.remove(0);
			Mockito.when(repo.findById(1)).thenReturn(Optional.of(course)).thenAnswer(i -> service.deleteCourse(11));
			assertEquals(true, service.deleteCourse(1));
			assertEquals(cList, service.findAllCourse());
		} catch (NoCourseException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}
}