package com.cg.services;

import java.util.List;

import com.cg.entities.Course;
import com.cg.exception.NoCourseException;

public interface CourseService {

	Course addCourse(Course c);

	List<Course> findAllCourse();

	Course findById(int id) throws NoCourseException;

	Course modifyCourse(int id, Course c) throws NoCourseException;

	boolean deleteCourse(int id) throws NoCourseException;
	
	List<Course> findCourseByName(String cName);
}