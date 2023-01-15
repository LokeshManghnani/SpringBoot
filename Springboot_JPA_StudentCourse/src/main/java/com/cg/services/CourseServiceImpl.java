package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.CourseRepository;
import com.cg.entities.Course;
import com.cg.exception.NoCourseException;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository cRepo;

	@Override
	public Course addCourse(Course c) {
		return cRepo.save(c);
	}

	@Override
	public List<Course> findAllCourse() {
		return cRepo.findAll();
	}

	@Override
	public Course findById(int id) throws NoCourseException {
		Optional<Course> crs = cRepo.findById(id);
		if (crs.isPresent())
			return crs.get();

		throw new NoCourseException("course not found");
	}

	@Override
	@Transactional
	public Course modifyCourse(int id, Course c) throws NoCourseException {
		Optional<Course> find = cRepo.findById(id);
		if (find.isPresent()) {
			deleteCourse(id);
			return cRepo.save(c);
		} else {
			throw new NoCourseException("course not found. cannot update.");
		}
	}

	@Override
	@Transactional
	public boolean deleteCourse(int id) throws NoCourseException {
		cRepo.deleteById(id);
		Optional<Course> crs = cRepo.findById(id);
		return !(crs.isEmpty());
	}

	@Override
	public List<Course> findCourseByName(String cName) {
		return cRepo.findByName(cName);
	}
}